package controller;

import model.Buyer;
import model.Product;

/**
 * Created by Nick on 4/4/2015.
 */
public class MarketPlaceManager {

    private static final MarketPlaceManager instance = new MarketPlaceManager();



    public static MarketPlaceManager getInstance() {
        return instance;
    }



    public void ProductDetalView(Buyer user, Product product) {
        //TODO populate the product list here.
        //new ProductDetailView(user, product);
    }
}
