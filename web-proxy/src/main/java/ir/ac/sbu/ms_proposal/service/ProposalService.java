package ir.ac.sbu.ms_proposal.service;

import ir.ac.sbu.ms_proposal.common.entity.Proposal;
import ir.ac.sbu.ms_proposal.common.request.AcceptProposalRequest;
import ir.ac.sbu.ms_proposal.common.request.AddProposalRequest;
import ir.ac.sbu.ms_proposal.common.request.TransferredProposalRequest;
import ir.ac.sbu.ms_proposal.common.response.ActionResult;
import ir.ac.sbu.ms_proposal.common.service.ResponseParser;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProposalService {
    private final ResponseParser parser;

    public ProposalService(ResponseParser parser) {
        this.parser = parser;
    }

    public ActionResult<Boolean> addProposal(Proposal proposal, String token, String url) {
        String responseJson = new AddProposalRequest(url, token, proposal).sendRequest();
        return parser.parseResponse(responseJson, Boolean.class);
    }

    public ActionResult<List<Proposal>> getTransferredProposals(String token, String url) {
        String responseJson = new TransferredProposalRequest(token, url).sendRequest();
        return parser.parseListResponse(responseJson, Proposal.class);
    }

    public ActionResult<Boolean> acceptProposal(String url, String proposalId, boolean accept, String token) {
        String responseJson = new AcceptProposalRequest(url, proposalId, accept, token).sendRequest();
        return parser.parseResponse(responseJson, Boolean.class);
    }
}
