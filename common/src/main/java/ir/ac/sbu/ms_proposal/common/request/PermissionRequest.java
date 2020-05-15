package ir.ac.sbu.ms_proposal.common.request;

import ir.ac.sbu.ms_proposal.common.entity.Permission;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class PermissionRequest extends Request {
    private String url;
    private Permission permission;
    private String token;

    public PermissionRequest(String url, Permission permission, String token) {
        this.url = url;
        this.permission = permission;
        this.token = token;
    }

    @Override
    public String getUrl() {
        return this.url;
    }

    @Override
    public void setHeaders(HttpPost postRequest) {
        super.setHeaders(postRequest);
        postRequest.setHeader("ms-proposal-token", token);
    }

    @Override
    public HttpEntity getBody() throws UnsupportedEncodingException {
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("permission", this.permission.toString()));
        return new UrlEncodedFormEntity(nameValuePairs);
    }
}
