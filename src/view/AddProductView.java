package view;

import Resources.ProjectConstants;
import controller.AccountManager;
import controller.SellerManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import model.Seller;

/**
 * The window where users can sign up for service.
 */
public class AddProductView extends JFrame {

    private final String TITLE = "Shopazon - Add New Product";

    private final JTextField name;
    private final JTextField description;
    private final JTextField cost;
    private final JTextField price;
    private final JTextField quantity;
    private final JTextField discountedBy;
    private final Seller seller;
    /**
     * Constructs and shows the add product view view
     * @param user Current instance of the seller object
     */
    public AddProductView(Seller user) {
        this.seller = user;
        setTitle(TITLE);
        setSize(ProjectConstants.WINDOW_WIDTH, ProjectConstants.WINDOW_HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Open the window in the center of the screen.
        setLocationRelativeTo(null);

        //Make the main JPanel to use in the Frame
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(
                new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        //Create the heading
        JLabel heading = new JLabel("Please fill in the following information for your product:");

        //Create the JTextFields
        name = createTextField("Name");
        description = createTextField("Description");
        cost = createTextField("Cost (x.xx)");
        price = createTextField("Price (x.xx)");
        quantity = createTextField("Quantity (xxx)");
        discountedBy = createTextField("Discounted By (xx)");

        //Create the Save button.
       final JButton btnSave = new JButton("Save");
        //Allow enter to press the button at any time.
        this.getRootPane().setDefaultButton(btnSave);
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //If the input is valid, send the data to create a user.
                if (validateFields()) {
                    //Check if they are registering as a buyer or seller.
                        SellerManager.getInstance().createProduct(
                                SellerManager.getInstance().getProductId(),
                                seller.getID(),
                                name.getText(),
                                description.getText(),                                
                                Double.parseDouble(cost.getText()),                   
                                Double.parseDouble(price.getText()),
                                Integer.parseInt(quantity.getText()),                   
                                Integer.parseInt(discountedBy.getText())
                        );
                    
                    // update products fle
                    SellerManager.getInstance().writeProductsToFile();
                    //Go back to sellerlist view.
                    new SellerListView(seller);
                    
                   

                    //Close the window
                    dispose();

                //Otherwise give the error message and let them try again.
                } else {
                    JOptionPane.showMessageDialog((Component) e.getSource(), "Could not save product, please verify all information is provided" );
                }
            }
        });


        //Add all the components to the main panel
        int fillerX = ProjectConstants.FILLER_X;
        int fillerY = 50;

        mainPanel.add(getFiller(fillerX, fillerY));
        mainPanel.add(heading);
        heading.setAlignmentX(Component.CENTER_ALIGNMENT);

        fillerY = ProjectConstants.FILLER_Y;
        mainPanel.add(getFiller(fillerX, fillerY));
        mainPanel.add(name);

        fillerY = ProjectConstants.FILLER_Y;
        mainPanel.add(getFiller(fillerX, fillerY));
        mainPanel.add(description);

        mainPanel.add(getFiller(fillerX, fillerY));
        mainPanel.add(cost);

        mainPanel.add(getFiller(fillerX, fillerY));
        mainPanel.add(price);

        mainPanel.add(getFiller(fillerX, fillerY));
        mainPanel.add(quantity);

        mainPanel.add(getFiller(fillerX, fillerY));
        mainPanel.add(discountedBy);

        mainPanel.add(getFiller(fillerX, fillerY));
        mainPanel.add(btnSave);
        btnSave.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Add the created panel to the main Frame
        this.add(mainPanel, BorderLayout.CENTER);

        //make the window visible.
        setVisible(true);
    }

    /**
     * Creates a JTextField that will highlight the text when it gets focus.
     * @param label The temporary filler text.
     * @return the constructed JTextField.
     */
    private JTextField createTextField(String label) {

        final JTextField textField = new JTextField(label);
        textField.setMaximumSize(
                new Dimension(ProjectConstants.TEXTFIELD_WIDTH, textField.getPreferredSize().height));

        //The will highlight the text when it gets focus since its pre-populated.
        textField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        textField.selectAll();
                    }
                });
            }
        });

        textField.setAlignmentX(Component.CENTER_ALIGNMENT);
        return textField;
    }

    /**
     * Validates the registration text fields.
     * @return True if all fields are valid.
     */
    private boolean validateFields() {
        return (
                !name.getText().isEmpty() &&
                cost.getText().matches("\\d+(\\.\\d{1,2})?") &&
                price.getText().matches("\\d+(\\.\\d{1,2})?") &&
                discountedBy.getText().matches("\\d+") &&
                quantity.getText().matches("\\d+")
                );
    }

    /**
     * Creates a component that can be used to insert whitespace around GUI elements.
     * @param x the amount of whitespace pixels along the x axis.
     * @param y the amount of whitespace pixels along the y axis.
     * @return the Filler component to add to another component for whitespace.
     */
    private Component getFiller(int x, int y) {
        Dimension size = new Dimension(x, y);
        return new Box.Filler(size, size, size);//Min Max and Preferred are all the same
    }
}
