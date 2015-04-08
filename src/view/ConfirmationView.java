package view;

import Resources.Common;
import Resources.ProjectConstants;
import controller.InventoryManager;
import controller.ShoppingCartManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import model.Buyer;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.DiscountedProduct;
import model.Product;
import model.Seller;

/**
 * Created by Nick on 4/4/2015.
 */
public class ConfirmationView extends JFrame {
    
    public ConfirmationView(Buyer user) {

        this.buyer = user;
        ///TODO Remove test function
        this.buyer.PopulateList();
        setTitle("Shopazon - Confirmation");
        setSize(ProjectConstants.WINDOW_WIDTH, ProjectConstants.WINDOW_HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
       
        //Open the window in the center of the screen.
         setLocationRelativeTo(null);
         ShoppingCartManager.getInstance();
         
         
        //Make the main JPanel to use in the Frame
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        //Add new button
        JPanel topPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        JButton addButton = CreateReturnButton(buyer);
        bottomPanel.add(addButton);
         
        //Add Payment text Fields
        
        //Create the heading
        JLabel heading = new JLabel("Thank you for shopping at Shopazon!");
       
        
        int fillerX = ProjectConstants.FILLER_X;
        int fillerY = 25;

        topPanel.add(Common.getFiller(fillerX, fillerY));
        topPanel.add(heading);
        heading.setAlignmentX(Component.LEFT_ALIGNMENT);      
        
        
        //populate jtable with products
        DisplayData(InventoryManager.getInstance().getProductList(), buyer);
        JScrollPane tableContainer = new JScrollPane(tbProducts);

        mainPanel.add(tableContainer, BorderLayout.CENTER);
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        //Show the window
        add(mainPanel);
        setVisible(true);
    }
   
    
    private JButton CreateReturnButton(final Buyer user)
    {
        //Create the Save button.
        final JButton btn = new JButton("Return to MarketPlace");
        //Allow enter to press the button at any time.
        this.getRootPane().setDefaultButton(btn);
        btn.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {     
                  new MarketPlaceView(user);
            }
        });
        return btn;
    }
    
    private void DisplayData(List<Product> ProductsList, Buyer user) 
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
        Object[] tableColumnNames = new Object[5];
        tableColumnNames[0] = "Name";
        tableColumnNames[1] = "Price per Item";
        tableColumnNames[2] = "Quantity";
        tableColumnNames[3] = "Total Price per item";
        tableColumnNames[4] = "Totals";
        

        aModel.setColumnIdentifiers(tableColumnNames);
        if (ProductsList == null) {
        this.tbProducts.setModel(aModel);
        return;
        }
        Locale locale = new Locale("en", "US");
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
        NumberFormat percentageFormat = NumberFormat.getPercentInstance(locale);
        Object[] objects = new Object[5];
        Product currentProduct;
        double subTotal = 0.0;
            //Write each product to a products file.
            Iterator cartIter = user.getShoppingCart().iterator();
            while(cartIter.hasNext()) {
                currentProduct = (Product) cartIter.next();

                   
                    objects[0] = currentProduct.getName();
                    objects[1] = currencyFormat.format(currentProduct.getCurrentPrice());
                    objects[2] = currentProduct.getQuantity();
                    objects[3] = currencyFormat.format((currentProduct.getCurrentPrice() * currentProduct.getQuantity()));
                    aModel.addRow(objects);
                    subTotal += (currentProduct.getCurrentPrice()* currentProduct.getQuantity());
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
    this.tbProducts.setModel(aModel);
    }
    JTable tbProducts = new JTable();
    Buyer buyer;
}
