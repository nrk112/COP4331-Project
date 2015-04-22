package controller;

import Resources.ProjectConstants;
import model.Buyer;
import model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/**
  * Shopping Cart Manager handles some functions related to the shopping cart.
  */
public class ShoppingCartManager {

    private static final ShoppingCartManager instance = new ShoppingCartManager();

    /**
     * Constructor does nothing.
     */
    private ShoppingCartManager() {
    }

    /**
     * Returns the instance of the ShoppingCartManager singleton.
     * @return the instance.
     */
    public static ShoppingCartManager getInstance() {
        return instance;
    }

    /**
     * Passes the shopping cart to the transaction manager where the transactions can be created.
     * @param user The buyer with attached shopping cart.
     */
    public void buyNow(Buyer user) {
        TransactionManager.getInstance().buyNow(user);
    }

    /**
     * Creates a JTable for use in the shopping cart and confirmation views.
     * @param ProductsList The list of products to populate the table with.
     * @param user The buyer whos products are populating the table.
     * @return The constructed JTable.
     */
    public JTable displayData(List<Product> ProductsList, Buyer user)
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
        Object[] tableColumnNames = {"Name", "Price per Item", "Quantity", "Total Price per item", "Totals"};
        aModel.setColumnIdentifiers(tableColumnNames);

        if (ProductsList == null) {
            tbProducts.setModel(aModel);
            return tbProducts;
        }

        Locale locale = new Locale("en", "US");
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
        NumberFormat percentageFormat = NumberFormat.getPercentInstance(locale);

        int columns = tableColumnNames.length;
        Object[] objects = new Object[columns];
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
                objects[4] = currencyFormat.format(currentProduct.getCurrentPrice()*quantity);
                aModel.addRow(objects);
                subTotal += (currentProduct.getCurrentPrice()*quantity);
                list.add(currentProduct.getProductID());
            }
        }
        if(subTotal > 0){
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
            objects[4] = percentageFormat.format(ProjectConstants.SALES_TAX);
            aModel.addRow(objects);

            objects[0] = "";
            objects[1] = "";
            objects[2] = "Grand Total:";
            objects[3] = "";
            objects[4] = currencyFormat.format((subTotal + (subTotal * (ProjectConstants.SALES_TAX))));
            aModel.addRow(objects);
        }

        //alternate background color for rows
        tbProducts.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

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
        tbProducts.setFont(ProjectConstants.MEDIUM_FONT);
        tbProducts.setRowHeight(ProjectConstants.TABLE_HEIGHT);
        return tbProducts;
    }
}
