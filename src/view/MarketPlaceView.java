package view;

import Resources.ProjectConstants;
import controller.InventoryManager;
import model.Buyer;
import model.Product;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;

/**
 * Displays all the items currently available for sale in the marketplace.
 */
public class MarketPlaceView extends JFrame {

    /**
     * Constructor sets up and displays the view.
     * @param user
     */
    public MarketPlaceView(final Buyer user) {

        //Set window properties
        setTitle("Shopazon - Marketplace");
        setSize(ProjectConstants.WINDOW_WIDTH + 50, ProjectConstants.WINDOW_HEIGHT+ 50);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Open the window in the center of the screen.
        setLocationRelativeTo(null);

        //Make the main JPanel to use in the Frame
        mainPanel = new JPanel(new BorderLayout());

        //Create the heading panel where the shopping cart link will be.
        headingPanel = new JPanel(new BorderLayout(10,10));
        String cartLink = user.getFullName() + " - Cart: " + user.getShoppingCart().size();
        JLabel cartLinkLabel = new JLabel(cartLink);
        cartLinkLabel.setFont(ProjectConstants.TITLE_FONT);
        cartLinkLabel.setBorder(new EmptyBorder(3, 0, 0, 10));

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
                new ShoppingCartView(MarketPlaceView.this, user);
                repaint();
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
        JPanel productPanel = new JPanel(new GridLayout(0,3,10,10));
        productPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        productPanel.setBackground(Color.WHITE);
        JScrollPane scrollProductPanel = new JScrollPane(productPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollProductPanel.getVerticalScrollBar().setUnitIncrement(ProjectConstants.SCROLL_SPEED);


         InventoryManager.getInstance();
         //populate seller products data
         InventoryManager.getInstance().GetSellerData();
        
        //Generate products
         Iterator productIter = InventoryManager.getInstance().getProductList().iterator();
         Product currentProduct;
            while(productIter.hasNext()) 
            {
                currentProduct = (Product) productIter.next();                
                productPanel.add(createItemIcon(user, currentProduct));
            }

        //
        mainPanel.add(headingPanel, BorderLayout.NORTH);
        mainPanel.add(scrollProductPanel, BorderLayout.CENTER);

        this.add(mainPanel);
        this.setVisible(true);
    }

    //Turn an inventory item into an icon for use in the main window.
    private JPanel createItemIcon(final Buyer user, final Product product) {
        int side = 180;
        JPanel panel = new JPanel(new BorderLayout());
        Dimension dimension = new Dimension(side, side);
        panel.setMaximumSize(dimension);
        panel.setMinimumSize(dimension);
        panel.setPreferredSize(dimension);
        panel.setBackground(Color.GRAY);

        JLabel title = new JLabel(product.getName());
        title.setBorder(new EmptyBorder(3, 3, 3, 3));
        title.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(title, BorderLayout.NORTH);
        JPanel fakePic = new JPanel();
        ImageIcon image = new ImageIcon(product.getImage());
        JLabel label = new JLabel(image);
        fakePic.setBackground(Color.DARK_GRAY);
        fakePic.add(label);
        panel.add(fakePic, BorderLayout.CENTER);

        JLabel price = new JLabel(String.format("%1$,.2f", product.getCurrentPrice()));
        price.setBorder(new EmptyBorder(3, 3, 3, 3));
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
                new ProductDetailView(MarketPlaceView.this, user, product);
                new MarketPlaceView(user);
                dispose();
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

    private JPanel headingPanel;
    private JPanel mainPanel;

}
