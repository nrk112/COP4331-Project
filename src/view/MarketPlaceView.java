package view;

import Resources.ProjectConstants;
import model.Buyer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Nick on 4/2/2015.
 */
public class MarketPlaceView extends JFrame {

    public MarketPlaceView(Buyer user) {

        setTitle("Shopazon - Marketplace");
        setSize(ProjectConstants.WINDOW_WIDTH, ProjectConstants.WINDOW_HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Open the window in the center of the screen.
        setLocationRelativeTo(null);

        //Make the main JPanel to use in the Frame
        JPanel mainPanel = new JPanel(new BorderLayout());

        //Create the heading panel where the shopping cart link will be.
        JPanel headingPanel = new JPanel(new BorderLayout(10,10));
        String nameLink = user.getFullName() + " - Cart";
        JLabel nameLinkLabel = new JLabel(nameLink);
        nameLinkLabel.setFont(new Font("Calibri", Font.BOLD, 24));
        nameLinkLabel.setBorder(new EmptyBorder(3,0,0,10));
        headingPanel.add(nameLinkLabel, BorderLayout.EAST);

        //Create the scrollable product panel.
        JPanel productPanel = new JPanel(new GridLayout(0,3,10,10));
        productPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        JScrollPane scrollProductPanel = new JScrollPane(productPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        //Generate fake products for now.
        for (int i = 0; i < 8; i++) {
            productPanel.add(createItemIcon());
        }

        //
        mainPanel.add(headingPanel, BorderLayout.NORTH);
        mainPanel.add(scrollProductPanel, BorderLayout.CENTER);

        this.add(mainPanel);
        this.setVisible(true);
    }

    //Turn an inventory item into an icon for use in the main window.
    private JPanel createItemIcon() {
        int side = 180;
        JPanel panel = new JPanel(new BorderLayout());
        Dimension dimension = new Dimension(side, side);
        panel.setMaximumSize(dimension);
        panel.setMinimumSize(dimension);
        panel.setPreferredSize(dimension);
        panel.setBackground(Color.GRAY);

        JLabel title = new JLabel("Product Name");
        title.setBorder(new EmptyBorder(3, 3, 3, 3));
        title.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(title, BorderLayout.NORTH);
        JPanel fakePic = new JPanel();
        fakePic.setBackground(Color.DARK_GRAY);
        panel.add(fakePic, BorderLayout.CENTER);

        JLabel price = new JLabel("Product Name");
        price.setBorder(new EmptyBorder(3, 3, 3, 3));
        price.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(price, BorderLayout.SOUTH);


        panel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                JOptionPane.showMessageDialog((Component) e.getSource(), "Here is some stuff about your item....  buy lots of them!");

            }

            @Override
            public void mouseReleased(MouseEvent e) {

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

}
