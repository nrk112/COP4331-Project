package model;

/**
 *
 */
public class UserFactory {

    public static UserModel CreateUser(String fullname, String username, String password, String streetAddress, String city, String state, String zip, boolean isSeller) {
        if (!isSeller) {
            Buyer user = new Buyer(
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
