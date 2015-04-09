package view;

import Resources.Common;
import Resources.ProjectConstants;
import controller.InventoryManager;
import controller.ShoppingCartManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
import model.Product;

/**
 * Created by Nick on 4/4/2015.
 */
public class ConfirmationView extends JFrame {
    
    public ConfirmationView(Buyer user) {

        this.buyer = user;
        ///TODO Remove test function
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
        this.tbProducts=ShoppingCartManager.getInstance().DisplayData(InventoryManager.getInstance().getProductList(), buyer);
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
                  user.ClearShoppingCart();
                  new MarketPlaceView(user);
            }
        });
        return btn;
    }
    

    JTable tbProducts = new JTable();
    Buyer buyer;
}
