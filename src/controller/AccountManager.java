package controller;

import model.Customer;
import view.SignupView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Nick on 3/30/2015.
 */
public class AccountManager {

    private static List<Customer> users = new ArrayList<>();

    public AccountManager() {
        //TODO we have to make this class a singleton. Also we should start LoginView from here and the main should start this.
        users = new ArrayList<>();
        readUsersFromFile();
    }

    /**
     * Finds the specified user and logs them in.
     * THIS FUNCTION IS SHOWN AS loginClicked() on the diagrams.
     * @param userName The userName of the user to authorize.
     * @param password The password of the user to authorize.
     * @return true if successful.
     */
    public static boolean authorizeUser(String userName, String password) {
        boolean isAuthorized = false;

        //Find the desired user from all current users.
        Customer currentUser = null;

        Iterator userIter = users.iterator();
        while(userIter.hasNext()) {
            currentUser = (Customer) userIter.next();
            if (currentUser.getName().equals(userName)) {
                break;
            } else {
                return isAuthorized;
            }
        }

        //TODO authorize the user this is not yet working
        //isAuthorized = currentUser.validateUser(password);

        return isAuthorized;
    }

    public static void signupClicked() {

        SignupView signupView = new SignupView();

    }

    /**
     * Populates this object with all available users.
     */
    private static void readUsersFromFile() {
        //TODO figure out how to read files and what format we want it in.
    }


}
