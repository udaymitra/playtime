package models;

/**
 * Created by soumya on 5/27/15.
 */
public class LoginCredentials {
    public EmailPassword emailPassword;
    public UserType userType;

    public LoginCredentials(EmailPassword emailPassword, UserType userType) {
        this.emailPassword = emailPassword;
        this.userType = userType;
    }

    public static enum UserType {
        SELLER, BUYER
    };
}
