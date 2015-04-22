package cop4331.model;

/**
 * This is the base concrete class for the product cop4331.model.
 * */
public class StandardProduct implements Product {

    private int productID;
    private int sellerID;
    private String name;
    private String description;
    private double cost;
    private double price;
    private int quantity;
    private String image = null;

    /**
     * Constructs the regular product.
     * @param productID The productID.
     * @param sellerID The sellerID.
     * @param name The name of the product.
     * @param description The description.
     * @param cost The sellers cost.
     * @param price The MSRP price.
     * @param quantity The quantity available.
     * @param image The fully qualified filename.
     */
    public StandardProduct(int productID, int sellerID, String name, String description, double cost, double price, int quantity, String image) {
        this.productID = productID;
        this.sellerID = sellerID;
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
    }

    @Override
    public int getProductID() {
        return productID;
    }

    @Override
    public int getSellerID() {
        return sellerID;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public double getCost() {
        return cost;
    }

    @Override
    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public double getCurrentPrice() {
        return price;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String getImage() {
        return image;
    }

    @Override
    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString()
    {
        return "";
    }

    @Override
    public boolean equals(Object other)
    {
        if((other.getClass() != this.getClass())) return false;
        StandardProduct item = (StandardProduct) other;
        return(
                item.getCost() == this.getCost() &&
                        item.getPrice() == this.getPrice() &&
                        item.getProductID() == this.getProductID() &&
                        item.getSellerID() == this.getSellerID() &&
                        item.getName().contentEquals(this.getName()))&&
                item.getDescription().contentEquals(this.getDescription()) &&
                item.getImage().contentEquals(this.getImage()
                );
    }
}
