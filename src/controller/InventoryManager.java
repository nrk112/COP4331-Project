/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Resources.ProjectConstants;
import model.DiscountedProduct;
import model.Product;
import model.ProductFactory;
import model.Seller;
import view.AddProductView;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Handles all functions related to seller product management. Singleton class.
 */
public class InventoryManager {

    private static final InventoryManager instance = new InventoryManager();
    private final ArrayList<Product> products = new ArrayList<>();

    /**
     * Constructor sets up InventoryManager.
     */
    private InventoryManager() {
        populateProductList();
    }

    /**
     * Returns the instance of the InventoryManager singleton.
     * @return the instance.
     */
    public static InventoryManager getInstance() {
        return instance;
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

    public void editProductByID(int productID, String name, String description, double cost, double price, int quantity, double discountedBy, String image) {

        for (Product product : products) {
            if (product.getProductID() == productID) {
                product.setImage(image);
                product.setQuantity(quantity);
                product.setDescription(description);
                product.setName(name);
                product.setCost(cost);
                product.setPrice(price);
                if (product instanceof DiscountedProduct) {
                    ((DiscountedProduct)product).setDiscountedBy(discountedBy);
                }
            }
        }
        writeProductsToFile();
    }

    /**
     * Decrement the quantity of a particular product.
     * @param productID The ID of the product sold
     * @param qtySold How many of product were sold
     * @postcondition Must call writeProductsToFile() after updating all quantities.
     */
    public void decrementQuantityByID(int productID, int qtySold) {

        for (Product currentProduct : products) {
            if (currentProduct.getProductID() == productID) {
                int newQty = currentProduct.getQuantity() - qtySold;
                currentProduct.setQuantity(newQty);
            }
        }
        writeProductsToFile();
    }


    /**
     * Gets a specific product by its ID
     * @param ID the ID of the product to get
     * @return the product
     */
    public Product getProductByID(int ID) {
        for (Product product : products) {
            if (product.getProductID() == ID) {
                return product;
            }
        }
        return null;
    }


   /**
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
                    String regex = "[\\r\\n]";
                    this.createProduct(
                            //Convert string to appropriate types and remove return characters.
                            Integer.parseInt(data[0].replaceAll(regex, "")),   //ProductID
                            Integer.parseInt(data[1].replaceAll(regex, "")),   //SellerID
                            data[2],                                                //Name
                            data[3],                                                //Description
                            Double.parseDouble(data[4].replaceAll(regex, "")), //Cost
                            Double.parseDouble(data[5].replaceAll(regex, "")), //Price
                            Integer.parseInt(data[6].replaceAll(regex, "")),   //Quantity
                            Double.parseDouble(data[7].replaceAll(regex, "")),  //DiscountedBy
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
        if(products.size()> 0) {
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
            writeProductsToFile();
        }
    }

    public void AddProductView(Seller user) {
        new AddProductView(user);
    }

}
