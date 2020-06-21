package ir.ac.sbu.ms_proposal.service;

import ir.ac.sbu.ms_proposal.common.entity.User;
import ir.ac.sbu.ms_proposal.common.request.LoginRequest;
import ir.ac.sbu.ms_proposal.common.response.ActionResult;
import ir.ac.sbu.ms_proposal.common.service.ResponseParser;
import ir.ac.sbu.ms_proposal.conf.Conf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AuthService {
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);
    private final ResponseParser parser;
    private final Conf conf;

    public AuthService(ResponseParser parser, Conf conf) {
        this.parser = parser;
        this.conf = conf;
    }

    public ActionResult<User> login(String username, String password, String url) {
        String responseString = new LoginRequest(username, password, url).sendRequest();
        return parser.parseResponse(responseString, ActionResult.class);
    }
}
