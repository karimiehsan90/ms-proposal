package ir.ac.sbu.ms_proposal.controller;

import ir.ac.sbu.ms_proposal.common.entity.Permission;
import ir.ac.sbu.ms_proposal.common.entity.User;
import ir.ac.sbu.ms_proposal.common.entity.request.UserRequestEntity;
import ir.ac.sbu.ms_proposal.common.response.ActionResult;
import ir.ac.sbu.ms_proposal.conf.Conf;
import ir.ac.sbu.ms_proposal.service.AuthService;
import ir.ac.sbu.ms_proposal.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final AuthService authService;
    private final UserService userService;
    private final Conf conf;

    public UserController(AuthService authService, UserService userService, Conf conf) {
        this.authService = authService;
        this.userService = userService;
        this.conf = conf;
    }

    @RequestMapping("/add")
    private ActionResult<Boolean> addUser(@RequestBody UserRequestEntity user,
                                          @RequestHeader("ms-proposal-token") String token) {
        if (authService.hasPermission(token, Permission.ADD_USER)) {
            return userService.addUser(user, token, String
                .format("http://%s:%s/user/add", conf.getAuth().getHost(), conf.getAuth().getPort()));
        }
        return new ActionResult<>(false, "شما اجازه دسترسی به این مورد را ندارید", false);
    }
}
