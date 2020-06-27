package ir.ac.sbu.ms_proposal.controller;

import ir.ac.sbu.ms_proposal.common.entity.Proposal;
import ir.ac.sbu.ms_proposal.common.response.ActionResult;
import ir.ac.sbu.ms_proposal.conf.Conf;
import ir.ac.sbu.ms_proposal.service.ProposalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proposal")
public class ProposalController {
    private final Conf conf;
    private final ProposalService service;

    public ProposalController(Conf conf, ProposalService service) {
        this.service = service;
        this.conf = conf;
    }

    @RequestMapping("/add")
    @CrossOrigin
    public ActionResult<Boolean> addProposal(@RequestBody Proposal proposal,
                                             @RequestHeader("ms_proposal_token") String token) {
        return service.addProposal(proposal, token, String
                .format("http://%s:%s/proposal/add", conf.getProposal().getHost(), conf.getProposal().getPort()));
    }

    @RequestMapping("/transferred")
    @CrossOrigin
    public ActionResult<List<Proposal>> getTransferredProposals(@RequestHeader("ms_proposal_token") String token) {
        return service.getTransferredProposals(token, String
                .format("http://%s:%s/proposal/transferred",
                        conf.getProposal().getHost(),
                        conf.getProposal().getPort()));
    }

    @RequestMapping("/accept")
    @CrossOrigin
    public ActionResult<Boolean> acceptProposal(@RequestHeader("ms_proposal_token") String token,
                                                @RequestParam("proposal_id") String proposalId,
                                                @RequestParam("is_accepted") boolean accept) {
        return service.acceptProposal(String
                .format("http://%s:%s/proposal/accept", conf.getProposal().getHost(), conf.getProposal().getPort()),
                proposalId, accept, token);
    }
}
