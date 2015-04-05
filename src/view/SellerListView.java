package view;

import Resources.ProjectConstants;
import controller.SellerManager;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import model.Seller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.DiscountedProduct;
import model.Product;

/**
 * Created by Nick on 4/2/2015.
 */
public class SellerListView extends JFrame {

    public SellerListView(Seller user) {

        this.seller = user;
        setTitle("Shopazon - Seller List View");
        setSize(ProjectConstants.WINDOW_WIDTH, ProjectConstants.WINDOW_HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Open the window in the center of the screen.
        setLocationRelativeTo(null);
         SellerManager.getInstance();
         SellerManager.getInstance().GetSellerData();
        //Make the main JPanel to use in the Frame
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        //TODO Put the code to create the window here.
        JPanel topPanel = new JPanel();
        JButton addButton = CreateAddNewButton(seller);
        topPanel.add(addButton);
        DisplayData(SellerManager.getInstance().getProductList(), seller);
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
        this.getRootPane().setDefaultButton(btn);
        btn.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {               
                    SellerManager.getInstance().AddProductView(user);
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
        Object[] tableColumnNames = new Object[7];
        tableColumnNames[0] = "ProductID";
        tableColumnNames[1] = "Name";
        tableColumnNames[2] = "Description";
        tableColumnNames[3] = "Cost";
        tableColumnNames[4] = "Price";
        tableColumnNames[5] = "Quantity";
        tableColumnNames[6] = "Discounted By";
        aModel.setColumnIdentifiers(tableColumnNames);
        if (ProductsList == null) {
        this.tbProducts.setModel(aModel);
        return;
        }
        Object[] objects = new Object[7];
        Product currentProduct;
            Iterator productIter = SellerManager.getInstance().getProductList().iterator();
            while(productIter.hasNext()) {
                currentProduct = (Product) productIter.next();
                if(currentProduct.getSellerID() == user.getID())
                {
                    double discountedBy = 0.0;
                    if(currentProduct instanceof DiscountedProduct)
                    {
                        discountedBy = ((DiscountedProduct)currentProduct).getDiscountedBy();
                    }
                    objects[0] =  currentProduct.getProductID();
                    objects[1] = currentProduct.getName();
                    objects[2] = currentProduct.getDescription();
                    objects[3] = currentProduct.getCost();
                    objects[4] =  currentProduct.getPrice();
                    objects[5] =  currentProduct.getQuantity();
                    objects[6] =  discountedBy;
                    aModel.addRow(objects);                
                }
            }
    this.tbProducts.setModel(aModel);
    }
    JTable tbProducts = new JTable();
    Seller seller;
}
