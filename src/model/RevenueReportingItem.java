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
public class RevenueReportingItem {
    
    public RevenueReportingItem(double totalCost, double totalRevenue, int totalQuantitySold)
    {
        this.cost = totalCost;
        this.revenue = totalRevenue;
        this.quantitySold = totalQuantitySold;
    }
    
    private double cost;
    private double revenue;
    private int quantitySold;
    
    public double getProfit()
    {
        return revenue-cost;
    }
    
    public int remainingQuantity(int originalInventory)
    {
        return originalInventory - quantitySold;
    }
    
    public double getTotalCost()
    {
        return cost;
    }
    
    public double getTotalRevenue()
    {
        return revenue;
    }
    
    public int getTotalQuantitySold()
    {
        return quantitySold;
    }
    
}
