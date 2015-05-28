package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import play.mvc.Http;

/**
 * Created by soumya on 5/27/15.
 */
public class RequestParser<T> {
    public static <T> T getJsonFromRequest(Http.Request request, Class<T> valueType) throws JsonProcessingException {
        ObjectMapper objectJsonMapper = new ObjectMapper();
        return objectJsonMapper.treeToValue(request.body().asJson(), valueType);
    }
}
