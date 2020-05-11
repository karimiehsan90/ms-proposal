package ir.ac.sbu.ms_proposal.controller;

import ir.ac.sbu.ms_proposal.common.entity.User;
import ir.ac.sbu.ms_proposal.common.response.ActionResult;
import ir.ac.sbu.ms_proposal.conf.Conf;
import ir.ac.sbu.ms_proposal.service.AuthService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final Conf conf;
    private final AuthService authService;

    public AuthController(Conf conf, AuthService authService) {
        this.conf = conf;
        this.authService = authService;
    }

    @RequestMapping("/login")
    public ActionResult<User> login(@RequestParam("username") String username,
                                    @RequestParam("password") String password) {
        return authService.login(username, password, String
                .format("http://%s:%d/login", conf.getAuth().getHost(), conf.getAuth().getPort()));
    }
}
