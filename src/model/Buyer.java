package model;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *  Represents the buyer object
 */
public class Buyer extends UserModel {
    private ArrayList<Product> cartItems = new ArrayList<>();

    /**
     * Constructs the buyer object.
     * @param userID The ID of this user.
     * @param fullName The users full name.
     * @param username The users username.
     * @param password The users password.
     * @param streetAddress The users street address.
     * @param city The users city.
     * @param state The users state.
     * @param zip The users zip code.
     * @param isSeller Should always be false for a buyer.
     */
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
