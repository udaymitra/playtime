package models;

import org.hibernate.validator.constraints.Email;

/**
 * Created by soumya on 5/13/15.
 */
public class Seller {
    public EmailPassword emailPassword;
    public String fname;
    public String lname;
    public Integer zip;
    public String cuisine;

    public Seller() {
    }

    public Seller(EmailPassword emailPassword, String fname, String lname, Integer zip, String cuisine) {
        this.emailPassword = emailPassword;
        this.fname = fname;
        this.lname = lname;
        this.zip = zip;
        this.cuisine = cuisine;
    }
}
