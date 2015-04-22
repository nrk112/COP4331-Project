package model;

import controller.TransactionManager;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Represents the seller object.
 */
public class Seller extends UserModel {

    private ArrayList<LineItem> lineItems = new ArrayList<>();

    /**
     * Creates the seller object.
     * @param userID The ID associated with this user.
     * @param fullName The users full name.
     * @param username The username of the user.
     * @param password The password of the user.
     * @param streetAddress The street address of the user.
     * @param city The city of the user.
     * @param state The state of the user.
     * @param zip The zip code of the user.
     * @param isSeller Should always be true for the seller.
     */
    public Seller(int userID, String fullName, String username, String password, String streetAddress, String city, String state, String zip, boolean isSeller) {
        super(userID, fullName, username, password, streetAddress, city, state, zip, isSeller);
        populateTransactions();
    }

    /**
     * Add an item to the list of products the seller sold.
     * @param item The product to add.
     */
    public void addToTransactionLineItemList(LineItem item)
    {
        lineItems.add(item);
    }

    /**
     * Creates a revenue reporting item for a specific product.
     * @param item the item to get reporting data from.
     * @return the revenue reporting item.
     */
    public RevenueReportingItem getRevenueReportingItem(Product item)
    {
        int quantity = 0;
        double cost = 0.0;
        double revenue = 0.0;

        LineItem currentItem;
        Iterator lineItemIter = lineItems.iterator();
        while(lineItemIter.hasNext()) {
            currentItem = (LineItem) lineItemIter.next();
            if(currentItem.getProductID() == item.getProductID() && currentItem.getSellerID() == this.getID())
            {
                quantity += currentItem.getQuantity();
                cost += currentItem.getCost();
                //revenue += currentItem.getPrice();

                //Added because its not calculating the total revenue after the discount.
                if (item instanceof DiscountedProduct) {
                    double percentDiscount = ((DiscountedProduct)item).getDiscountedBy() / 100.0;
                    revenue += currentItem.getPrice() - (currentItem.getPrice() * percentDiscount);
                } else {
                    revenue += currentItem.getPrice();
                }
            }
        }
        return new RevenueReportingItem(cost, revenue, quantity);
    }

    /**
     * Clears the list of items associated with this seller and repopulates the list with current items.
     */
    public void populateTransactions() {
        lineItems.clear();
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
