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
    
    public String getImage()
    {
        return image;
    }
    
    public String toString()
    {
        return name + ": "+ description;
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
        if(!(other instanceof StandardProduct)) return false;
        if(other == null && this !=null) return false;
        if(other !=null && this == null) return false;
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
    
}
