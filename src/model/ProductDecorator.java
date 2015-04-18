package model;

/**
 * The base class to modify a product with the decorator pattern. Extend class to use.
 */
public abstract class ProductDecorator implements Product {

    protected Product item;

    public ProductDecorator(Product product) {
        item = product;
    }

    public double getPrice() {
        return item.getPrice();
    }

    public double getCurrentPrice()
    {
        return item.getCurrentPrice();
    }

    public int getQuantity()
    {
        return item.getQuantity();
    }

    public int getProductID()
    {
        return item.getProductID();
    }

    public int getSellerID()
    {
        return item.getSellerID();
    }

    public double getCost()
    {
        return item.getCost();
    }

    public String getName()
    {
        return item.getName();
    }

    public String getDescription()
    {
        return item.getDescription();
    }

    public String getImage()
    {
        return item.getImage();
    }

    public void setPrice(double price) {
        item.setPrice(price);
    }

    public void setQuantity(int qty) {
        item.setQuantity(qty);
    }

    public void setCost(double cost) {
        item.setCost(cost);
    }

    public void setName(String name) {
        item.setName(name);
    }

    public void setDescription(String description) {
        item.setDescription(description);
    }

    public void setImage(String fileName) {
        item.setImage(fileName);
    }
}
