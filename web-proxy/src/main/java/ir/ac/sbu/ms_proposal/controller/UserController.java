package ir.ac.sbu.ms_proposal.controller;

import ir.ac.sbu.ms_proposal.common.entity.request.UserRequestEntity;
import ir.ac.sbu.ms_proposal.common.response.ActionResult;
import ir.ac.sbu.ms_proposal.conf.Conf;
import ir.ac.sbu.ms_proposal.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService service;
    private final Conf conf;

    public UserController(UserService service, Conf conf) {
        this.service = service;
        this.conf = conf;
    }

    @CrossOrigin
    @RequestMapping("/add")
    public ActionResult<Boolean> addUser(@RequestBody UserRequestEntity user,
                                         @RequestHeader("ms_proposal_token") String token) {
        return service.addUser(user, token, String
                .format("http://%s:%s/user/add", conf.getLoginRegister().getHost(), conf.getLoginRegister().getPort()));
    }
}
