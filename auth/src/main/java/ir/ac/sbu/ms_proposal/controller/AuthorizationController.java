package ir.ac.sbu.ms_proposal.controller;

import ir.ac.sbu.ms_proposal.common.entity.Permission;
import ir.ac.sbu.ms_proposal.common.response.ActionResult;
import ir.ac.sbu.ms_proposal.service.AuthorizationService;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/permission")
public class AuthorizationController {
    private final AuthorizationService service;

    public AuthorizationController(AuthorizationService service) {
        this.service = service;
    }

    @RequestMapping("/get")
    public ActionResult<Boolean> hasPermission(@RequestHeader("ms-proposal-token") String token
            , @RequestParam("permission")Permission permission) {
        return service.hasPermission(token, permission);
    }
}
