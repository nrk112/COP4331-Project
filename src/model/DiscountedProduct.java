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
    private double discountedBy =  0;    
    public DiscountedProduct(Product item, double discountedBy)
    {
        this.item=item;
        this.discountedBy = discountedBy;
    }
        
    public double getPrice() {
      return item.getPrice()*(1-discountedBy/100);
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
        return item.toString() + " (Discount " + discountedBy + "%";
    }
    
}
