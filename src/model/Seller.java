package model;

import controller.TransactionManager;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 */
public class Seller extends UserModel {
    private ArrayList<LineItem> lineItems = new ArrayList<>();
    
    public Seller(int userID, String fullName, String username, String password, String streetAddress, String city, String state, String zip, boolean isSeller) {
        super(userID, fullName, username, password, streetAddress, city, state, zip, isSeller);
        getSellerTransactions();
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

    /**
     *
     */
    //TODO:  Shouldn't this be in the SellerManager or Seller class itself? When a user is created it can get its own stuff.
    private void getSellerTransactions() {
        Iterator lineItemIter = TransactionManager.getInstance().getTransactionLineItemList().iterator();
        LineItem currentLineItem ;
        while(lineItemIter.hasNext()) {
            currentLineItem = (LineItem) lineItemIter.next();
            if(currentLineItem.getSellerID() == this.getID())
            {
                this.addToTransactionLineItemList(currentLineItem);
            }
        }
    }
}
