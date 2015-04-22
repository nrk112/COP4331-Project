/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Resources.ProjectConstants;
import model.Buyer;
import model.Product;
import model.TransactionLineItem;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 *Handles functions related with purchase transactions.
 */
public class TransactionManager {

    private static final TransactionManager instance = new TransactionManager();
    private final ArrayList<TransactionLineItem> lineItems = new ArrayList<>();

    /**
     * Constructor will call the function to populate itself with all transactions.
     */
    private TransactionManager() {
        populateTransactionList();
    }

    /**
     * Returns the instance of the TransactionManager singleton.
     * @return the instance.
     */
    public static TransactionManager getInstance() {
        return instance;
    }

    /**
     * Gets the transaction list.
     * @return the list of transactions.
     */
    public ArrayList getTransactionLineItemList() {
        return lineItems;
    }

    /**
     * Create a sequential productID.
     * @return the productID.
     * @precondition transactions can not be removed or deleted for the new ID to be valid.
     */
    public int getTransactionLineItemId(){
        int newID;
        if (lineItems.isEmpty()){
            newID = 0;
        } else {
            //Get last userID
            newID = lineItems.get(lineItems.size() - 1).getLineItemID();
        }
        return newID + 1;
    }

    /**
     * Create a product, then add it to the list.
     * @param transactionLineItemID The transaction line item I\id.
     * @param productID The product id.
     * @param sellerID The seller id.
     * @param buyerID the buyer id.
     * @param name The product name.
     * @param cost The product cost.
     * @param price The product price.
     * @param quantity The number of products for sale.
     */
    public void createTransactionLineItem(int transactionLineItemID, int productID, int sellerID, int buyerID, String name, double cost, double price, int quantity) {
        TransactionLineItem lineItem = createLineItem( transactionLineItemID, productID, sellerID, buyerID, name, cost, price, quantity);
        addTransactionLineitem(lineItem);
    }


    /**
     * Takes the users shopping cart and creates transactions for each object, then saves them to the database.
     * @param user The user that has a shopping cart attached.
     */
    public void buyNow(Buyer user) {

        Product currentProduct;
        int productQty = 1;
        //Create a transaction line item per product for this user and add to transactionlineitem list
        Iterator productIter = user.getShoppingCart().iterator();
        while(productIter.hasNext()) {
            currentProduct = (Product) productIter.next();
            createTransactionLineItem(getTransactionLineItemId(), currentProduct.getProductID(), currentProduct.getSellerID(),
                    user.getID(), currentProduct.getName(), currentProduct.getCost(),
                    currentProduct.getPrice(), productQty);
            //Added to decrement the actual inventory.
            InventoryManager.getInstance().decrementQuantityByID(currentProduct.getProductID(), productQty);
        }
        InventoryManager.getInstance().writeProductsToFile();
        writeTransactionsToFile();
    }

    /**
     * Reads the transactions file and populates the transaction ArrayList.
     */
    public void readTransactionsFromFile() {

        String[] data = new String[8];
        int index = 0;
        String field;

        try(FileReader fileReader = new FileReader(ProjectConstants.TRANSACTION_FILE)) {
            //Create the scanner
            Scanner scanner = new Scanner(fileReader);

            //Set the scanner to use commas and any amount of spaces for the delimiter
            scanner.useDelimiter("~");

            //On each line, loop through each field.
            while (scanner.hasNext()) {
                field = scanner.next();
                data[index] = field;
                index++;

                if (index == 8) {

                    index = 0;
                    String regex = "[\\r\\n]"; //Regex to replace the return characters at the end of each line.
                    //Create product
                    this.createTransactionLineItem(
                            //Convert string to appropriate types and remove return characters.
                            Integer.parseInt(data[0].replaceAll(regex, "")),   //TransactionLineItemID
                            Integer.parseInt(data[1].replaceAll(regex, "")),   //ProductID
                            Integer.parseInt(data[2].replaceAll(regex, "")),   //SellerID
                            Integer.parseInt(data[3].replaceAll(regex, "")),   //UserID
                            data[4],                                           //Name
                            Double.parseDouble(data[5].replaceAll(regex, "")), //Cost
                            Double.parseDouble(data[6].replaceAll(regex, "")), //Price
                            Integer.parseInt(data[7].replaceAll(regex, ""))    //Quantity
                    );
                }
            }
            fileReader.close();
        } catch (Exception e) { //Possible NumberFormatException or IOException
            e.printStackTrace();
        }
    }

    /**
     * Writes all transaction line item data from transaction line item list to a text file.
     */
    public void writeTransactionsToFile() {

        try(final FileWriter fw = new FileWriter(ProjectConstants.TRANSACTION_FILE, false)) {
            final PrintWriter pw = new PrintWriter(fw);

            TransactionLineItem currentLineItem;
            //Write each product to a products file.
            Iterator lineItemIter = getTransactionLineItemList().iterator();
            while(lineItemIter.hasNext()) {
                currentLineItem = (TransactionLineItem) lineItemIter.next();
                pw.println(
                        currentLineItem.getLineItemID()+ "~" +
                                currentLineItem.getProductID() + "~" +
                                currentLineItem.getSellerID() + "~" +
                                currentLineItem.getBuyerID() + "~" +
                                currentLineItem.getName() + "~" +
                                currentLineItem.getCost() + "~" +
                                currentLineItem.getPrice() + "~" +
                                currentLineItem.getQuantity() + "~"
                );
            }
            pw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a single transaction line item used to record a transaction.
     * @param transactionLineItemID The ID.
     * @param productID The ID of the product associated with the transaction.
     * @param sellerID The ID of the seller associated with the transaction.
     * @param buyerID The ID of the Buyer associated with the transaction.
     * @param name The name of the product.
     * @param cost The original cost of the product.
     * @param price The price the item was sold for.
     * @param quantity The number of products purchased.
     * @return The constructed transaction line item.
     * @precondition Quantity should always be 1.
     */
    private TransactionLineItem createLineItem(int transactionLineItemID, int productID, int sellerID, int buyerID, String name, double cost, double price, int quantity) {

        return new TransactionLineItem(transactionLineItemID, productID, sellerID, buyerID, name, cost, price, quantity);

    }


    /**
     * Clears and populates the master list of transactions.
     */
    private void populateTransactionList() {
        if(getTransactionLineItemList().size()> 0) {
            lineItems.clear();
        }
        readTransactionsFromFile();
    }


    /**
     * Adds a single transactionLineItem to the list of transactions.
     * @param lineItem The lineItem to add.
     */
    private void addTransactionLineitem(TransactionLineItem lineItem) {
        lineItems.add(lineItem);
    }

}
