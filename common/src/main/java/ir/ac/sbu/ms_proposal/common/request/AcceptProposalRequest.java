package ir.ac.sbu.ms_proposal.common.request;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class AcceptProposalRequest extends Request {
    private String url;
    private String proposalId;
    private boolean accept;
    private String token;

    public AcceptProposalRequest(String url, String proposalId, boolean accept, String token) {
        this.url = url;
        this.proposalId = proposalId;
        this.accept = accept;
        this.token = token;
    }

    @Override
    public String getUrl() {
        return this.url;
    }

    @Override
    protected void setHeaders(HttpPost postRequest) {
        super.setHeaders(postRequest);
        postRequest.setHeader("ms-proposal-token", this.token);
    }

    @Override
    public HttpEntity getBody() {
        List<NameValuePair> nameValuePairList = new ArrayList<>();
        nameValuePairList.add(new BasicNameValuePair("proposal_id", proposalId));
        nameValuePairList.add(new BasicNameValuePair("is_accepted", String.valueOf(accept)));
        return new UrlEncodedFormEntity(nameValuePairList, StandardCharsets.UTF_8);
    }
}
