package controller;

import Resources.ProjectConstants;
import model.Buyer;
import model.Seller;
import model.UserModel;
import view.LoginView;
import view.MarketPlaceView;
import view.SellerListView;
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

        UserModel currentUser;
        //Find the desired user from all current users.
        Iterator userIter = users.iterator();
        while(userIter.hasNext()) {
            currentUser = (UserModel) userIter.next();
            if (currentUser.getUsername().equals(userName) && currentUser.validateUser(password)) {
                if (currentUser.isSeller()) {
                    Seller seller = (Seller) currentUser;
                    new SellerListView(seller);
                } else {
                    Buyer buyer = (Buyer) currentUser;
                    new MarketPlaceView(buyer);
                }
                isAuthorized = true;
                break;
            }
        }
        return isAuthorized;
    }

    /**
     * Add a user to the list.
     * @param user the user to add
     */
    private void addUser(UserModel user) {
        users.add(user);
    }

    /**
     * Create a user, then add it to the list.
     * @param username The users name.
     * @param password The users password.
     * @param streetAddress The users street address.
     * @param city The users city.
     * @param state The users state.
     * @param zip The users zip code.
     * @param isSeller True if the user is a seller.
     */
    public void createUser(String fullName, String username, String password, String streetAddress, String city, String state, String zip, boolean isSeller) {
        //Check if they are registering as a buyer or seller.
        if (!isSeller) {
            Buyer user = new Buyer(
                    fullName,
                    username,
                    password,
                    streetAddress,
                    city,
                    state,
                    zip,
                    isSeller
            );
            AccountManager.getInstance().addUser(user);
        } else {
            Seller user = new Seller(
                    fullName,
                    username,
                    password,
                    streetAddress,
                    city,
                    state,
                    zip,
                    isSeller
            );
            AccountManager.getInstance().addUser(user);
        }
    }

    /**
     *Loads the signup view page for a user to register.
     */
    public void signupClicked() {
        new SignupView();
    }

    /**
     * Populates AccountManager with all available users.
     */
    public void readUsersFromFile() {
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

    public void writeUsersToFile() {

    }
}
