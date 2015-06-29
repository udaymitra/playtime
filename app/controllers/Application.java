package controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import database.DatabaseQueryHandler;
import database.SellerTableQueries;
import models.EmailPassword;
import models.LoginCredentials;
import models.Seller;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;


public class Application extends Controller {
    private static DatabaseQueryHandler databaseQueryHandler = new DatabaseQueryHandler();
    private static SellerTableQueries sellerTableQueries = new SellerTableQueries(databaseQueryHandler);

    private static final String USER_EMAIL = "USER_EMAIL";
    private static final String USER_LAST_LOGIN_TIMESTAMP = "USER_LAST_LOGIN_TIMESTAMP";

    public static Result index() {
        return ok(index.render("Hi Pranav Achanta!"));
    }

    @BodyParser.Of(BodyParser.Json.class)
    public static Result loginSeller() {
        try {
            EmailPassword emailPassword = RequestParser.getJsonFromRequest(request(), EmailPassword.class);
            LoginCredentials loginCredentials = new LoginCredentials(emailPassword, LoginCredentials.UserType.SELLER);
            boolean sellerCredentialsVerified = sellerTableQueries.checkSellerCredentials(loginCredentials);

            if (sellerCredentialsVerified) {
                session(USER_EMAIL, emailPassword.email);
                String lastLoginTimestamp = "" + System.currentTimeMillis();
                session(USER_LAST_LOGIN_TIMESTAMP, lastLoginTimestamp);
            }
            ObjectNode result = sellerCredentialsVerified
                    ? ResponseSerializer.createJson("success")
                    : ResponseSerializer.createJson("failure");
            return ok(result);
        } catch (Exception ex) {
            return ok("error handling query");
        }
    }

    @BodyParser.Of(BodyParser.Json.class)
    public static Result addNewSeller() {
        try {
            Seller seller = RequestParser.getJsonFromRequest(request(), Seller.class);
            boolean addNewSellerSuccess = sellerTableQueries.addNewSeller(seller);
            ObjectNode result = addNewSellerSuccess
                    ? ResponseSerializer.createJson("success")
                    : ResponseSerializer.createJson("failure");
            return  ok(result);
        } catch (Exception ex) {
            return ok("error handling query");
        }
    }

    @BodyParser.Of(BodyParser.Json.class)
    public static Result validateEmail(){
        try{
            String email = RequestParser.getJsonFromRequest(request(), String.class);
            boolean validEmail = sellerTableQueries.validateEmail(email);
            ObjectNode result = validEmail
                    ? ResponseSerializer.createJson("success")
                    : ResponseSerializer.createJson("failure");
            return ok(result);
        }catch (Exception ex){
            return ok("Internal Server Error");
        }
    }
}
