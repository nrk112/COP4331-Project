package model;

/**
 * Factory Class that generates the appropriate class based upon isSeller field
 */
public class UserFactory {

    public static UserModel CreateUser(int userID, String fullname, String username, String password, String streetAddress, String city, String state, String zip, boolean isSeller) {
        if (!isSeller) {
            Buyer user = new Buyer(
                    userID,
		    fullname,
                    username,
                    password,
                    streetAddress,
                    city,
                    state,
                    zip,
                    isSeller
            );
            return user;
        } else {
            Seller user = new Seller(
                    userID,
                    fullname,
                    username,
                    password,
                    streetAddress,
                    city,
                    state,
                    zip,
                    isSeller
            );
            return user;
        }
    }

}
