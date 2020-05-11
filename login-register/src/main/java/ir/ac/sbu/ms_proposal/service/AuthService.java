package ir.ac.sbu.ms_proposal.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.ac.sbu.ms_proposal.common.entity.User;
import ir.ac.sbu.ms_proposal.common.request.LoginRequest;
import ir.ac.sbu.ms_proposal.common.response.ActionResult;
import ir.ac.sbu.ms_proposal.common.service.ResponseParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AuthService {
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);
    private final ResponseParser parser;

    public AuthService(ResponseParser parser) {
        this.parser = parser;
    }

    public ActionResult<User> login(String username, String password, String url) {
        String responseString = new LoginRequest(username, password, url).sendRequest();
        return parser.parseResponse(responseString, ActionResult.class);
    }
}
