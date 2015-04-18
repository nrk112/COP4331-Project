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
 *
 */
public class TransactionManager {
    
    private static final TransactionManager instance = new TransactionManager();
    private final ArrayList<TransactionLineItem> lineItems = new ArrayList<>();

    private TransactionManager() {
        populateTransactionList();
    }

    /**
     * Returns the instance of the SellerManager singleton.
     * @return the instance.
     */
    public static TransactionManager getInstance() {
        return instance;
    }
    
   /**
    *  Get product list
    * @return the product ArrayList.
    */
    public ArrayList getTransactionLineItemList() {
        return lineItems;
    }
    /**
     * Create a sequential productID.
     * @return the productID.
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
    
    /*
    
    */
    public void BuyNow(Buyer user) {
       
        Product currentProduct;
        //Create a transaction line item per product for this user and add to transactionlineitem list
        Iterator productIter = user.getShoppingCart().iterator();
        while(productIter.hasNext()) {
             currentProduct = (Product) productIter.next();
             createTransactionLineItem(getTransactionLineItemId(), currentProduct.getProductID(), currentProduct.getSellerID(),
                                                            user.getID(), currentProduct.getName(), currentProduct.getCost(), 
                                                            currentProduct.getPrice(), 1); 
        }

    }
    
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

                    //Create product
                    this.createTransactionLineItem(
                            //Convert string to appropriate types and remove return characters.
                            Integer.parseInt(data[0].replaceAll("[\\r\\n]", "")),   //TransactionLineItemID
                            Integer.parseInt(data[1].replaceAll("[\\r\\n]", "")),   //ProductID
                            Integer.parseInt(data[2].replaceAll("[\\r\\n]", "")),   //SellerID
                            Integer.parseInt(data[3].replaceAll("[\\r\\n]", "")),   //UserID
                            data[4],                                                //Name
                            Double.parseDouble(data[5].replaceAll("[\\r\\n]", "")), //Cost
                            Double.parseDouble(data[6].replaceAll("[\\r\\n]", "")), //Price
                            Integer.parseInt(data[7].replaceAll("[\\r\\n]", ""))   //Quantity
                    );
                }
            }
            fileReader.close();
        } catch (Exception e) { //NumberFormatException or IOException
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

     
    private TransactionLineItem createLineItem(int transactionLineItemID, int productID, int sellerID, int buyerID, String name, double cost, double price, int quantity) {
        
        return new TransactionLineItem(transactionLineItemID, productID, sellerID, buyerID, name, cost, price, quantity);
        
    }


    private void populateTransactionList() {
        if(getTransactionLineItemList().size()> 0) {
            lineItems.clear();
        }        
        readTransactionsFromFile();
    }


    private void addTransactionLineitem(TransactionLineItem lineItem) {
            lineItems.add(lineItem);
    }

}
