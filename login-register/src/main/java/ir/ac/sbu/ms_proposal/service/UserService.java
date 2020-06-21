package ir.ac.sbu.ms_proposal.service;

import ir.ac.sbu.ms_proposal.common.entity.request.UserRequestEntity;
import ir.ac.sbu.ms_proposal.common.request.AddUserRequest;
import ir.ac.sbu.ms_proposal.common.response.ActionResult;
import ir.ac.sbu.ms_proposal.common.service.ResponseParser;
import org.springframework.stereotype.Component;

@Component
public class UserService {
    private final ResponseParser parser;

    public UserService(ResponseParser parser) {
        this.parser = parser;
    }

    public ActionResult<Boolean> addUser(UserRequestEntity user, String token, String url) {
        if (validateUserFields(user)) {
            String addUserResponse = new AddUserRequest(url, user, token).sendRequest();
            return parser.parseResponse(addUserResponse, Boolean.class);
        } else {
            return new ActionResult<>(false, "لطفا مقادیر خواسته شده را وارد نمایید", false);
        }
    }

    private boolean validateUserFields(UserRequestEntity user) {
        if (user.getField() == null || user.getField().isEmpty()) {
            return false;
        }
        if (user.getName() == null || user.getName().isEmpty()) {
            return false;
        }
        if (user.getIdentificationNumber() == null || !user.getIdentificationNumber().matches("^[0-9]+$")) {
            return false;
        }
        if (user.getUsername() == null || !user.getUsername().matches("^[a-z]([a-z0-9]|_[a-z0-9]|.[a-z0-9])+$")) {
            return false;
        }
        if (user.getPassword() == null || user.getPassword().length() < 6) {
            return false;
        }
        return user.getRole() != null;
    }
}
