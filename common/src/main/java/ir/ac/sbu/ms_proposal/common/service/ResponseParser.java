package ir.ac.sbu.ms_proposal.common.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ResponseParser {
    private static final Logger logger = LoggerFactory.getLogger(ResponseParser.class);
    public <T> T parseResponse(String json, Class<T> tClass) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, tClass);
        } catch (JsonProcessingException e) {
            logger.warn("Cannot parse json output", e);
        }
        return null;
    }
}
