package model;

/**
 * Created by Nick on 3/30/2015.
 */
public class Buyer extends UserModel {
    public Buyer(String username, String password, String streetAddress, String city, String state, String zip, boolean isSeller) {
        super(username, password, streetAddress, city, state, zip, isSeller);
    }
}
