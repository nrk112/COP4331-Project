/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cop4331.model;

/**
 * Line item interface is the basis for completed transactions.
 */
public interface LineItem 
{
    /**
     * the ID of the item.
     */
    int lineItemID = -1;

    /**
     * The ID of the product associated with the item.
     */
    int productID = -1;

    /**
     * The ID of the seller associated with the item.
     */
    int sellerID = -1;

    /**
     * The ID of the buyer associated with the item.
     */
    int buyerID = -1;

    /**
     * The name of the item.
     */
    String name = null;

    /**
     * The sellers cost of the item.
     */
    double cost = 0.0;

    /**
     * The price of the item.
     */
    double price = 0.0;

    /**
     * The quantity purchased of the item.
     */
    int quantity = 0;

    /**
     * Gets the MSRP price of the item.
     * @return the price.
     */
    double getPrice();

    /**
     * Gets the quantity associated with the item.
     * @return the quantity.
     */
    int getQuantity();

    /**
     * Gets the product ID associated with the item.
     * @return The productID
     */
    int getProductID();

    /**
     * Gets the ID of the seller associated with the item.
     * @return the seller ID.
     */
    int getSellerID();

    /**
     * Gets the ID of the buyer associated with the item.
     * @return the buyers ID.
     */
    int getBuyerID();

    /**
     * Gets the ID of this line item.
     * @return the ID.
     */
    int getLineItemID();

    /**
     * gets the sellers cost of the item.
     * @return the cost.
     */
    double getCost();

    /**
     * Gets the name of the item.
     * @return the name.
     */
    String getName();

    @Override
    String toString();
}
