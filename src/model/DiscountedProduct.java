/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * A decorator for product that applies a discount
 */
public class DiscountedProduct implements Product{
    private Product item;
    private int discountedBy =  0;   
    
    public DiscountedProduct(Product item, int discountedBy)
    {
        this.item=item;
        this.discountedBy = discountedBy;
    }
        
    public double getPrice() {
       return (item.getPrice()*(1-(discountedBy/100))); 
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
    public int getDiscountedBy()
    {
        return discountedBy;
    }
    public String toString()
    {
        return item.toString() + " (Discount " + discountedBy + "%";
    }
    
}
