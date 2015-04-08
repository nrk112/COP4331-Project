package view;

import Resources.ProjectConstants;
import controller.InventoryManager;
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
import model.Product;

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
         InventoryManager.getInstance();
         //populate seller products data
         InventoryManager.getInstance().GetSellerData();
         
         
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
        Object[] tableColumnNames = new Object[8];
        tableColumnNames[0] = "ProductID";
        tableColumnNames[1] = "Name";
        tableColumnNames[2] = "Description";
        tableColumnNames[3] = "Cost";
        tableColumnNames[4] = "Original Price";
        tableColumnNames[5] = "Discounted By";
        tableColumnNames[6] = "Current Price";
        tableColumnNames[7] = "Quantity";

        aModel.setColumnIdentifiers(tableColumnNames);
        if (ProductsList == null) {
        this.tbProducts.setModel(aModel);
        return;
        }
        Locale locale = new Locale("en", "US");
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
        NumberFormat percentageFormat = NumberFormat.getPercentInstance(locale);
        Object[] objects = new Object[8];
        Product currentProduct;
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
                    objects[0] = currentProduct.getProductID();
                    objects[1] = currentProduct.getName();
                    objects[2] = currentProduct.getDescription();
                    objects[3] = currencyFormat.format(currentProduct.getCost());
                    objects[4] = currencyFormat.format(currentProduct.getPrice());
                    objects[5] = (discountedBy==0)? "--" : percentageFormat.format(discountedBy/100);
                    objects[6] = currencyFormat.format(currentProduct.getCurrentPrice());
                    objects[7] = currentProduct.getQuantity();
                    aModel.addRow(objects);                
                }
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
}
