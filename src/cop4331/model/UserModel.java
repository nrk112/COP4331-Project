package cop4331.model;

/**
 * Represents a user.
 */
public abstract class UserModel implements User {

    private int ID = -1;
    private String fullName = null;
    private String username = null;
    private String password;
    private String streetAddress = null;
    private String city = null;
    private String state = null;
    private String zip = null;
    private boolean isSeller = false;

    /**
     * Saves the values for the user object.
     * @param userID The ID of this user.
     * @param fullName The users full name.
     * @param username The users username.
     * @param password The users password.
     * @param streetAddress The users street address.
     * @param city The users city.
     * @param state The users state.
     * @param zip The users zip code.
     * @param isSeller Flag for seller privileges..
     */
    public UserModel(int userID, String fullName, String username, String password, String streetAddress, String city, String state, String zip, boolean isSeller) {
        this.ID = userID;
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.isSeller = isSeller;
    }

    @Override
    public boolean validateUser(String pw) {
        //TODO If passwords are encrypted we can decrypt here.
        boolean isValidated = false;
        if (this.password.equals(pw)) {
            isValidated = true;
        }
        return isValidated;
    }

    @Override
    public String getFullName() {
        return fullName;
    }

    @Override
    public boolean isSeller() {
        return isSeller;
    }

    @Override
    public String getZip() {
        return zip;
    }

    @Override
    public String getState() {
        return state;
    }

    @Override
    public String getCity() {
        return city;
    }

    @Override
    public String getStreetAddress() {
        return streetAddress;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public int getID() {
        return ID;
    }
}
