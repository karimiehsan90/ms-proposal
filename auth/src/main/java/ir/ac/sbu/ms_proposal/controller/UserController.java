package ir.ac.sbu.ms_proposal.controller;

import ir.ac.sbu.ms_proposal.common.entity.User;
import ir.ac.sbu.ms_proposal.common.entity.request.UserRequestEntity;
import ir.ac.sbu.ms_proposal.common.response.ActionResult;
import ir.ac.sbu.ms_proposal.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @RequestMapping("/add")
    public ActionResult<Boolean> addUser(@RequestBody UserRequestEntity user) {
        return service.addUser(user);
    }

    @RequestMapping("/get")
    public ActionResult<List<User>> getUsers() {
        return service.getUsers();
    }

}
