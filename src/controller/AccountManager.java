package controller;

import Resources.Common;
import Resources.ProjectConstants;
import model.Buyer;
import model.Seller;
import model.UserFactory;
import model.UserModel;
import view.LoginView;
import view.MarketPlaceView;
import view.SellerListView;
import view.SignupView;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * Handles all functions related to customer registration and is considered the entry point for the program.
 */
public class AccountManager {

    private static final AccountManager INSTANCE = new AccountManager();
    private final List<UserModel> users = new ArrayList<>();

    /**
     * Constructor populates users and shows a login view.
     */
    private AccountManager() {
        readUsersFromFile();
        new LoginView();
    }

    /**
     * Returns the instance of the AccountManger singleton.
     * @return the instance.
     */
    public static AccountManager getInstance() {
        return INSTANCE;
    }

    /**
     * Finds the specified user and logs them in.
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
        writeUsersToFile();
    }

    /**
     * Checks if a particular username is already being used by another customer.
     * @param userName the name to check for
     * @return true if the name exists.
     */
    private boolean userNameExists(String userName) {

        UserModel currentUser;
        Iterator userIter = users.iterator();
        while(userIter.hasNext()) {
            currentUser = (UserModel) userIter.next();
            if (currentUser.getUsername().equals(userName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Create a user, then add it to the list.
     * @param userID
     * @param fullName The users full name.
     * @param username The users name.
     * @param password The users password.
     * @param streetAddress The users street address.
     * @param city The users city.
     * @param state The users state.
     * @param zip The users zip code.
     * @param isSeller True if the user is a seller.
     * @return 
     */
    public boolean createUser(int userID,
                           String fullName,
                           String username,
                           String password,
                           String streetAddress,
                           String city,
                           String state,
                           String zip,
                           boolean isSeller) {
        boolean success = false;
        if (!userNameExists(username)){
            addUser(UserFactory.CreateUser(
                    userID,
                    fullName,
                    username,
                    password,
                    streetAddress,
                    city,
                    state,
                    zip,
                    isSeller));
            success = true;
        }
        return success;
    }

    /**
     * Create a sequential userID.
     * @return the userID.
     */
    public int getNewUserId(){
        int newID;
        if (users.isEmpty()){
            newID = 0;
        } else {
            //Get last userID
            newID = users.get(users.size() - 1).getID();
        }
        return newID + 1;
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

        String[] data = new String[9];
        int index = 0;
        String field;

        try(FileReader fileReader = new FileReader(ProjectConstants.USER_FILE)) {
            //Create the scanner
            Scanner scanner = new Scanner(fileReader);

            //Set the scanner to use commas and any amount of spaces for the delimiter
            scanner.useDelimiter(", *");

            //On each line, loop through each field.
            while (scanner.hasNext()) {
                field = scanner.next();
                data[index] = field;
                index++;

                if (index == 9) {

                    index = 0;

                    //Create the user
                    //AccountManager.getInstance().
                            createUser(
                                    //Convert string to integer and remove return characters.
                                    Integer.parseInt(data[0].replaceAll("[\\r\\n]", "")),    //userID
                                    data[1],    //FullName
                                    data[2],    //userName
                                    data[3],    //password
                                    data[4],    //Street address
                                    data[5],    //city
                                    data[6],    //State
                                    data[7],    //Zip
                                    data[8].equals("true")    //Seller status
                            );
                }
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            Common.resetDatabase();
        } catch (Exception e) { //NumberFormatException or IOException
            e.printStackTrace();
        }
    }

    /**
     * Writes all user data from all users to a text file.
     */
    public void writeUsersToFile() {

        try(final FileWriter fw = new FileWriter(ProjectConstants.USER_FILE)) {
            final PrintWriter pw = new PrintWriter(fw);

            UserModel currentUser;
            //Find the desired user from all current users.
            Iterator userIter = users.iterator();
            while(userIter.hasNext()) {
                currentUser = (UserModel) userIter.next();
                pw.println(
                        currentUser.getID() + "," +
                        currentUser.getFullName() + "," +
                        currentUser.getUsername() + "," +
                        currentUser.getPassword() + "," +
                        currentUser.getStreetAddress() + "," +
                        currentUser.getCity() + "," +
                        currentUser.getState() + "," +
                        currentUser.getZip() + "," +
                        currentUser.isSeller() + ","
                );
            }
            pw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
