/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Lenore
 */
public interface LineItem 
{
    int lineItemID = -1;
    int productID = -1;
    int sellerID = -1;
    int buyerID = -1;
    String name = null;
    double cost = 0.0;
    double price = 0.0;
    int quantity = 0;
    double getPrice();
    int getQuantity();
    int getProductID();
    int getSellerID();
    int getBuyerID();
    int getLineItemID();
    double getCost();
    String getName();
    String toString();
}
