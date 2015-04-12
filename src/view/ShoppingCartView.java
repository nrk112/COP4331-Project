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
 * The view that displays a particular users shopping cart.
 */
public class ShoppingCartView extends JFrame {
    
    private final JTextField nameOnCard;
    private final JTextField creditcardNumber;
    private final JTextField cvv;
    private final JTextField expirationMonth;
    private final JTextField expirationYear;
    private JTable tbProducts;
    private Buyer buyer;
    private JFrame parentFrame;
    
    public ShoppingCartView(JFrame parentFrame, Buyer user) {

        this.buyer = user;
        this.parentFrame = parentFrame;

        ///TODO Remove test function
        //this.buyer.PopulateList();
        setTitle("Shopazon - Shopping Cart");
        setSize(ProjectConstants.WINDOW_WIDTH, ProjectConstants.WINDOW_HEIGHT);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
       
        //Open the window in the center of the screen.
         setLocationRelativeTo(null);
         ShoppingCartManager.getInstance();
         ShoppingCartManager.getInstance().getTransactionData();

        //Make the main JPanel to use in the Frame
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        //Add new button
        JPanel topPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        JButton addButton = CreateAddNewButton(buyer);
        bottomPanel.add(addButton);
        
        //Add return button
        JButton returnButton = CreateReturnToMarketPlaceButton(buyer);
        bottomPanel.add(returnButton);
         
        //Add Payment text Fields
        
        //Create the heading
        JLabel heading = new JLabel("Payment:");
        //Create the JTextFields
        nameOnCard = Common.createTextField("Name on Credit Card");
        creditcardNumber = Common.createTextField("Credit Card Number");
        cvv = Common.createTextField("3 or 4 Digit Code");
        expirationMonth = Common.createTextField("Expiration Month (xx)");
        expirationYear = Common.createTextField("Expiration Year (xxxx)");
        
        int fillerX = ProjectConstants.FILLER_X;
        int fillerY = 25;

        topPanel.add(Common.getFiller(fillerX, fillerY));
        topPanel.add(heading);
        heading.setAlignmentX(Component.LEFT_ALIGNMENT);

        fillerY = ProjectConstants.FILLER_Y;
        topPanel.add(Common.getFiller(fillerX, fillerY));
        topPanel.add(nameOnCard);

        fillerY = ProjectConstants.FILLER_Y;
        topPanel.add(Common.getFiller(fillerX, fillerY));
        topPanel.add(creditcardNumber);

        fillerY = ProjectConstants.FILLER_Y;
        topPanel.add(Common.getFiller(fillerX, fillerY));
        topPanel.add(cvv);

        fillerY = ProjectConstants.FILLER_Y;
        topPanel.add(Common.getFiller(fillerX, fillerY));
        topPanel.add(expirationMonth);
        
        fillerY = ProjectConstants.FILLER_Y;
        topPanel.add(Common.getFiller(fillerX, fillerY));
        topPanel.add(expirationYear);
        
        
        //populate jtable with products
        this.tbProducts = ShoppingCartManager.getInstance().DisplayData(InventoryManager.getInstance().getProductList(), buyer);
        JScrollPane tableContainer = new JScrollPane(tbProducts);

        mainPanel.add(tableContainer, BorderLayout.CENTER);
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        //Show the window
        add(mainPanel);
        setVisible(true);
    }
     /**
     * Validates the payment info text fields.
     * @return True if all fields are valid.
     */
    private boolean validateFields() {
        return (
                !nameOnCard.getText().isEmpty() &&
                expirationYear.getText().matches("[0-9]{4}")&&
                expirationMonth.getText().matches("[0-9]{2}")&&
                cvv.getText().matches("[0-9]{3,4}") &&
                creditcardNumber.getText().matches("\\d+")
                );
    }
    
    private JButton CreateReturnToMarketPlaceButton(final Buyer user)
    {
        //Create the Cancel button.
       final JButton btn = new JButton("Return to Market Place");
        //Allow enter to press the button at any time.
        this.getRootPane().setDefaultButton(btn);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
                    //Go back to Marketplace view.
                    //new MarketPlaceView(user);
                parentFrame.repaint();

                    //Close the window
                    dispose();
            }
        });
        return btn;
    }


    private JButton CreateAddNewButton(final Buyer user)
    {
        //Create the Save button.
        final JButton btn = new JButton("Buy Now");
        //Allow enter to press the button at any time.
        this.getRootPane().setDefaultButton(btn);
        btn.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {     
                  if (validateFields()) {
                    ShoppingCartManager.getInstance().BuyNow(user);                    
                    
                    // update products fle
                    ShoppingCartManager.getInstance().writeTransactionsToFile();
                    //Go confirmation view.
                    new ConfirmationView(user); 

                    //Close the window
                      parentFrame.repaint();
                    dispose();

                  } //Otherwise give the error message and let them try again.
                 else {
                    JOptionPane.showMessageDialog((Component) e.getSource(), "Please provide payment information!");
                }
            }
        });
        return btn;
    }

}
