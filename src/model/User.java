package model;

/**
 * Created by Nick on 3/30/2015.
 */
public interface User {

    public int ID = -1;
    public String fullName = null;
    public String username = null;
    public String password = null;
    public String streetAddress = null;
    public String city = null;
    public String state = null;
    public String zip = null;
    public boolean isSeller = false;
    public boolean validateUser(String pw);

}
