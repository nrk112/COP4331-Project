package model;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Nick on 3/30/2015.
 */
public class Seller extends UserModel {
    private ArrayList<LineItem> lineItems = new ArrayList<>();
    
    public Seller(int userID, String fullName, String username, String password, String streetAddress, String city, String state, String zip, boolean isSeller) {
        super(userID, fullName, username, password, streetAddress, city, state, zip, isSeller);
    }
    
    public ArrayList<LineItem> getTransactionLineItems()
    {
        return lineItems;
    }
    
   public void addToTransactionLineItemList(LineItem item)
    {
        lineItems.add(item);
    }
    
    public RevenueReportingItem getRevenueReportingItem(Product item)
    {
     int quantity = 0;
     double cost = 0.0;
     double revenue = 0.0;
      
     LineItem currentItem;
     Iterator lineItemIter = lineItems.iterator();
            while(lineItemIter.hasNext()) {
                currentItem = (LineItem) lineItemIter.next();
                if(currentItem.getProductID()==item.getProductID() && currentItem.getSellerID()==this.getID())
                {
                    quantity += currentItem.getQuantity();
                    cost += currentItem.getCost();
                    revenue += currentItem.getPrice();
                }
            }
       return new RevenueReportingItem(cost, revenue, quantity);
    }
}
