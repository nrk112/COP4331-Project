/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * Holds the Shopping Cart item for recording purposes
 */
public class TransactionLineItem implements LineItem{

    private int lineItemID = -1;
    private int productID = -1;
    private int sellerID = -1;
    private int buyerID = -1;
    private String name = null;
    private double cost = 0.0;
    private double price = 0.0;
    private int quantity = 0;

    public TransactionLineItem(int transactionLineItemId, int productID, int sellerID, int buyerID, String name, double cost, double price, int quantity) {
        this.lineItemID     = transactionLineItemId;
        this.productID      = productID;
        this.sellerID       = sellerID;
        this.buyerID        = buyerID;
        this.name           = name;
        this.cost           = cost;
        this.price          = price;
        this.quantity       = quantity;
    }
    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public int getProductID() {
        return productID;
    }

    @Override
    public int getSellerID() {
        return sellerID;
    }

    @Override
    public double getCost() {
        return cost;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getBuyerID() {
        return buyerID;
    }

    @Override
    public int getLineItemID() {
        return lineItemID;
    }
    
}
