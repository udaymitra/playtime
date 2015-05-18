package models;

/**
 * Created by soumya on 5/13/15.
 */
public class Seller {
    public String companyName;
    public String address;
    public Integer price;

    public Seller(String companyName) {
        this.companyName = companyName;
    }

    public Seller(String companyName, String address, Integer price) {
        this.companyName = companyName;
        this.address = address;
        this.price = price;
    }
}
