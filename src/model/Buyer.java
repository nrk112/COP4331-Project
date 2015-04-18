package model;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *  Represents the buyer object
 */
public class Buyer extends UserModel {
    private ArrayList<Product> cartItems = new ArrayList<>();
    
    public Buyer(int userID, String fullName, String username, String password, String streetAddress, String city, String state, String zip, boolean isSeller) {
        super(userID, fullName, username, password, streetAddress, city, state, zip, isSeller);
    }
    public ArrayList<Product> getShoppingCart()
    {
        return cartItems;
    }
    
    public void addToShoppingCart(Product item)
    {
        cartItems.add(item);
    }
    public int getShoppingCartQuantity(Product item)
    {
     int count = 0;
     Product currentProduct;
     Iterator lineItemIter = cartItems.iterator();
            while(lineItemIter.hasNext()) {
                currentProduct = (Product) lineItemIter.next();
                if(currentProduct.equals(item))
                {
                    count++;
                }
            }
       return count;
       //return cartItems.size();
    }

    public void clearShoppingCart() {
        cartItems.clear();
    }
}
