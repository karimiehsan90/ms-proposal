package ir.ac.sbu.ms_proposal.common.request;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class GetUserByTokenRequest extends Request {
    private String url;
    private String token;

    public GetUserByTokenRequest(String url, String token) {
        this.url = url;
        this.token = token;
    }

    @Override
    public String getUrl() {
        return this.url;
    }

    @Override
    public HttpEntity getBody() throws UnsupportedEncodingException {
        List<NameValuePair> nameValuePairList = new ArrayList<>();
        nameValuePairList.add(new BasicNameValuePair("token", token));
        return new UrlEncodedFormEntity(nameValuePairList);
    }
}
