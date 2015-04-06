package model;

import java.util.ArrayList;
/**
 * Created by Nick on 3/30/2015.
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
    public void PopulateList()
    {
        addToShoppingCart(new StandardProduct(1,1,"Red Coffee Cup", "desc", 0.99, 4.99, 1)); 
        addToShoppingCart(new StandardProduct(2,1,"Blue Coffee Cup", "desc", 0.89, 4.99, 2));
        addToShoppingCart(new DiscountedProduct( new StandardProduct(3,1,"Black Coffee Cup", "desc", 0.99, 4.99, 3), 5.0)); 
    }
    public void addToShoppingCart(Product item)
    {
        cartItems.add(item);
    }
}
