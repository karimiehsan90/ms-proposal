package ir.ac.sbu.ms_proposal.common.request;

import ir.ac.sbu.ms_proposal.common.entity.Proposal;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class AddProposalRequest extends Request {
    public AddProposalRequest(String url, String token, Proposal proposal) {
        this.url = url;
        this.token = token;
        this.proposal = proposal;
    }

    private String url;
    private String token;
    private Proposal proposal;

    @Override
    public String getUrl() {
        return this.url;
    }

    @Override
    protected void setHeaders(HttpPost postRequest) {
        super.setHeaders(postRequest);
        postRequest.setHeader("ms-proposal-token", token);
        postRequest.setHeader("Content-Type", "application/json");
    }

    @Override
    public HttpEntity getBody() throws UnsupportedEncodingException {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("passed_credits", proposal.getPassedCredits());
            jsonObject.put("description", proposal.getDescription());
            jsonObject.put("start_time", proposal.getStartTime());
            jsonObject.put("submit_time", proposal.getSubmitTime());
            jsonObject.put("end_time", proposal.getEndTime());
            jsonObject.put("keywords", new JSONArray(proposal.getKeywords()));
            jsonObject.put("title", proposal.getTitle());
            jsonObject.put("specific_facilities", proposal.getSpecificFacilities());
            jsonObject.put("teacher_id_num", proposal.getTeacherIdNumber());
            jsonObject.put("term", proposal.getTerm());
            return new StringEntity(jsonObject.toString(), StandardCharsets.UTF_8);
        } catch (JSONException e) {
            throw new UnsupportedEncodingException("Json Exception " + e.getMessage());
        }
    }
}
