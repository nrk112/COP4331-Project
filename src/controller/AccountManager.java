package controller;

import Resources.ProjectConstants;
import model.UserModel;
import view.LoginView;
import view.SignupView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Handles all functions related to customer registration. Singleton class.
 */
public class AccountManager {

    private static final AccountManager instance = new AccountManager();
    private static List<UserModel> users = new ArrayList<>();

    private AccountManager() {
        users = new ArrayList<>();
        readUsersFromFile();
        new LoginView();
    }

    /**
     * Returns the instance of the AccountManger singleton.
     * @return the instance.
     */
    public static AccountManager getInstance() {
        return instance;
    }

    /**
     * Finds the specified user and logs them in.
     * THIS FUNCTION IS SHOWN AS loginClicked() on the diagrams.
     * @param userName The userName of the user to authorize.
     * @param password The password of the user to authorize.
     * @return true if successful.
     */
    public boolean authorizeUser(String userName, String password) {
        boolean isAuthorized = false;

        //Find the desired user from all current users.
        UserModel currentUser = null;

        Iterator userIter = users.iterator();
        while(userIter.hasNext()) {
            currentUser = (UserModel) userIter.next();
            if (currentUser.getUsername().equals(userName)) {
                break;
            } else {
                return isAuthorized;
            }
        }

        //TODO authorize the user this is not yet working
        //isAuthorized = currentUser.validateUser(password);

        return isAuthorized;
    }

    public void signupClicked() {
        new SignupView();
    }

    /**
     * Populates AccountManager with all available users.
     */
    private static void readUsersFromFile() {
        //TODO figure out how to read files and what format we want it in.

        try (BufferedReader br = new BufferedReader(new FileReader(ProjectConstants.USER_FILE))) {
            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                System.out.println(sCurrentLine);
            }

        } catch (FileNotFoundException e) {
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
