package cop4331.model;

/**
 * A decorator for product that applies a discount
 */
public class DiscountedProduct extends ProductDecorator {

    private double discountedBy;

    /**
     * Constructor saves an instance of the product to be decorated and then decorates it with the discount.
     * @param product The product to add a discount.
     * @param discountedBy The percentage the product is discounted by.
     */
    public DiscountedProduct(Product product, double discountedBy)
    {
        super(product);
        this.discountedBy = discountedBy;
    }

    @Override
    public double getCurrentPrice()
    {
        double newPrice = getPrice()*(1-(discountedBy/100));
        return Math.round(newPrice*100.0)/100.0;
    }

    /**
     * Returns the discount associated with this product.
     * @return The discount.
     */
    public double getDiscountedBy()
    {
        return discountedBy;
    }

    /**
     * Sets the discount associated with this product.
     * @param discountedBy the percentage of discount.
     */
    public void setDiscountedBy(double discountedBy) {
        this.discountedBy = discountedBy;
    }

    @Override
    public String toString()
    {
        return this.item.getDescription() + " (Marked down by " + discountedBy + "%)";
    }

    @Override
    public boolean equals(Object other)
    {
        if((other.getClass()!= this.getClass())) return false;
        DiscountedProduct item = (DiscountedProduct) other;
        return( 
                item.getDiscountedBy() == this.getDiscountedBy() &&
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
