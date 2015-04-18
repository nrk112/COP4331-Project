/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Comparator;
/**
 *
 * A decorator for product that applies a discount
 */
public class DiscountedProduct implements Product{
    private Product item;
    private double discountedBy =  0;   
    
    public DiscountedProduct(Product item, double discountedBy)
    {
        this.item=item;
        this.discountedBy = discountedBy;
    }
        
    public double getPrice() {
       return item.getPrice(); 
    }  
    public double getCurrentPrice()
    {
        double newPrice = getPrice()*(1-(discountedBy/100)); 
        return Math.round(newPrice*100.0)/100.0;
    }
    public int getQuantity()
    {
        return this.item.getQuantity();
    }
    public int getProductID()
    {
        return this.item.getProductID();
    }
    public int getSellerID()
    {
        return this.item.getSellerID();
    }
    public double getCost()
    {
        return this.item.getCost();
    }
    public String getName()
    {
        return this.item.getName();
    }
    public String getDescription()
    {
        return this.item.getDescription();
    }
    
    public String getImage()
    {
        return item.getImage();
    }

    @Override
    public void setPrice(double price) {

    }

    @Override
    public void setCurrentPrice(double currentPrice) {

    }

    @Override
    public void setQuantity(int qty) {

    }

    @Override
    public void setCost(double cost) {

    }

    @Override
    public void setName(String name) {

    }

    @Override
    public void setDescription(String description) {

    }

    @Override
    public void setImage(String fileName) {

    }

    public double getDiscountedBy()
    {
        return discountedBy;
    }
    public String toString()
    {
        return this.item.getDescription()+ " (Marked down by " + discountedBy + "%)";
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
        if((other.getClass()!= this.getClass())) return false;
        DiscountedProduct item = (DiscountedProduct) other;
        return( 
                item.getDiscountedBy() == this.getDiscountedBy() &&
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
