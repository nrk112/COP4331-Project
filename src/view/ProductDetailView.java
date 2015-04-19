package view;

import Resources.ProjectConstants;
import model.Buyer;
import model.DiscountedProduct;
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
    private Product product;
    private JComboBox<String> qtyComboBox;

    /**
     * Constructs and shows the add product view view
     * @param user Current instance of the seller object
     * @param product The product that will be used to display details about.
     */
    public ProductDetailView(Buyer user, final Product product) {

        this.buyer = user;
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

        String productPrice = String.format("$%1$,.2f", product.getCurrentPrice());

        //Display the discount portion.
        if(product instanceof DiscountedProduct && ((DiscountedProduct)product).getDiscountedBy() != 0.0){
            JLabel discountLabel = new JLabel("On Sale! " + ((DiscountedProduct)product).getDiscountedBy() + "% OFF!");
            discountLabel.setFont(ProjectConstants.TITLE_FONT);
            discountLabel.setForeground(Color.RED);
            titlePanel.add(discountLabel, BorderLayout.SOUTH);
            productPrice = String.format("Was: $%1$,.2f NOW ONLY: $%2$,.2f", product.getPrice(), product.getCurrentPrice());
        }

        JLabel titleLabel = new JLabel(product.getName());
        titleLabel.setFont(ProjectConstants.TITLE_FONT);

        JLabel priceLabel = new JLabel(productPrice);
        priceLabel.setFont(ProjectConstants.TITLE_FONT);

        titlePanel.add(titleLabel, BorderLayout.WEST);
        titlePanel.add(priceLabel, BorderLayout.EAST);

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
        JTextArea description = new JTextArea(product.getDescription());
        description.setLineWrap(true);
        description.setWrapStyleWord(true);
        description.setEditable(false);
        //description.setBackground(Color.);
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

        //Make quantity labels
        int qtyAvailable = (product.getQuantity() - buyer.getShoppingCartQuantity(product));
        JLabel availableLabel = new JLabel("Qty Available: " + qtyAvailable + "  ");
        JLabel qtyLabel = new JLabel("Qty: ");

        //Make the quantity field
        String[] qytStrings = {"1", "2", "3", "4", "5"};
        qtyComboBox = new JComboBox<>(qytStrings);
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

                if (qtySelected <= qtyAvailable) {

                    //Add the specified amount to the cart.
                    for (int i = 0; i < qtySelected; i++) {
                        buyer.addToShoppingCart(product);
                    }

                    //Notify them of success
                    JOptionPane.showMessageDialog(null, qtySelected + " " + product.getName()+ " added to cart. Total items: "+ buyer.getShoppingCart().size() );
                    dispose();

                } else {
                    //Notify them of failure
                    JOptionPane.showMessageDialog(null, "Sorry! There is only " + product.getQuantity() + " of " + product.getName() + " left! Please select a different amount." );
                }
            }
        });

        //Add the stuff to the panel
        panel.add(availableLabel);
        panel.add(qtyLabel);
        panel.add(qtyComboBox);
        panel.add(addButton);

        mainPanel.add(panel, BorderLayout.EAST);

        return mainPanel;
    }
}
