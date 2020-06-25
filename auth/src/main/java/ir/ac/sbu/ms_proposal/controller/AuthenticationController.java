package ir.ac.sbu.ms_proposal.controller;

import ir.ac.sbu.ms_proposal.common.response.ActionResult;
import ir.ac.sbu.ms_proposal.conf.Conf;
import ir.ac.sbu.ms_proposal.common.entity.User;
import ir.ac.sbu.ms_proposal.service.AuthenticationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AuthenticationController {
    private final Conf conf;
    private final AuthenticationService authenticationService;

    public AuthenticationController(Conf conf, AuthenticationService authenticationService) {
        this.conf = conf;
        this.authenticationService = authenticationService;
    }

    @RequestMapping("/login")
    public ActionResult<User> login(@RequestParam("username") String username,
                                    @RequestParam("password") String password) {
        ActionResult<User> result = new ActionResult<>();
        Optional<User> optionalUser = authenticationService.login(username, password);
        if (optionalUser.isPresent()) {
            result.setSuccess(true);
            result.setData(optionalUser.get());
        }
        else {
            result.setSuccess(false);
            result.setError("نام کاربری یا پسورد شما اشتباه است");
        }
        return result;
    }

    @RequestMapping("/login/token")
    public ActionResult<User> getUserByToken(@RequestParam("token") String token) {
        Optional<User> optionalUser = authenticationService.getByToken(token);
        return optionalUser.map(user -> new ActionResult<>(true, null, user))
                .orElseGet(() -> new ActionResult<>(false, "کاربر یافت نشد", null));
    }
}
