package ir.ac.sbu.ms_proposal.controller;

import ir.ac.sbu.ms_proposal.common.entity.User;
import ir.ac.sbu.ms_proposal.common.response.ActionResult;
import ir.ac.sbu.ms_proposal.conf.Conf;
import ir.ac.sbu.ms_proposal.service.AuthService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthController {
    private final AuthService authService;
    private final Conf conf;

    public AuthController(AuthService authService, Conf conf) {
        this.authService = authService;
        this.conf = conf;
    }

    @RequestMapping("/login")
    @CrossOrigin
    public ActionResult<User> login(@RequestParam("username") String username,
                                    @RequestParam("password") String password) {
        return authService.login(username, password, String
                .format("http://%s:%d/login", conf.getLoginRegister().getHost(), conf.getLoginRegister().getPort()));
    }
}
