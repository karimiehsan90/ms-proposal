package ir.ac.sbu.ms_proposal.controller;

import ir.ac.sbu.ms_proposal.common.entity.Permission;
import ir.ac.sbu.ms_proposal.common.entity.Proposal;
import ir.ac.sbu.ms_proposal.common.response.ActionResult;
import ir.ac.sbu.ms_proposal.common.service.PermissionService;
import ir.ac.sbu.ms_proposal.conf.Conf;
import ir.ac.sbu.ms_proposal.service.ProposalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proposal")
public class ProposalController {
    private final PermissionService permissionService;
    private final ProposalService proposalService;
    private final Conf conf;

    public ProposalController(PermissionService permissionService, ProposalService proposalService, Conf conf) {
        this.permissionService = permissionService;
        this.proposalService = proposalService;
        this.conf = conf;
    }

    @RequestMapping("/add")
    public ActionResult<Boolean> addProposal(@RequestBody Proposal proposal,
                                             @RequestHeader("ms-proposal-token") String token) {
        if (permissionService.hasPermission(token, Permission.ADD_PROPOSAL, conf.getAuth())) {
            return proposalService.addProposal(token, proposal);
        }
        return new ActionResult<>(false, "شما اجازه دسترسی به این مورد را ندارید", false);
    }

    @RequestMapping("/transferred")
    public ActionResult<List<Proposal>> getTransferredProposals(@RequestHeader("ms-proposal-token") String token) {
        if (permissionService.hasPermission(token, Permission.ACCEPT_PROPOSAL, conf.getAuth())) {
            return new ActionResult<>(true, null, proposalService.getTransferredProposals(token, String
                .format("http://%s:%s/login/token", conf.getAuth().getHost(), conf.getAuth().getPort())));
        }
        return new ActionResult<>(false, "شما اجازه دسترسی به این مورد را ندارید", null);
    }

    @RequestMapping("/accept")
    public ActionResult<Boolean> acceptProposal(@RequestHeader("ms-proposal-token") String token,
                                                @RequestParam("proposal_id") String proposalId,
                                                @RequestParam("is_accepted") boolean accept) {
        if (proposalService.hasPermissionToProposal(token, proposalId, String.
                format("http://%s:%s/login/token", conf.getAuth().getHost(), conf.getAuth().getPort()))) {
            return proposalService.acceptProposal(proposalId, accept);
        }
        return new ActionResult<>(false, "شما اجازه دسترسی به این مورد را ندارید", false);
    }
}
