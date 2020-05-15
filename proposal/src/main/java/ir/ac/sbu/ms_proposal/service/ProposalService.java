package ir.ac.sbu.ms_proposal.service;

import ir.ac.sbu.ms_proposal.common.entity.Proposal;
import ir.ac.sbu.ms_proposal.common.response.ActionResult;
import ir.ac.sbu.ms_proposal.repository.ProposalRepository;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class ProposalService {
    private final ProposalRepository repository;

    public ProposalService(ProposalRepository repository) {
        this.repository = repository;
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
}
