package database;

import com.google.common.base.Preconditions;
import models.LoginCredentials;
import models.Seller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by soumya on 5/27/15.
 */
public class SellerTableQueries {
    DatabaseQueryHandler databaseQueryHandler;

    public SellerTableQueries(DatabaseQueryHandler databaseQueryHandler) {
        this.databaseQueryHandler = databaseQueryHandler;
    }

    public boolean checkSellerCredentials(LoginCredentials loginCredentials) throws SQLException {
        Preconditions.checkArgument(loginCredentials.userType == LoginCredentials.UserType.SELLER);
        String query = String.format("SELECT * FROM seller WHERE email LIKE '%s' AND pass LIKE '%s'",
                loginCredentials.emailPassword.email, loginCredentials.emailPassword.pass);
        ResultSet resultSet = databaseQueryHandler.getResults(query);
        List<String> emails = new ArrayList<>();
        while (resultSet.next()) {
            Optional.ofNullable(resultSet.getString("email"))
                    .ifPresent(emails::add);
        }

        return !emails.isEmpty();
    }

    public boolean addNewSeller(Seller seller) throws SQLException {
        boolean sellerExists = checkSellerCredentials(new LoginCredentials(
                seller.emailPassword, LoginCredentials.UserType.SELLER));
        if (sellerExists) {
            return false;
        }
        String query = String.format("INSERT INTO seller VALUES ('%s', '%s', '%s', '%s', %d, '%s')",
                seller.emailPassword.email, seller.emailPassword.pass, seller.fname, seller.lname, seller.zip,
                seller.cuisine);
        System.out.println(query);
        databaseQueryHandler.executeUpdate(query);
        return true;
    }
}
