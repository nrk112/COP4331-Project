package cop4331.view;

import cop4331.Resources.ProjectConstants;
import cop4331.Resources.WrapLayout;
import cop4331.controller.InventoryManager;
import cop4331.model.Buyer;
import cop4331.model.Product;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;

/**
 * The window that displays all the items currently available for sale in the marketplace.
 */
public class MarketPlaceView extends JDialog {

    /**
     * Constructor sets up and displays the cop4331.view.
     * @param user The buyer object representing the customer.
     */
    public MarketPlaceView(final Buyer user) {

        this.user = user;

        //Set window properties
        setModal(true);
        setTitle("Shopazon - Marketplace");
        setSize(ProjectConstants.WINDOW_WIDTH + 50, ProjectConstants.WINDOW_HEIGHT + 50);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        //Open the window in the center of the screen.
        setLocationRelativeTo(null);

        //Make the main JPanel to use in the Frame
        JPanel mainPanel = new JPanel(new BorderLayout());

        //Create the heading panel where the shopping cart link will be.
        JPanel headingPanel = new JPanel(new BorderLayout(10,10));
        cartLinkLabel = new JLabel();
        cartLinkLabel.setFont(ProjectConstants.TITLE_FONT);
        cartLinkLabel.setBorder(new EmptyBorder(3, 0, 0, 10));
        updateHeading();

        //Add the mouse click listener to the label.
        cartLinkLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                new ShoppingCartView(user);
                updateHeading();
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        
        headingPanel.add(cartLinkLabel, BorderLayout.EAST);

        //Create the scrollable product panel.
        JPanel productPanel = new JPanel(new WrapLayout(FlowLayout.CENTER, 20, 20));
        productPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        productPanel.setBackground(Color.DARK_GRAY);
        JScrollPane scrollProductPanel = new JScrollPane(productPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollProductPanel.getVerticalScrollBar().setUnitIncrement(ProjectConstants.SCROLL_SPEED);
        
        //Generate products
         Iterator productIter = InventoryManager.getInstance().getProductList().iterator();
         Product currentProduct;
            while(productIter.hasNext()) 
            {
                currentProduct = (Product) productIter.next();                
                productPanel.add(createItemIcon(currentProduct));
            }

        mainPanel.add(headingPanel, BorderLayout.NORTH);
        mainPanel.add(scrollProductPanel, BorderLayout.CENTER);

        this.add(mainPanel);
        this.setVisible(true);
    }

    /**
     * Updates the heading which shows the amount of items int he cart.
     */
    private void updateHeading(){
        String cartLink = user.getFullName() + " - Cart: " + user.getShoppingCart().size();
        cartLinkLabel.setText(cartLink);
    }

    /**
     * Turn an inventory item into a JPanel contianing price, picture, and name of the product.
     * @param product the object representing the item for sale.
     * @return The constructed JPanel holding the information
     */
    private JPanel createItemIcon(final Product product) {
        int width = 180;
        int height = 200;
        JPanel panel = new JPanel(new BorderLayout());
        Dimension dimension = new Dimension(width, height);
        panel.setMaximumSize(dimension);
        panel.setMinimumSize(dimension);
        panel.setPreferredSize(dimension);
        panel.setBackground(Color.LIGHT_GRAY);

        JLabel title = new JLabel(product.getName());
        title.setFont(ProjectConstants.MEDIUM_FONT);
        title.setBorder(new EmptyBorder(3, 3, 3, 3));
        title.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(title, BorderLayout.NORTH);

        ImageIcon image = new ImageIcon(product.getImage());
        JLabel label = new JLabel(image);
        panel.add(label, BorderLayout.CENTER);

        JLabel price = new JLabel(String.format("$%1$,.2f", product.getCurrentPrice()));
        price.setBorder(new EmptyBorder(3, 3, 3, 3));
        price.setFont(ProjectConstants.MEDIUM_FONT);
        price.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(price, BorderLayout.SOUTH);

        panel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                new ProductDetailView(user, product);
                updateHeading();
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        return panel;
    }

    private JLabel cartLinkLabel;
    private Buyer user;
}
