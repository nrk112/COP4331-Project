package cop4331.view;

import cop4331.Resources.Common;
import cop4331.Resources.ProjectConstants;
import cop4331.controller.InventoryManager;
import cop4331.controller.ShoppingCartManager;
import cop4331.model.Buyer;
import cop4331.model.Product;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

/**
 * The cop4331.view that displays a particular users shopping cart.
 */
public class ShoppingCartView extends JDialog {

    private JTextField nameOnCard;
    private JTextField creditcardNumber;
    private JTextField cvv;
    private JTextField expirationMonth;
    private JTextField expirationYear;
    private JTable tbProducts;
    private Buyer user;

    public ShoppingCartView(Buyer user) {

        this.user = user;

        setModal(true);

        setTitle("Shopazon - Shopping Cart");
        setSize(ProjectConstants.WINDOW_WIDTH, ProjectConstants.WINDOW_HEIGHT);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        //Open the window in the center of the screen.
        setLocationRelativeTo(null);

        //Make the main JPanel to use in the Frame
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        //Add new button
        JPanel bottomPanel = new JPanel();
        JButton addButton = createBuyButton(this.user);
        bottomPanel.add(addButton);
        bottomPanel.add(createClearCartBtn());

        //populate jtable with products
        this.tbProducts = ShoppingCartManager.getInstance().displayData(InventoryManager.getInstance().getProductList(), this.user);
        JScrollPane tableContainer = new JScrollPane(tbProducts);

        //Add the panels to the main one.
        mainPanel.add(tableContainer);
        mainPanel.add(createPaymentPanel());
        mainPanel.add(bottomPanel);

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

    private JButton createClearCartBtn()
    {
        final JButton btn = new JButton("Empty the Cart");
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShoppingCartView.this.user.clearShoppingCart();
                ShoppingCartView.this.tbProducts.setVisible(false);
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
                if(ShoppingCartView.this.user.getShoppingCart().size() == 0) {
                    JOptionPane.showMessageDialog(null, "Your Cart is empty, please add something to purchase.");
                }else if(validateFields()) {
                    ShoppingCartManager.getInstance().buyNow(user);

                    //Go confirmation cop4331.view.
                    new ConfirmationView(user);

                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Please provide the correct payment information!");
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
        //mainPanel.add(getTotalsPanel(), BorderLayout.EAST);
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

        ArrayList list = new ArrayList();
        double subTotal = 0.0;
        Product currentProduct;
        Iterator cartIter = user.getShoppingCart().iterator();
        while(cartIter.hasNext()) {
            currentProduct = (Product) cartIter.next();
            if(!list.contains(currentProduct.getProductID()))
            {
                int quantity = user.getShoppingCartQuantity(currentProduct);
                subTotal += (currentProduct.getCurrentPrice()*quantity);
            }
        }
        Locale locale = new Locale("en", "US");
        NumberFormat total = NumberFormat.getCurrencyInstance(locale);
        total.format((subTotal + (subTotal * (ProjectConstants.SALES_TAX))));

        NumberFormat tax = NumberFormat.getCurrencyInstance(locale);
        tax.format(subTotal * ProjectConstants.SALES_TAX);

        JLabel subLabel = new JLabel("Subtotal: " + subTotal);
        JLabel taxLabel = new JLabel("Tax: " + tax);
        JLabel totalLabel = new JLabel("Total: " + total);

        panel.add(subLabel);
        panel.add(taxLabel);
        panel.add(totalLabel);

        return panel;
    }

}
