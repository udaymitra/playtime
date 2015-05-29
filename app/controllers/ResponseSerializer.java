package controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Created by pranav on 5/29/15.
 */
public class ResponseSerializer {
    public static ObjectNode createJson(String message) {
        return play.libs.Json.newObject().put("Message", message);
    }
}
