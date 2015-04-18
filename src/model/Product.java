package model;

/**
 * Establishes the baseline for products
 */
public interface Product {
    int productID = -1;
    int sellerID = -1;
    String name = null;
    String description = null;
    double cost = 0.0;
    double price = 0.0;
    int quantity = 0;
    String image=null;
    double getPrice();
    double getCurrentPrice();
    int getQuantity();
    int getProductID();
    int getSellerID();
    double getCost();
    String getName();
    String getDescription();
    String getImage();
    void setPrice(double price);
    void setQuantity(int qty);
    void setCost(double cost);
    void setName(String name);
    void setDescription(String description);
    void setImage(String fileName);

    String toString();
    boolean equals(Object other);
    
}