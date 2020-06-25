package ir.ac.sbu.ms_proposal.common.request;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

import java.io.UnsupportedEncodingException;

public class TransferredProposalRequest extends Request {
    private String token;
    private String url;

    public TransferredProposalRequest(String token, String url) {
        this.token = token;
        this.url = url;
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
        return new StringEntity("");
    }
}
