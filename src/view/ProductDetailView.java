package view;

import Resources.ProjectConstants;
import model.Buyer;
import model.Product;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The window where users can sign up for service.
 */
public class ProductDetailView extends JDialog {

    private final Buyer buyer;
    private JFrame parentFrame;
    private Product product;
    private JComboBox qtyComboBox;

    /**
     * Constructs and shows the add product view view
     * @param user Current instance of the seller object
     * @param product
     */
    public ProductDetailView(JFrame parentFrame, Buyer user, final Product product) {

        this.buyer = user;
        this.parentFrame = parentFrame;
        this.product = product;


        //Make this window modal
        this.setModal(true);

        //Set window properties
        setTitle(product.getName() + " - Details");
        setSize(ProjectConstants.WINDOW_WIDTH, ProjectConstants.WINDOW_HEIGHT);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        //Open the window in the center of the screen.
        setLocationRelativeTo(null);

        //Make the main JPanel to use in the Frame
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setLayout(new BorderLayout());

        //Create the title panel.
        JPanel titlePanel = new JPanel(new BorderLayout());

        //Format price
        String productPrice = String.format("%1$,.2f", product.getPrice());

        JLabel titleLabel = new JLabel(product.getName());
        titleLabel.setFont(ProjectConstants.TITLE_FONT);

        JLabel priceLabel = new JLabel("$" + productPrice);
        priceLabel.setFont(ProjectConstants.TITLE_FONT);

        titlePanel.add(titleLabel, BorderLayout.WEST);
        titlePanel.add(priceLabel, BorderLayout.EAST);



        /*
        //Create the heading
        String onSale = "";
        if(product.getCurrentPrice()!=product.getPrice())
        {
            onSale = "Now on Sale!";
        }

        //Create the heading panel where the shopping cart link will be.
        JPanel headingPanel = new JPanel(new BorderLayout(10,10));
        String cartLink = user.getFullName() + " - Cart: " + user.getShoppingCart().size();
        JLabel cartLinkLabel = new JLabel(cartLink);
        cartLinkLabel.setFont(ProjectConstants.TITLE_FONT);
        cartLinkLabel.setBorder(new EmptyBorder(3, 0, 0, 10));
        mainPanel.add(headingPanel);
        JLabel heading = new JLabel(product.getName() + " " + onSale);
        mainPanel.add(heading, BorderLayout.NORTH);
        
                
        int side = 180;
        JPanel panel = new JPanel(new BorderLayout());
        Dimension dimension = new Dimension(side, side);
        panel.setMaximumSize(dimension);
        panel.setMinimumSize(dimension);
        panel.setPreferredSize(dimension);
        panel.setBackground(Color.LIGHT_GRAY);

        JLabel title = new JLabel(product.toString());
        title.setBorder(new EmptyBorder(3, 3, 3, 3));
        title.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(title, BorderLayout.NORTH);
        
        
        JPanel fakePic = new JPanel();
        ImageIcon image = new ImageIcon(product.getImage());
        JLabel label = new JLabel(image);
        fakePic.setBackground(Color.DARK_GRAY);
        fakePic.add(label);
        panel.add(fakePic, BorderLayout.CENTER);

        JLabel price = new JLabel(String.format("$"+"%1$,.2f", product.getCurrentPrice()));
        price.setBorder(new EmptyBorder(3, 3, 3, 3));
        price.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(price, BorderLayout.SOUTH);
        
        mainPanel.add(panel, BorderLayout.CENTER);

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
                        buyer.addToShoppingCart(product);
                        JOptionPane.showMessageDialog((Component) e.getSource(), product.getName()+ " added to cart. Total items: "+ buyer.getShoppingCart().size() );
                //Otherwise give the error message and let them try again.
                } else {
                    JOptionPane.showMessageDialog((Component) e.getSource(), "Could not save product, please verify all information is provided" );
                }
            }
        });

        //Create the Cancel button.
       final JButton btnCancel = new JButton("Return to Market Place");
        //Allow enter to press the button at any time.
        this.getRootPane().setDefaultButton(btnCancel);
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
                    //Go back to sellerlist view.
                    //new MarketPlaceView(buyer);
                parentFrame.repaint();

                    //Close the window
                    dispose();
            }
        });
        
         //Create the Cancel button.
       final JButton btnGoToCart = new JButton("Go To Cart");
        //Allow enter to press the button at any time.
        this.getRootPane().setDefaultButton(btnGoToCart);
        btnGoToCart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
                    //Go back to sellerlist view.
                    new ShoppingCartView(null, buyer);

                    //Close the window
                    dispose();
            }
        });

       

    JPanel buttonPanel = new JPanel(new GridLayout(0,3,10,10));

        buttonPanel.add(btnSave);
        btnSave.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        buttonPanel.add(btnCancel);
        btnCancel.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttonPanel.add(btnGoToCart);
        btnCancel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        //Add the created panel to the main Frame
        this.add(mainPanel, BorderLayout.CENTER);

        */

        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(getDescriptionPanel(), BorderLayout.CENTER);
        mainPanel.add(getButtonPanel(), BorderLayout.SOUTH);

        this.add(mainPanel, BorderLayout.CENTER);
        //make the window visible.
        setVisible(true);
    }

    /**
     * Create the description panel with image.
     * @return the constructed panel.
     */
    private JPanel getDescriptionPanel(){
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createLoweredBevelBorder());

        //create the description text area
        JTextArea description = new JTextArea(product.toString());
        description.setLineWrap(true);
        description.setWrapStyleWord(true);
        description.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(description, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        //Create the image panel
        ImageIcon image = new ImageIcon(product.getImage());
        JLabel imageLabel = new JLabel(image);
        imageLabel.setBorder(new EmptyBorder(5, 5, 5, 5));

        //Add stuff to the main panel
        panel.add(imageLabel ,BorderLayout.WEST);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    /**
     * Create the lower panel with the quantity and add to cart button
     * @return the panel
     */
    private JPanel getButtonPanel() {

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(5, 0, 0, 0));

        JPanel panel = new JPanel();

        //Make quantity label
        JLabel qtyLabel = new JLabel("Qty: ");

        //Make the quantity field
        String[] qytStrings = {"1", "2", "3", "4", "5"};
        qtyComboBox = new JComboBox(qytStrings);
        qtyComboBox.setPreferredSize(new Dimension(50, 27));

        //Make the add to cart button
        JButton addButton = new JButton("Add to Cart");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Check the qty selection and assign it
                int qtySelected;
                String msg = (String)qtyComboBox.getSelectedItem();
                switch (msg) {
                    case "1":
                        qtySelected = 1;
                        break;
                    case "2":
                        qtySelected = 2;
                        break;
                    case "3":
                        qtySelected = 3;
                        break;
                    case "4":
                        qtySelected = 4;
                        break;
                    case "5":
                        qtySelected = 5;
                        break;
                    default:
                        qtySelected = 1;
                        break;
                }

                if (qtySelected <= product.getQuantity()) {

                    //Add the specified amount to the cart.
                    for (int i = 0; i < qtySelected; i++) {
                        buyer.addToShoppingCart(product);
                    }

                    //Notify them of success
                    JOptionPane.showMessageDialog(null, qtySelected + " " + product.getName()+ " added to cart. Total items: "+ buyer.getShoppingCart().size() );

                    parentFrame.repaint();
                    //Close the window.
                    dispose();

                } else {
                    //Notify them of failure
                    JOptionPane.showMessageDialog(null, "Sorry! There is only " + product.getQuantity() + " of " + product.getName() + " left! Please select a different amount." );
                }
            }
        });

        //Add the stuff to the panel
        panel.add(qtyLabel);
        panel.add(qtyComboBox);
        panel.add(addButton);

        mainPanel.add(panel, BorderLayout.EAST);

        return mainPanel;
    }
}
