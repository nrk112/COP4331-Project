package model;

/**
 * Created by Nick on 3/30/2015.
 */
public class Seller extends UserModel {
    public Seller(String fullName, String username, String password, String streetAddress, String city, String state, String zip, boolean isSeller) {
        super(fullName, username, password, streetAddress, city, state, zip, isSeller);
    }
}
