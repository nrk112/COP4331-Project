package model;

/**
 * The base class to modify a product with the decorator pattern. Extend class to use.
 */
public abstract class ProductDecorator implements Product {

    protected Product item;

    /**
     * Saves an instance of the item to decorate.
     * @param product The product to decorate.
     */
    public ProductDecorator(Product product) {
        item = product;
    }

    @Override
    public double getPrice() {
        return item.getPrice();
    }

    @Override
    public double getCurrentPrice()
    {
        return item.getCurrentPrice();
    }

    @Override
    public int getQuantity()
    {
        return item.getQuantity();
    }

    @Override
    public int getProductID()
    {
        return item.getProductID();
    }

    @Override
    public int getSellerID()
    {
        return item.getSellerID();
    }

    @Override
    public double getCost()
    {
        return item.getCost();
    }

    @Override
    public String getName()
    {
        return item.getName();
    }

    @Override
    public String getDescription()
    {
        return item.getDescription();
    }

    @Override
    public String getImage()
    {
        return item.getImage();
    }

    @Override
    public void setPrice(double price) {
        item.setPrice(price);
    }

    @Override
    public void setQuantity(int qty) {
        item.setQuantity(qty);
    }

    @Override
    public void setCost(double cost) {
        item.setCost(cost);
    }

    @Override
    public void setName(String name) {
        item.setName(name);
    }

    @Override
    public void setDescription(String description) {
        item.setDescription(description);
    }

    @Override
    public void setImage(String fileName) {
        item.setImage(fileName);
    }
}
