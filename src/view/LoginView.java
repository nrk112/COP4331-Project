package view;

import Resources.Common;
import Resources.ProjectConstants;
import controller.AccountManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The login window where a user can log in as either a buyer or a seller.
 */
public class LoginView extends JFrame {

    //Window properties
    private final String TITLE = "Shopazon - Login";

    /**
     * Constructor set up the windows properties and calls the functions to create and show the GUI.
     */
    public LoginView() {

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
        JLabel heading = new JLabel("Thank you for choosing Shopazon! Please log in to continue.");

        //Create the username fields
        final JTextField userName = Common.createTextField("User Name");

        //Create the password field
        final JPasswordField password = new JPasswordField("Password");
        password.setMaximumSize(
                new Dimension(ProjectConstants.TEXTFIELD_WIDTH, password.getPreferredSize().height));
        password.setEchoChar((char)0);

        //The will highlight the text when it gets focus since its pre-populated.
        password.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        password.selectAll();
                        password.setEchoChar('*');
                    }
                });
            }
        });

        //Create the registration text
        JLabel registerLabel = new JLabel("If you need an account click \"Register\"!");

        //Create the SignIn button.
        JButton loginBtn = new JButton("Log In");
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pass = new String(password.getPassword());
                if (AccountManager.getInstance().authorizeUser(userName.getText(), pass)) {
                    //Close this window.
                    dispose();
                }else{
                    JOptionPane.showMessageDialog(null, "Login Failed! Please try again!","", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        //Create the Register Button
        JButton registerBtn = new JButton("Register");
        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AccountManager.getInstance().signupClicked();
            }
        });

        //Add the objects to the main panel.
        int fillerX = ProjectConstants.FILLER_X;
        int fillerY = 100;
        mainPanel.add(Common.getFiller(fillerX, fillerY));
        mainPanel.add(heading);
        heading.setAlignmentX(Component.CENTER_ALIGNMENT);

        fillerY = ProjectConstants.FILLER_Y;
        mainPanel.add(Common.getFiller(fillerX, fillerY));
        mainPanel.add(userName);
        userName.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(Common.getFiller(fillerX, fillerY));
        mainPanel.add(password);
        password.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(Common.getFiller(fillerX, fillerY));
        mainPanel.add(loginBtn);
        loginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(Common.getFiller(fillerX, fillerY));
        mainPanel.add(registerLabel);
        registerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(Common.getFiller(fillerX, fillerY));
        mainPanel.add(registerBtn);
        registerBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Add the created panel to the main Frame
        this.add(mainPanel, BorderLayout.CENTER);
        mainPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //make the window visible.
        setVisible(true);
    }
}
