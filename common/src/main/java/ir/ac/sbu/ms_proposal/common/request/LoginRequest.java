package ir.ac.sbu.ms_proposal.common.request;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class LoginRequest extends Request {
    private String username;
    private String password;
    private String url;

    public LoginRequest(String username, String password, String url) {
        this.username = username;
        this.password = password;
        this.url = url;
    }

    @Override
    public String getUrl() {
        return this.url;
    }

    @Override
    public HttpEntity getBody() throws UnsupportedEncodingException {
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("username", this.username));
        nameValuePairs.add(new BasicNameValuePair("password", this.password));
        return new UrlEncodedFormEntity(nameValuePairs);
    }
}
