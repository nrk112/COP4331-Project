package view;

import Resources.ProjectConstants;
import model.Seller;

import javax.swing.*;

/**
 * Created by Nick on 4/2/2015.
 */
public class SellerListView extends JFrame {

    public SellerListView(Seller user) {

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
