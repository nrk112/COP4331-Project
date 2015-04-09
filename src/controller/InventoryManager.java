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
import model.DiscountedProduct;
import model.LineItem;
import model.Product;
import model.ProductFactory;
import model.Seller;
import view.AddProductView;

/**
 * Handles all functions related to seller product management. Singleton class.
 */
public class InventoryManager {
    private static final InventoryManager instance = new InventoryManager();
    private final ArrayList<Product> products = new ArrayList<>();
    private final ArrayList<LineItem> lineItems = new ArrayList<>();

    private InventoryManager() {
    }
    
    public void GetSellerData()
    {                
        populateProductList();   
        TransactionManager.getInstance();
        TransactionManager.getInstance().getTransactionData();
    }
    
    /**
     * Create a product, then add it to the list.
     * @param productID The product id.
     * @param sellerID The seller id.
     * @param name The product name.
     * @param description The product description.
     * @param cost The product cost.
     * @param price The product price.
     * @param quantity The number of products for sale.
     * @param discountedBy the discount amount as percentage.
     */
    public void createProduct(int productID, int sellerID, String name, String description, double cost, double price, int quantity, double discountedBy, String image) {
        Product product = ProductFactory.CreateProduct(productID,sellerID, name, description, cost, price, quantity, discountedBy, image);         
        addProduct(product);
    }
    
    /**
     * Returns the instance of the InventoryManager singleton.
     * @return the instance.
     */
    public static InventoryManager getInstance() {
        return instance;
    }

   /*
    *  Get product list
    * @return the product ArrayList.
    */
    public ArrayList getProductList() {
        return products;
    }
    /**
     * Create a sequential productID.
     * @return the productID.
     */
    public int getProductId(){
         int newID;
         if (products.isEmpty()){
             newID = 0;
         } else {
             //Get last userID
             newID = products.get(products.size() - 1).getProductID();
         }
         return newID + 1;
     } 
    /*
    *  Get product list
    * @return the product ArrayList.
    */
    public ArrayList getTransactionLineItemList() {
        return lineItems;
    }
    /**
     * Populates InventoryManager with all available products by reading from text file.
     */
    public void readProductsFromFile() {

        String[] data = new String[9];
        int index = 0;
        String field;

        try(FileReader fileReader = new FileReader(ProjectConstants.PRODUCT_FILE)) {
            //Create the scanner
            Scanner scanner = new Scanner(fileReader);

            //Set the scanner to use commas and any amount of spaces for the delimiter
            scanner.useDelimiter("~");

            //On each line, loop through each field.
            while (scanner.hasNext()) {
                field = scanner.next();
                data[index] = field;
                index++;

                if (index == 9) {

                    index = 0;

                    //Create product
                    InventoryManager.getInstance().createProduct(
                            //Convert string to appropriate types and remove return characters.
                            Integer.parseInt(data[0].replaceAll("[\\r\\n]", "")),   //ProductID
                            Integer.parseInt(data[1].replaceAll("[\\r\\n]", "")),   //SellerID
                            data[2],                                                //Name
                            data[3],                                                //Description
                            Double.parseDouble(data[4].replaceAll("[\\r\\n]", "")), //Cost
                            Double.parseDouble(data[5].replaceAll("[\\r\\n]", "")), //Price
                            Integer.parseInt(data[6].replaceAll("[\\r\\n]", "")),   //Quantity
                            Double.parseDouble(data[7].replaceAll("[\\r\\n]", "")),  //DiscountedBy
                            data[8]                                                //Image
                    );
                }
            }
            fileReader.close();
        } catch (Exception e) { //NumberFormatException or IOException
            e.printStackTrace();
        }
    }

    /**
     * Writes all product data from all products to a text file.
     */
    public void writeProductsToFile() {

        try(final FileWriter fw = new FileWriter(ProjectConstants.PRODUCT_FILE, false)) {
            final PrintWriter pw = new PrintWriter(fw);

            Product currentProduct;
            //Write each product to a products file.
            Iterator productIter = getProductList().iterator();
            while(productIter.hasNext()) {
                currentProduct = (Product) productIter.next();
                double discountedBy = 0;
                if(currentProduct instanceof DiscountedProduct)
                {
                    discountedBy = ((DiscountedProduct)currentProduct).getDiscountedBy();
                }
                pw.println(
                        currentProduct.getProductID() + "~" +
                        currentProduct.getSellerID() + "~" +
                        currentProduct.getName() + "~" +
                        currentProduct.getDescription() + "~" +
                        currentProduct.getCost() + "~" +
                        currentProduct.getPrice() + "~" +
                        currentProduct.getQuantity() + "~" +
                        discountedBy + "~" +
                        currentProduct.getImage() + "~"
                        
                );
            }
            pw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        
    /**
     * Populates the product list.     * 
     */
    private void populateProductList() {
        if(getProductList().size()> 0)
        {
            products.clear();
        }        
        readProductsFromFile();
    }
    /**
     * Add a product to the list.
     * @param product the product to add
     */
    private void addProduct(Product product) {
        
        if(!products.contains(product))  
        {
            products.add(product);
        }
    }

    public void AddProductView(Seller user) {
        new AddProductView(user);
    }
    
     public void readTransactionsFromFile() {

       TransactionManager.getInstance().readTransactionsFromFile();
    }
    
}