package model;

/**
 *
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

    public UserModel(String fullName, String username, String password, String streetAddress, String city, String state, String zip, boolean isSeller) {
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

    public String getFullName() {
        return fullName;
    }

    public boolean isSeller() {
        return isSeller;
    }

    public String getZip() {
        return zip;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public int getID() {
        return ID;
    }
}
