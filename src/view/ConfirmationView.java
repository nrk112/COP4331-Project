package view;

import Resources.Common;
import Resources.ProjectConstants;
import controller.InventoryManager;
import controller.ShoppingCartManager;
import model.Buyer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Displays a confirmation page that the products were purchased.
 */
public class ConfirmationView extends JDialog {
    
    public ConfirmationView(Buyer user) {

        this.buyer = user;

        setModal(true);

        setTitle("Shopazon - Confirmation");
        setSize(ProjectConstants.WINDOW_WIDTH, ProjectConstants.WINDOW_HEIGHT);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
       
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
        final JButton btn = new JButton("OK");
        //Allow enter to press the button at any time.
        this.getRootPane().setDefaultButton(btn);
        btn.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {     
                  user.ClearShoppingCart();
                  //new MarketPlaceView(user);
                dispose();
            }
        });
        return btn;
    }
    

    JTable tbProducts = new JTable();
    Buyer buyer;
}
