package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import database.DatabaseQueryHandler;
import models.Seller;
import models.User;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import views.html.index;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.stream.Stream;

public class Application extends Controller {
    // Custom Objects
    private static UserQueryHandler userQueryHandler = new UserQueryHandler();
    private static DatabaseQueryHandler databaseQueryHandler = new DatabaseQueryHandler();
    // Get Point
    public static Result index() {
        return ok(index.render("Hi Pranav!"));
    }
    // Get Point
    public static Result returnName(String id) {
        return ok("Hi there, " + id);
    }
    // Get Ticker Point
    public static Result returnPrice(String name, String year){
        String query = "select price from stocks where name=\"" + name + "\"";
        System.out.println(query);
        ResultSet resultSet = databaseQueryHandler.getResults(query);
        try {
            if (resultSet.next()) {
                String price = resultSet.getString("price");
                return ok(price);
            }
            return ok("No price found");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
