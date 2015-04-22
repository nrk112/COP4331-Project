package model;

/**
 * Establishes the baseline for products
 */
public interface Product {

    /**
     * The productID
     */
    int productID = -1;

    /**
     * The ID of the seller associated with thie product.
     */
    int sellerID = -1;

    /**
     * The name of the product.
     */
    String name = null;

    /**
     * The description of the product.
     */
    String description = null;

    /**
     * The sellers cost of the product.
     */
    double cost = 0.0;

    /**
     * The price of the product.
     */
    double price = 0.0;

    /**
     * The quantity of the product.
     */
    int quantity = 0;

    /**
     * The fully qualified filename of the image of product.
     */
    String image=null;

    /**
     * Gets the price of the product.
     * @return the price
     */
    double getPrice();

    /**
     * Gets the current price after any modifications to the product.
     * @return the price.
     */
    double getCurrentPrice();

    /**
     * Gets the quantity available of the product.
     * @return the quantity.
     */
    int getQuantity();

    /**
     * Gets the ID of the product.
     * @return the ID>
     */
    int getProductID();

    /**
     * Gets the ID of the seller associated with the product.
     */
    int getSellerID();

    /**
     * Gets the sellers cost fo teh product.
     * @return the cost.
     */
    double getCost();

    /**
     * Gets the name of the product.
     * @return the name.
     */
    String getName();

    /**
     * Gets the description of hte product.
     * @return the description.
     */
    String getDescription();

    /**
     * Gets the fully quaified filename of the products image.
     * @return the the filename.
     */
    String getImage();

    /**
     * Sets the price.
     * @param price the price.
     */
    void setPrice(double price);

    /**
     * Sets the quantity.
     * @param qty the quantity.
     */
    void setQuantity(int qty);

    /**
     * Sets the sellers cost.
     * @param cost the cost.
     */
    void setCost(double cost);

    /**
     * Sets the name.
     * @param name The name.
     */
    void setName(String name);

    /**
     * Sets the description.
     * @param description The description.
     */
    void setDescription(String description);

    /**
     * Sets the filename.
     * @param fileName the filename.
     */
    void setImage(String fileName);

    @Override
    String toString();

    @Override
    boolean equals(Object other);
}