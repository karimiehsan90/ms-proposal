package ir.ac.sbu.ms_proposal.common.request;

import ir.ac.sbu.ms_proposal.common.entity.request.UserRequestEntity;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class AddUserRequest extends Request {
    private static final Logger logger = LoggerFactory.getLogger(AddUserRequest.class);
    private String url;
    private UserRequestEntity user;
    private String token;

    public AddUserRequest(String url, UserRequestEntity user, String token) {
        this.url = url;
        this.user = user;
        this.token = token;
    }

    @Override
    protected void setHeaders(HttpPost postRequest) {
        super.setHeaders(postRequest);
        postRequest.setHeader("ms-proposal-token", token);
        postRequest.setHeader("Content-Type", "application/json");
    }

    @Override
    public String getUrl() {
        return this.url;
    }

    @Override
    public HttpEntity getBody() throws UnsupportedEncodingException {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", this.user.getName());
            jsonObject.put("id_number", this.user.getIdentificationNumber());
            jsonObject.put("username", this.user.getUsername());
            jsonObject.put("password", this.user.getPassword());
            jsonObject.put("role", this.user.getRole());
            jsonObject.put("field", this.user.getField());
            logger.warn(jsonObject.toString());
            return new StringEntity(jsonObject.toString(), StandardCharsets.UTF_8);
        } catch (JSONException e) {
            throw new UnsupportedEncodingException("Json Exception " + e.getMessage());
        }
    }
}
