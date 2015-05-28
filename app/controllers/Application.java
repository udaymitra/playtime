package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    public static Result index() {
        return ok(index.render("Hi Pranav!"));
    }

    @BodyParser.Of(BodyParser.Json.class)
    public static Result loginSeller() {
        try {
            EmailPassword emailPassword = RequestParser.getJsonFromRequest(request(), EmailPassword.class);
            LoginCredentials loginCredentials = new LoginCredentials(emailPassword, LoginCredentials.UserType.SELLER);
            boolean sellerCredentialsVerified = sellerTableQueries.checkSellerCredentials(loginCredentials);
            return sellerCredentialsVerified ? ok("success") : ok("failure");
        } catch (Exception ex) {
            return ok("error handling query");
        }
    }

    @BodyParser.Of(BodyParser.Json.class)
    public static Result addNewSeller() {
        try {
            Seller seller = RequestParser.getJsonFromRequest(request(), Seller.class);
            System.out.println(seller);
            boolean addNewSellerSuccess = sellerTableQueries.addNewSeller(seller);
            return addNewSellerSuccess ? ok("success") : ok("failure");
        } catch (Exception ex) {
            return ok("error handling query");
        }
    }
}
