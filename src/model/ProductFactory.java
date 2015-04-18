package model;

/**
 *Creates products
 */
public class ProductFactory {

    /**
     * Determines if a regular or discounted product should be created and returns appropriately.
     * @param productID New products ID
     * @param sellerID New products SellerID
     * @param name New products name
     * @param description New Products description
     * @param cost New products cost
     * @param price New products price
     * @param quantity New products quantity
     * @param discountedBy Percent the new product is discounted by
     * @param image Filename of the image associated with the new product.
     * @return The constructed product.
     */
    public static Product CreateProduct(int productID, int sellerID, String name, String description, double cost, double price, int quantity, double discountedBy, String image) {

        Product product = new StandardProduct(
                productID,
                sellerID,
                name,
                description,
                cost,
                price,
                quantity,
                image
        );

        if (discountedBy>0)
        {
            return new DiscountedProduct(product, discountedBy);
        }
        return product;
    }
}

