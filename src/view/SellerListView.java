package view;

import Resources.ProjectConstants;
import controller.InventoryManager;
import controller.TransactionManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import model.Seller;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.DiscountedProduct;
import model.LineItem;
import model.Product;
import model.RevenueReportingItem;

/**
 * Presents to the seller a list of their products
 */
public class SellerListView extends JFrame {

    public SellerListView(Seller user) {

        this.seller = user;
        setTitle("Shopazon - Seller List View");
        setSize(ProjectConstants.WINDOW_WIDTH, ProjectConstants.WINDOW_HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Open the window in the center of the screen.
         setLocationRelativeTo(null);
         
         TransactionManager.getInstance();
         InventoryManager.getInstance();
         
         //populate seller products data
         InventoryManager.getInstance().GetSellerData();
         GetSellerTransactions();
        //Make the main JPanel to use in the Frame
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        //Add new button
        JPanel topPanel = new JPanel();
        JButton addButton = CreateAddNewButton(seller);
        topPanel.add(addButton);
        
        //populate jtable with products
        DisplayData(InventoryManager.getInstance().getProductList(), seller);
        JScrollPane tableContainer = new JScrollPane(tbProducts);

        mainPanel.add(tableContainer, BorderLayout.CENTER);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        //Show the window
        add(mainPanel);
        setVisible(true);
    }
    
    private JButton CreateAddNewButton(final Seller user)
    {
        //Create the Save button.
        final JButton btn = new JButton("Add New Product");
        //Allow enter to press the button at any time.
        this.getRootPane().setDefaultButton(btn);
        btn.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {               
                    InventoryManager.getInstance().AddProductView(user);
                    //Close the window
                    dispose();
            }
        });
        return btn;
    }
    
    private void DisplayData(List<Product> ProductsList, Seller user) 
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
        tableColumnNames[2] = "Quantity";
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
                    RevenueReportingItem rri = seller.getRevenueReportingItem(currentProduct);
                    objects[0] = currentProduct.getName();
                    objects[1] = currentProduct.getDescription();
                    objects[2] = currentProduct.getQuantity();
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
                    Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    if (!isSelected) {
                        c.setBackground(row % 2 == 0 ? Color.white : Color.LIGHT_GRAY);
                    }
                    return c;
                }
            });            
    //binding the jtable to the model
    this.tbProducts.setModel(aModel);
    }
    JTable tbProducts = new JTable();
    Seller seller;

    private void GetSellerTransactions() {
        Iterator lineItemIter = TransactionManager.getInstance().getTransactionLineItemList().iterator();
        LineItem currentLineItem ;
        while(lineItemIter.hasNext()) {
            currentLineItem = (LineItem) lineItemIter.next();
            if(currentLineItem.getSellerID() == seller.getID())
            {
                seller.addToTransactionLineItemList(currentLineItem);
            }
        }
    }
}
