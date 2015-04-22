package cop4331.model;

/**
 * Factory Class that generates the appropriate class based upon isSeller field
 */
public class UserFactory {

    /**
     * Constructs the proper user object.
     * @param userID The ID of this user.
     * @param fullname The users full name.
     * @param username The users username.
     * @param password The users password.
     * @param streetAddress The users street address.
     * @param city The users city.
     * @param state The users state.
     * @param zip The users zip code.
     * @param isSeller Flags wether or not the UserFactory should return a buyer or a seller.
     * @return The user object of proper type.
     */
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
