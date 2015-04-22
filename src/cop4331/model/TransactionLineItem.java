package cop4331.model;

/**
 * Holds the Shopping Cart item for recording purposes
 */
public class TransactionLineItem implements LineItem{
    private int lineItemID;
    private int productID;
    private int sellerID;
    private int buyerID;
    private String name;
    private double cost;
    private double price;
    private int quantity;

    /**
     * Creates the transaction line item object.
     * @param transactionLineItemId The ID of this item.
     * @param productID The ID of the product associated with this item.
     * @param sellerID The sellers ID
     * @param buyerID The buyers ID
     * @param name The name of the product sold.
     * @param cost The sellers cost of the product.
     * @param price The price of the product.
     * @param quantity The quantity sold.
     */
    public TransactionLineItem(int transactionLineItemId, int productID, int sellerID, int buyerID, String name, double cost, double price, int quantity) {
        this.lineItemID     = transactionLineItemId;
        this.productID      = productID;
        this.sellerID       = sellerID;
        this.buyerID        = buyerID;
        this.name           = name;
        this.cost           = cost;
        this.price          = price;
        this.quantity       = quantity;
    }
    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public int getQuantity() {
        return quantity;
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
    public double getCost() {
        return cost;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getBuyerID() {
        return buyerID;
    }

    @Override
    public int getLineItemID() {
        return lineItemID;
    }
}
