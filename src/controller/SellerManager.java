/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Resources.ProjectConstants;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import model.Product;
import model.UserModel;

/**
 * Handles all functions related to seller product management. Singleton class.
 */
public class SellerManager {
    private static final SellerManager instance = new SellerManager();
    private ArrayList<Product> productList;

    private SellerManager() {
        populateProductList();

    }

    public SellerManager getInstance() {
        return instance;
    }

    public ArrayList getProductList() {
        return productList;
    }

    private void populateProductList() {
        //TODO populate the product list here.
        productList = new ArrayList<>();
    }
    
    /**
     * Populates AccountManager with all available users.
     */
    public void readProductsFromFile() {

//        String[] data = new String[9];
//        int index = 0;
//        String field;
//
//        try(FileReader fileReader = new FileReader(ProjectConstants.PRODUCT_FILE)) {
//            //Create the scanner
//            Scanner scanner = new Scanner(fileReader);
//
//            //Set the scanner to use commas and any amount of spaces for the delimiter
//            scanner.useDelimiter(", *");
//
//            //On each line, loop through each field.
//            while (scanner.hasNext()) {
//                field = scanner.next();
//                data[index] = field;
//                index++;
//
//                if (index == 9) {
//
//                    index = 0;
//
//                    //Create the user
//                    AccountManager.getInstance().createUser(
//                            //Convert string to integer and remove return characters.
//                            Integer.parseInt(data[0].replaceAll("[\\r\\n]", "")),    //userID
//                            data[1],    //FullName
//                            data[2],    //userName
//                            data[3],    //password
//                            data[4],    //Street address
//                            data[5],    //city
//                            data[6],    //State
//                            data[7],    //Zip
//                            data[8].equals("true")    //Seller status
//                    );
//                }
//            }
//            fileReader.close();
//        } catch (Exception e) { //NumberFormatException or IOException
//            e.printStackTrace();
//        }
    }

    /**
     * Writes all user data from all users to a text file.
     */
    public void writeProductsToFile() {

//        try(final FileWriter fw = new FileWriter(ProjectConstants.USER_FILE)) {
//            final PrintWriter pw = new PrintWriter(fw);
//
//            UserModel currentUser;
//            //Find the desired user from all current users.
//            Iterator userIter = users.iterator();
//            while(userIter.hasNext()) {
//                currentUser = (UserModel) userIter.next();
//                pw.println(
//                        currentUser.getID() + "," +
//                        currentUser.getFullName() + "," +
//                        currentUser.getUsername() + "," +
//                        currentUser.getPassword() + "," +
//                        currentUser.getStreetAddress() + "," +
//                        currentUser.getCity() + "," +
//                        currentUser.getState() + "," +
//                        currentUser.getZip() + "," +
//                        currentUser.getIsSeller() + ","
//                );
//            }
//            pw.close();
//            fw.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
    
}
