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
        String addUserResponse = new AddUserRequest(url, user, token).sendRequest();
        return parser.parseResponse(addUserResponse, ActionResult.class);
    }
}
