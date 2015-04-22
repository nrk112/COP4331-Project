package cop4331.model;

/**
 * The basis for any type of user.
 */
public interface User {

    /**
     * The users ID
     */
    int ID = -1;

    /**
     * The users full name.
     */
    String fullName = null;

    /**
     * The users userName.
     */
    String username = null;

    /**
     * The users password.
     */
    String password = null;

    /**
     * The users street address.
     */
    String streetAddress = null;

    /**
     * The users city.
     */
    String city = null;

    /**
     * The users state.
     */
    String state = null;

    /**
     *The users zip code.
     */
    String zip = null;

    /**
     * boolean flag determining what object to create.
     */
    boolean isSeller = false;

    /**
     * Verifies the the login password matches the stored password.
     * @param pw The login password.
     * @return True if validated.
     */
    boolean validateUser(String pw);
    String getFullName();

    /**
     * Gets the flag for seller object.
     * @return the flag.
     */
    boolean isSeller();

    /**
     * Gets the zip code of the user.
     * @return the zip code.
     */
    String getZip();

    /**
     * Gets the state the user resides in.
     * @return the state.
     */
    String getState();

    /**
     * Gets the city the user resides in.
     * @return the city.
     */
    String getCity();

    /**
     * Getst he street address of the user.
     * @return the street address.
     */
    String getStreetAddress();

    /**
     * Gets the password of the user.
     * @return the password.
     */
    String getPassword();

    /**
     * Gets the userName of the user.
     * @return The username.
     */
    String getUsername();

    /**
     * Gets the ID of the user.
     * @return the ID.
     */
    int getID();
}
