package ir.ac.sbu.ms_proposal.controller;

import ir.ac.sbu.ms_proposal.common.entity.Permission;
import ir.ac.sbu.ms_proposal.common.entity.User;
import ir.ac.sbu.ms_proposal.common.entity.request.UserRequestEntity;
import ir.ac.sbu.ms_proposal.common.response.ActionResult;
import ir.ac.sbu.ms_proposal.common.service.PermissionService;
import ir.ac.sbu.ms_proposal.conf.Conf;
import ir.ac.sbu.ms_proposal.service.AuthService;
import ir.ac.sbu.ms_proposal.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final AuthService authService;
    private final UserService userService;
    private final PermissionService permissionService;
    private final Conf conf;

    public UserController(AuthService authService, UserService userService,
                          PermissionService permissionService, Conf conf) {
        this.authService = authService;
        this.userService = userService;
        this.permissionService = permissionService;
        this.conf = conf;
    }

    @RequestMapping("/add")
    public ActionResult<Boolean> addUser(@RequestBody UserRequestEntity user,
                                          @RequestHeader("ms-proposal-token") String token) {
        logger.warn(user.getName());
        if (permissionService.hasPermission(token, Permission.ADD_USER, conf.getAuth())) {
            return userService.addUser(user, token, String
                .format("http://%s:%s/user/add", conf.getAuth().getHost(), conf.getAuth().getPort()));
        }
        return new ActionResult<>(false, "شما اجازه دسترسی به این مورد را ندارید", false);
    }

    @RequestMapping("/get")
    public ActionResult<List<User>> getUsers() {

            return userService.getUsers(String.format("http://%s:%s/user/get",
                                                        conf.getAuth().getHost(), conf.getAuth().getPort()));
    }

}
