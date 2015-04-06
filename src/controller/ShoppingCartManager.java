package controller;

import java.util.ArrayList;
import model.Buyer;
import model.Product;
/**
 * Created by Nick on 4/4/2015.
 */
public class ShoppingCartManager {

    private static final ShoppingCartManager instance = new ShoppingCartManager();
    private final ArrayList<Product> products = new ArrayList<>();
    private ShoppingCartManager() {
    }
    public static ShoppingCartManager getInstance() {
        return instance;
    }
    public void BuyNow(Buyer user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
