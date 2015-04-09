package controller;

import Resources.ProjectConstants;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.Buyer;
import model.DiscountedProduct;
import model.Product;
import model.TransactionLineItem;
import view.ConfirmationView;

/**
 * Shopping Cart Manager handles all functions related with the shopping cart
 */
public class ShoppingCartManager {

    private static final ShoppingCartManager instance = new ShoppingCartManager();

    private ShoppingCartManager() {
    }
    
    public void getTransactionData()
    {                
        TransactionManager.getInstance();
        TransactionManager.getInstance().getTransactionData();
    }
    /**
     * Returns the instance of the SellerManager singleton.
     * @return the instance.
     */
    public static ShoppingCartManager getInstance() {
        return instance;
    }
    
   /*
    *  Get product list
    * @return the product ArrayList.
    */
    public ArrayList getTransactionLineItemList() {
        return TransactionManager.getInstance().getTransactionLineItemList();
    }
    
    /**
     * Create a product, then add it to the list.
     * @param transactionLineItemID The transaction line item I\id.
     * @param productID The product id.
     * @param sellerID The seller id.
     * @param buyerID the buyer id.
     * @param name The product name.
     * @param cost The product cost.
     * @param price The product price.
     * @param quantity The number of products for sale.
     */
    public void createTransactionLineItem(int transactionLineItemID, int productID, int sellerID, int buyerID, String name, double cost, double price, int quantity) {
        TransactionManager.getInstance().createTransactionLineItem(transactionLineItemID, productID, sellerID, buyerID, name, cost, price, quantity);
    }
    
    /*
    
    */
    public void BuyNow(Buyer user) {       
       TransactionManager.getInstance().BuyNow(user);
    }
    
     /**
     * reads all transaction line item data from transaction line item list to a text file.
     * @param ProductsList
     * @param user
     * @return 
     */
    public JTable DisplayData(List<Product> ProductsList, Buyer user) 
   {
       JTable tbProducts = new JTable();
        DefaultTableModel aModel = new DefaultTableModel() 
        {            //setting the jtable read only
            @Override
            public boolean isCellEditable(int row, int column) 
            {
            return false;
            }
        };
        
        //setting the column name
        Object[] tableColumnNames = new Object[5];
        tableColumnNames[0] = "Name";
        tableColumnNames[1] = "Price per Item";
        tableColumnNames[2] = "Quantity";
        tableColumnNames[3] = "Total Price per item";
        tableColumnNames[4] = "Totals";
        

        aModel.setColumnIdentifiers(tableColumnNames);
        if (ProductsList == null) {
        tbProducts.setModel(aModel);
        return tbProducts;
        }
        Locale locale = new Locale("en", "US");
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
        NumberFormat percentageFormat = NumberFormat.getPercentInstance(locale);
        Object[] objects = new Object[5];
        Product currentProduct;
        double subTotal = 0.0;
            //Write each product to a products file.
            ArrayList list = new ArrayList();
            Iterator cartIter = user.getShoppingCart().iterator();
            while(cartIter.hasNext()) {
                    currentProduct = (Product) cartIter.next();
                   if(!list.contains(currentProduct.getProductID()))
                   {  
                        int quantity = user.getShoppingCartQuantity(currentProduct);
                        objects[0] = currentProduct.getName();
                        objects[1] = currencyFormat.format(currentProduct.getCurrentPrice());
                        objects[2] = quantity;
                        objects[3] = currencyFormat.format(currentProduct.getCurrentPrice()*quantity);
                        aModel.addRow(objects);
                        subTotal += (currentProduct.getCurrentPrice()*quantity);
                        list.add(currentProduct.getProductID());
                   }
            }
            if(subTotal>0){
                objects[0] = "";
                objects[1] = "";
                objects[2] = "SubTotal:";
                objects[3] = "";
                objects[4] = currencyFormat.format(subTotal);
                aModel.addRow(objects);   
                
                objects[0] = "";
                objects[1] = "";
                objects[2] = "Sales Tax:";
                objects[3] = "";
                objects[4] = percentageFormat.format(.06);
                aModel.addRow(objects);                 
                
                objects[0] = "";
                objects[1] = "";
                objects[2] = "Grand Total:";
                objects[3] = "";
                objects[4] = currencyFormat.format((subTotal + (subTotal * (ProjectConstants.SALES_TAX))));
                aModel.addRow(objects);                 
            }
            tbProducts.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {//alternate background color for rows
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    JLabel c = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    if (!isSelected) {
                        c.setBackground(row % 2 == 0 ? Color.white : Color.LIGHT_GRAY);
                        if(column==0){
                            c.setHorizontalAlignment(JLabel.LEFT);
                        }
                        else if(column == 2)
                        {
                            if (value instanceof Integer) 
                            {
                            c.setHorizontalAlignment(JLabel.CENTER);  
                            }
                            else
                            {
                                c.setHorizontalAlignment(JLabel.RIGHT); 
                            }
                        }
                        else
                        {
                            c.setHorizontalAlignment(JLabel.RIGHT);                            
                        }
                         if(row == table.getRowCount()-1) c.setFont(c.getFont().deriveFont(Font.BOLD));
                    }
                    
                    return c;
                }
            });            
    //binding the jtable to the model
    tbProducts.setModel(aModel);
    return tbProducts;
    }

    public void writeTransactionsToFile() {
        TransactionManager.getInstance().writeTransactionsToFile();
    }
     
    


}
