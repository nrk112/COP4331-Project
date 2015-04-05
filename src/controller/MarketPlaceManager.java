package controller;

import model.Product;

import java.util.ArrayList;

/**
 * Created by Nick on 4/4/2015.
 */
public class MarketPlaceManager {

    private static final MarketPlaceManager instance = new MarketPlaceManager();
    private ArrayList<Product> productList;

    private MarketPlaceManager() {
        populateProductList();

    }

    public MarketPlaceManager getInstance() {
        return instance;
    }

    public ArrayList getProductList() {
        return productList;
    }

    private void populateProductList() {
        //TODO populate the product list here.
        productList = new ArrayList<>();
    }
}
