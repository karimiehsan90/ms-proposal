package ir.ac.sbu.ms_proposal.service;

import ir.ac.sbu.ms_proposal.common.entity.Proposal;
import ir.ac.sbu.ms_proposal.common.request.AddProposalRequest;
import ir.ac.sbu.ms_proposal.common.response.ActionResult;
import ir.ac.sbu.ms_proposal.common.service.ResponseParser;
import org.springframework.stereotype.Component;

@Component
public class ProposalService {
    private final ResponseParser parser;

    public ProposalService(ResponseParser parser) {
        this.parser = parser;
    }

    public ActionResult<Boolean> addProposal(Proposal proposal, String token, String url) {
        String responseJson = new AddProposalRequest(url, token, proposal).sendRequest();
        return parser.parseResponse(responseJson, ActionResult.class);
    }
}
