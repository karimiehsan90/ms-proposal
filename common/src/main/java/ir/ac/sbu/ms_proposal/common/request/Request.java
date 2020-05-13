package ir.ac.sbu.ms_proposal.common.request;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public abstract class Request {
    private Logger logger = LoggerFactory.getLogger(Request.class);

    public String sendRequest() {
        HttpPost postRequest = new HttpPost(getUrl());
        try {
            postRequest.setEntity(getBody());
            postRequest.setHeader("Accept-Encoding", "utf-8");
            try (CloseableHttpClient client = HttpClients.createDefault()) {
                CloseableHttpResponse response = client.execute(postRequest);
                InputStream inputStream = response.getEntity().getContent();
                return getResponse(inputStream);
            } catch (IOException e) {
                logger.warn("Cannot send request to {}", getUrl(), e);
            }
        } catch (UnsupportedEncodingException e) {
            logger.warn("Cannot send request to {}", getUrl(), e);
        }
        return null;
    }

    private String getResponse(InputStream inputStream) throws IOException {
        try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            StringBuilder responseBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                responseBuilder.append(line).append("\n");
            }
            return responseBuilder.toString();
        }
    }

    public abstract String getUrl();

    public abstract HttpEntity getBody() throws UnsupportedEncodingException;
}
