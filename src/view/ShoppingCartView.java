package view;

import Resources.ProjectConstants;
import model.Buyer;

import javax.swing.*;

/**
 * Created by Nick on 4/4/2015.
 */
public class ShoppingCartView extends JFrame {

    public ShoppingCartView(Buyer user) {

        setTitle("Shopazon - Seller List View");
        setSize(ProjectConstants.WINDOW_WIDTH, ProjectConstants.WINDOW_HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Open the window in the center of the screen.
        setLocationRelativeTo(null);

        //Make the main JPanel to use in the Frame
        JPanel mainPanel = new JPanel();
        mainPanel.add(new JLabel("Here be more dragons!"));
        this.add(mainPanel);
        this.setVisible(true);
    }
}
