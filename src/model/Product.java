package model;

/**
 * Establishes the baseline for products
 */
public interface Product {
    public int productID = -1;
    public int sellerID = -1;
    public String name = null;
    public String description = null;
    public double cost = 0.0;
    public double price = 0.0;
    public int quantity = 0;
    public double getPrice();
    public int getQuantity();
    public int getProductID();
    public int getSellerID();
    public double getCost();
    public String getName();
    public String getDescription();
    public String toString();
}
