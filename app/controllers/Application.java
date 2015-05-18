package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Seller;
import models.User;
import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {
    private static UserQueryHandler userQueryHandler = new UserQueryHandler();

    public static Result index() {
        return ok(index.render("Hi Uday!"));
    }

    public static Result returnName(String id) {
        return ok("Hi, " + id);
    }

    @BodyParser.Of(BodyParser.Json.class)
    public static Result postendpoint() {
        Http.RequestBody body = request().body();
        JsonNode jsonNode = body.asJson();

        ObjectMapper jsonObjectMapper = new ObjectMapper();
        try {
            User user = jsonObjectMapper.treeToValue(jsonNode, User.class);
            return ok("user json: " + jsonObjectMapper.valueToTree(user));

        } catch (Exception ex) {
            return ok("error handling query");
        }
    }

    @BodyParser.Of(BodyParser.Json.class)
    public static Result newpostendpoint() {
        try {
            ObjectMapper objectJsonMapper = new ObjectMapper();
            User user = objectJsonMapper.treeToValue(request().body().asJson(), User.class);
            Seller sellerForUser = userQueryHandler.getSellerForUser(user);
            JsonNode jsonNode = objectJsonMapper.valueToTree(sellerForUser);
            return ok(jsonNode);
        } catch (Exception ex) {
            return ok("error handling query");
        }
    }
}
