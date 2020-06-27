package ir.ac.sbu.ms_proposal.service;

import ir.ac.sbu.ms_proposal.common.entity.Proposal;
import ir.ac.sbu.ms_proposal.common.entity.ProposalState;
import ir.ac.sbu.ms_proposal.common.entity.User;
import ir.ac.sbu.ms_proposal.common.request.GetUserByTokenRequest;
import ir.ac.sbu.ms_proposal.common.response.ActionResult;
import ir.ac.sbu.ms_proposal.common.service.ResponseParser;
import ir.ac.sbu.ms_proposal.repository.ProposalRepository;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Component
public class ProposalService {
    private final ProposalRepository repository;
    private final ResponseParser parser;

    public ProposalService(ProposalRepository repository, ResponseParser parser) {
        this.repository = repository;
        this.parser = parser;
    }

    public ActionResult<Boolean> addProposal(String token, Proposal proposal) {
        ActionResult<Boolean> result = new ActionResult<>();
        if (isProposalValid(proposal)) {
            proposal.setOwnerId(token);
            proposal.setSubmitTime(Calendar.getInstance().getTimeInMillis());
            repository.save(proposal);
            result.setSuccess(true);
        } else {
            result.setError("لطفا مقادیر خواسته شده را وارد نمایید");
        }
        return result;
    }

    private boolean isProposalValid(Proposal proposal) {
        if (proposal.getTitle() == null || proposal.getTitle().isEmpty()) {
            return false;
        }
        if (proposal.getDescription() == null || proposal.getDescription().isEmpty()) {
            return false;
        }
        if (proposal.getKeywords() == null || proposal.getKeywords().isEmpty()) {
            return false;
        }
        return proposal.getTeacherIdNumber() != null;
    }

    public List<Proposal> getTransferredProposals(String token, String url) {
        String responseJson = new GetUserByTokenRequest(url, token).sendRequest();
        ActionResult<User> userActionResult = parser.parseResponse(responseJson, User.class);
        String teacherIdNum = userActionResult.getData().getIdentificationNumber();
        return repository.getByTeacherIdNumber(teacherIdNum);
    }

    public boolean hasPermissionToProposal(String token, String proposalId, String url) {
        String responseJson = new GetUserByTokenRequest(url, token).sendRequest();
        ActionResult<User> userActionResult = parser.parseResponse(responseJson, User.class);
        String teacherIdNum = userActionResult.getData().getIdentificationNumber();
        return repository.getByTeacherIdNumberAndId(teacherIdNum, proposalId).isPresent();
    }

    public ActionResult<Boolean> acceptProposal(String proposalId, boolean accept) {
        Optional<Proposal> proposalOptional = repository.getById(proposalId);
        proposalOptional.ifPresent(proposal -> {
            proposal.setProposalState(accept ? ProposalState.TEACHER_ACCEPTED : ProposalState.TEACHER_REJECTED);
            repository.save(proposal);
        });
        return new ActionResult<>(true, null, true);
    }
}
