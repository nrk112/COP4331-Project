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
public class ProductDetailView extends JFrame {

    private final String TITLE = "Shopazon - Add New Product";

//    private final JTextField name;
//    private final JTextField description;
//    private final JTextField cost;
//    private final JTextField price;
//    private final JTextField quantity;
//    private final JTextField discountedBy;
    private final Buyer buyer;
//    private final JTextField image;
    /**
     * Constructs and shows the add product view view
     * @param user Current instance of the seller object
     * @param product
     */
    public ProductDetailView(Buyer user, final Product product) {
        this.buyer = user;
        setTitle(TITLE);
        setSize(ProjectConstants.WINDOW_WIDTH, ProjectConstants.WINDOW_HEIGHT);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        //Open the window in the center of the screen.
        setLocationRelativeTo(null);

        //Make the main JPanel to use in the Frame
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

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

        JLabel title = new JLabel(product.getDescription());
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
                    new MarketPlaceView(buyer);

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
                    new ShoppingCartView(buyer);

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

        //make the window visible.
        setVisible(true);
    }


    /**
     * Validates the registration text fields.
     * @return True if all fields are valid.
     */
    private boolean validateFields() {
        return ( true
//                !name.getText().isEmpty() &&
//                cost.getText().matches("\\d+(\\.\\d{1,2})?") &&
//                price.getText().matches("\\d+(\\.\\d{1,2})?") &&
//                discountedBy.getText().matches("\\d+(\\.\\d{1,2})?") &&
//                quantity.getText().matches("\\d+") &&
//                !image.getText().isEmpty()
                );
    }
}
