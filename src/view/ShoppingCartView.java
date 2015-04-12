package view;

import Resources.Common;
import Resources.ProjectConstants;
import controller.InventoryManager;
import controller.ShoppingCartManager;
import model.Buyer;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The view that displays a particular users shopping cart.
 */
public class ShoppingCartView extends JDialog {
    
    private JTextField nameOnCard;
    private JTextField creditcardNumber;
    private JTextField cvv;
    private JTextField expirationMonth;
    private JTextField expirationYear;
    private JTable tbProducts;
    private Buyer buyer;
    private JFrame parentFrame;
    
    public ShoppingCartView(JFrame parentFrame, Buyer user) {

        this.buyer = user;
        this.parentFrame = parentFrame;

        setModal(true);

        setTitle("Shopazon - Shopping Cart");
        setSize(ProjectConstants.WINDOW_WIDTH, ProjectConstants.WINDOW_HEIGHT);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
       
        //Open the window in the center of the screen.
        setLocationRelativeTo(null);

        ShoppingCartManager.getInstance();
        ShoppingCartManager.getInstance().getTransactionData();

        //Make the main JPanel to use in the Frame
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        //Add new button
        JPanel bottomPanel = new JPanel();
        JButton addButton = createBuyButton(buyer);
        bottomPanel.add(addButton);
        
        //Add return button
        JButton returnButton = CreateReturnToMarketPlaceButton(buyer);
        bottomPanel.add(returnButton);

        //populate jtable with products
        this.tbProducts = ShoppingCartManager.getInstance().DisplayData(InventoryManager.getInstance().getProductList(), buyer);
        JScrollPane tableContainer = new JScrollPane(tbProducts);

        //Add the panels to the main one.
        mainPanel.add(tableContainer);
        mainPanel.add(createPaymentPanel());
        mainPanel.add(createBuyButton(user));

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
       final JButton btn = new JButton("Cancel");
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


    private JButton createBuyButton(final Buyer user)
    {
        //Create the button.
        final JButton btn = new JButton("Buy Now");
        //Allow enter to press the button at any time.
        this.getRootPane().setDefaultButton(btn);

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {     
                  if (validateFields()) {
                    ShoppingCartManager.getInstance().BuyNow(user);                    
                    
                    // update transactions file
                    ShoppingCartManager.getInstance().writeTransactionsToFile();

                    //Go confirmation view.
                    new ConfirmationView(user);

                    //Close the window
                    getParent().repaint();
                    dispose();

                  } else {
                    JOptionPane.showMessageDialog(null, "Please provide payment information!");
                }
            }
        });
        return btn;
    }

    /**
     * Create the panel where users input credit card data.
     * @return The created panel.
     */
    private JPanel createPaymentPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        panel.setBorder(new TitledBorder("Payment Information"));

        nameOnCard = Common.createTextField("Name on Credit Card");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5,5,5,5);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        panel.add(nameOnCard, c);

        creditcardNumber = Common.createTextField("Credit Card Number");
        c.gridx = 0;
        c.gridy = 1;
        panel.add(creditcardNumber, c);

        cvv = Common.createTextField("CVV Code");
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        panel.add(cvv, c);

        expirationMonth = Common.createTextField("Exp. Month (xx)");
        c.gridx = 1;
        c.gridy = 2;
        panel.add(expirationMonth, c);

        expirationYear = Common.createTextField("Exp. Year (xxxx)");
        c.gridx = 2;
        c.gridy = 2;
        panel.add(expirationYear, c);

        mainPanel.add(panel, BorderLayout.CENTER);
        mainPanel.add(getTotalsPanel(), BorderLayout.EAST);
        return mainPanel;
    }

    /**
     * Creates the panel that has the total cost information.
     * @return the created panel.
     */
    private JPanel getTotalsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setMinimumSize(new Dimension(100, 50));

        JLabel subTotal = new JLabel("Subtotal: ");
        JLabel tax = new JLabel("Tax: ");
        JLabel total = new JLabel("Total: ");

        panel.add(subTotal);
        panel.add(tax);
        panel.add(total);

        return panel;
    }

}
