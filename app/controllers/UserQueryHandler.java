package controllers;

import models.Seller;
import models.User;

/**
 * Created by soumya on 5/13/15.
 */
public class UserQueryHandler {
    public Seller getSellerForUser(User user) {
        System.out.println(user.age + ", " + user.userName);
        return new Seller("company", "1000 kiely blvd", 100);
    }
}
