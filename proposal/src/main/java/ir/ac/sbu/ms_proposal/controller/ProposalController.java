package ir.ac.sbu.ms_proposal.controller;

import ir.ac.sbu.ms_proposal.common.entity.Permission;
import ir.ac.sbu.ms_proposal.common.entity.Proposal;
import ir.ac.sbu.ms_proposal.common.response.ActionResult;
import ir.ac.sbu.ms_proposal.common.service.PermissionService;
import ir.ac.sbu.ms_proposal.conf.Conf;
import ir.ac.sbu.ms_proposal.service.ProposalService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
