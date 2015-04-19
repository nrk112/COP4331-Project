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

    /**
     * Gets this users shopping cart
     * @return the cart
     */
    public ArrayList<Product> getShoppingCart()
    {
        return cartItems;
    }

    /**
     * Add a product to the shopping cart
     * @param item the product to add
     */
    public void addToShoppingCart(Product item)
    {
        cartItems.add(item);
    }

    /**
     * Calculates how many of a specific product is in the cart
     * @param item the product to calculate
     * @return the amount of said product in the cart.
     */
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
    }

    /**
     * Remove all items from the shopping cart.
     */
    public void clearShoppingCart() {
        cartItems.clear();
    }
}
