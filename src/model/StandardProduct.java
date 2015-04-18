/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Comparator;

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
    private int quantity;
    private String image = null;
    
    public StandardProduct(int productID, int sellerID, String name, String description, double cost, double price, int quantity, String image)
    {
        this.cost=cost;
        this.description = description;
        this.name = name;
        this.price = price;
        this.productID = productID;
        this.quantity = quantity;
        this.sellerID = sellerID;
        this.image = image;
    }

    public String toString()
    {
        return description;
    }
    
    public static Comparator<Product> SortByName()
    {
       Comparator comp = new Comparator<Product>()
       {
            @Override
            public int compare(Product s1, Product s2)
            {
                return s1.name.compareTo(s2.name);
            }        
        };
    return comp;
    }
    
    @Override
    public boolean equals(Object other)
    {
        if((other.getClass() != this.getClass())) return false;
        StandardProduct item = (StandardProduct) other;
        return( 
                item.getCost() == this.getCost() &&
                item.getPrice() == this.getPrice() &&
                item.getProductID() == this.getProductID() &&
                item.getSellerID() == this.getSellerID() &&
                item.getName().contentEquals(this.getName()))&&
                item.getDescription().contentEquals(this.getDescription()) &&
                item.getImage().contentEquals(this.getImage()
           );        
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
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public double getCost() {
        return cost;
    }

    @Override
    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public double getCurrentPrice() {
        return 0;
    }

    @Override
    public void setCurrentPrice(double currentPrice) {
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String getImage() {
        return image;
    }

    @Override
    public void setImage(String image) {
        this.image = image;
    }
}
