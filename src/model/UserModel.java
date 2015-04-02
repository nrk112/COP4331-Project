package model;

/**
 *
 */
public class UserModel {

    private String name;

    public UserModel() {
    }

    public String getName() {
        return this.name;
    }

    public boolean validateUser(String password) {
        //TODO If passwords are encrypted we can decrypt here.
        return false;
    }

}
