package ir.ac.sbu.ms_proposal.common.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.ac.sbu.ms_proposal.common.entity.Proposal;
import ir.ac.sbu.ms_proposal.common.response.ActionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResponseParser {
    private static final Logger logger = LoggerFactory.getLogger(ResponseParser.class);
    public <T> ActionResult<T> parseResponse(String json, Class<T> tClass) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JavaType type = mapper.getTypeFactory().constructParametricType(ActionResult.class, tClass);
            return mapper.readValue(json, type);
        } catch (JsonProcessingException e) {
            logger.warn("Cannot parse json output", e);
        }
        return null;
    }

    public <T> ActionResult<List<T>> parseListResponse(String json, Class tClass) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JavaType listType = mapper.getTypeFactory().constructParametricType(List.class, tClass);
            JavaType actionResultType = mapper.getTypeFactory().constructParametricType(ActionResult.class, listType);
            return mapper.readValue(json, actionResultType);
        } catch (JsonProcessingException e) {
            logger.warn("Cannot parse json output", e);
        }
        return null;
    }
}
