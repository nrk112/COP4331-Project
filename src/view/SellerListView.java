package view;

import Resources.ProjectConstants;
import controller.InventoryManager;
import controller.TransactionManager;
import model.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/**
 * Presents to the seller a list of their products
 */
public class SellerListView extends JFrame {

    public SellerListView(Seller user) {

        this.user = user;
        setTitle("Shopazon - " + user.getFullName() + "'s items.");
        setSize(ProjectConstants.WINDOW_WIDTH + 200, ProjectConstants.WINDOW_HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Open the window in the center of the screen.
        setLocationRelativeTo(null);
         
        //populate seller products data
        InventoryManager.getInstance().GetSellerData();
        GetSellerTransactions();

        //Make the main JPanel to use in the Frame
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        //Add new button
        JPanel topPanel = new JPanel();
        JButton addButton = CreateAddNewButton(user);
        topPanel.add(addButton);
        
        //populate jtable with products
        displayData(InventoryManager.getInstance().getProductList(), user);
        JScrollPane tableContainer = new JScrollPane(tbProducts);

        mainPanel.add(tableContainer, BorderLayout.CENTER);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        //Show the window
        add(mainPanel);
        setVisible(true);
    }
    
    private JButton CreateAddNewButton(final Seller user)
    {
        final JButton btn = new JButton("Add New Product");
        //Allow enter to press the button at any time.
        this.getRootPane().setDefaultButton(btn);
        btn.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                    InventoryManager.getInstance().AddProductView(user);
                displayData(InventoryManager.getInstance().getProductList(), user);
            }
        });
        return btn;
    }
    
    private void displayData(List<Product> ProductsList, Seller user)
    {
        DefaultTableModel aModel = new DefaultTableModel() 
        {            //setting the jtable read only
            @Override
            public boolean isCellEditable(int row, int column) 
            {
            return false;
            }
        };
        
        //setting the column name
        Object[] tableColumnNames = new Object[11];
        tableColumnNames[0] = "Name";
        tableColumnNames[1] = "Description";
        tableColumnNames[2] = "Quantity Remaining";
        tableColumnNames[3] = "Quantity Sold";
        tableColumnNames[4] = "Cost";
        tableColumnNames[5] = "Total Cost";
        tableColumnNames[6] = "Original Price";
        tableColumnNames[7] = "Discounted By";
        tableColumnNames[8] = "Current Price";
        tableColumnNames[9] = "Total Revenue";
        tableColumnNames[10] = "Total Profit";

        aModel.setColumnIdentifiers(tableColumnNames);
        if (ProductsList == null) {
        this.tbProducts.setModel(aModel);
        return;
        }
        Locale locale = new Locale("en", "US");
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
        NumberFormat percentageFormat = NumberFormat.getPercentInstance(locale);
        Object[] objects = new Object[11];
        Product currentProduct;        
        double subTotalCost = 0.0;      
        double subTotalRev = 0.0;      
        double subTotalProfit = 0.0;
            //Write each product to a products file.
            Iterator productIter = InventoryManager.getInstance().getProductList().iterator();
            while(productIter.hasNext()) {
                currentProduct = (Product) productIter.next();
                if(currentProduct.getSellerID() == user.getID())
                {
                    double discountedBy = 0.0;
                    if(currentProduct instanceof DiscountedProduct)
                    {
                        discountedBy = ((DiscountedProduct)currentProduct).getDiscountedBy();
                    }
                    RevenueReportingItem rri = user.getRevenueReportingItem(currentProduct);
                    objects[0] = currentProduct.getName();
                    objects[1] = currentProduct.getDescription();
                    objects[2] = rri.getRemainingQuantity(currentProduct.getQuantity());
                    objects[3] = rri.getTotalQuantitySold();
                    objects[4] = currencyFormat.format(currentProduct.getCost());
                    objects[5] = currencyFormat.format(rri.getTotalCost());
                    objects[6] = currencyFormat.format(currentProduct.getPrice());
                    objects[7] = (discountedBy==0)? "--" : percentageFormat.format(discountedBy/100);
                    objects[8] = currencyFormat.format(currentProduct.getCurrentPrice());
                    objects[9] = currencyFormat.format(rri.getTotalRevenue());
                    objects[10] = currencyFormat.format(rri.getProfit());
                    aModel.addRow(objects);  
                    subTotalCost += rri.getTotalCost();
                    subTotalRev += rri.getTotalRevenue();
                    subTotalProfit += rri.getProfit();
                    
                }
            }
            if(subTotalCost>0){
                objects[0] = "Totals:";
                objects[1] = "";
                objects[2] = "";
                objects[3] = "";
                objects[4] = "";
                objects[5] = currencyFormat.format(subTotalCost);
                objects[6] = "";
                objects[7] = "";
                objects[8] = "";
                objects[9] = currencyFormat.format(subTotalRev);
                objects[10] = currencyFormat.format(subTotalProfit);
                aModel.addRow(objects);   
                
                objects[0] = "Profit Margin:";
                objects[1] = "";
                objects[2] = "";
                objects[3] = "";
                objects[4] = "";
                objects[5] = "";
                objects[6] = "";
                objects[7] = "";
                objects[8] = "";
                objects[9] = "";
                objects[10] = percentageFormat.format((subTotalProfit/subTotalCost));
                aModel.addRow(objects);          
            }
            tbProducts.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {//alternate background color for rows
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                   JLabel c = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    if (!isSelected) {
                        c.setBackground(row % 2 == 0 ? Color.white : Color.LIGHT_GRAY);
                        if(column==0){
                            c.setHorizontalAlignment(JLabel.LEFT);
                        }
                        else if(column < 4)
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
        this.tbProducts.setModel(aModel);
    }
    JTable tbProducts = new JTable();
    Seller user;

    /**
     *
     */
    //TODO:  Shouldn't this be in the SellerManager or Seller class itself? When a user is created it can get its own stuff.
    private void GetSellerTransactions() {
        Iterator lineItemIter = TransactionManager.getInstance().getTransactionLineItemList().iterator();
        LineItem currentLineItem ;
        while(lineItemIter.hasNext()) {
            currentLineItem = (LineItem) lineItemIter.next();
            if(currentLineItem.getSellerID() == user.getID())
            {
                user.addToTransactionLineItemList(currentLineItem);
            }
        }
    }
}
