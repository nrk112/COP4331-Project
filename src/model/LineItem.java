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
    public int lineItemID = -1;
    public int productID = -1;
    public int sellerID = -1;
    public int buyerID = -1;
    public String name = null;
    public double cost = 0.0;
    public double price = 0.0;
    public int quantity = 0;
    public double getPrice();
    public int getQuantity();
    public int getProductID();
    public int getSellerID();
    public int getBuyerID();
    public int getLineItemID();
    public double getCost();
    public String getName();
    public String toString();
}
