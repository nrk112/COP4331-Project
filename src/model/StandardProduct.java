/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * abstract class that defines common methods
 */
public class StandardProduct implements Product{

    private int productID = -1;
    private int sellerID = -1;
    private String name = null;
    private String description = null;
    private double cost = 0.0;
    private double price = 0.0;
    private int quantity = 0;
    
    public StandardProduct(int productID, int sellerID, String name, String description, double cost, double price, int quantity)
    {
        this.cost=cost;
        this.description = description;
        this.name = name;
        this.price = price;
        this.productID = productID;
        this.quantity = quantity;
        this.sellerID = sellerID;
    }
    
    public double getPrice() {
        return price;
    }
    public double getCurrentPrice(){
        return getPrice();
    }       
    public int getQuantity()
    {
        return quantity;
    }
    public int getProductID()
    {
        return productID;
    }
    public int getSellerID()
    {
        return sellerID;
    }
    public double getCost()
    {
        return cost;
    }
    public String getName()
    {
        return name;
    }
    public String getDescription()
    {
        return description;
    }
    
    public String toString()
    {
        return name + ": "+ description;
    }
    
}
