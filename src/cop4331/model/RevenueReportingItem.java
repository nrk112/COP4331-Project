package cop4331.model;

/**
 * Helper class used to help calculate the totals associated with the sellers sales.
 */
public class RevenueReportingItem {

    /**
     * Creates the reporting item.
     * @param totalCost The total sellers cost of all the products.
     * @param totalRevenue The total sales of all the products.
     * @param totalQuantitySold The amount of products sold.
     */
    public RevenueReportingItem(double totalCost, double totalRevenue, int totalQuantitySold)
    {
        this.cost = totalCost;
        this.revenue = totalRevenue;
        this.quantitySold = totalQuantitySold;
    }

    private double cost;
    private double revenue;
    private int quantitySold;

    /**
     * Gets the profit from this sellers sales.
     * @return the profit.
     */
    public double getProfit()
    {
        return revenue-cost;
    }

    /**
     * Gets the total seller cost of all the sales.
     * @return the cost.
     */
    public double getTotalCost()
    {
        return cost;
    }

    /**
     * Gets the total revenue of all the sellers sales.
     * @return the revenue.
     */
    public double getTotalRevenue()
    {
        return revenue;
    }

    /**
     * Gets the quantity of items sold.
     * @return the quantity.
     */
    public int getTotalQuantitySold()
    {
        return quantitySold;
    }
}
