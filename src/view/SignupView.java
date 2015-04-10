package view;

import Resources.Common;
import Resources.ProjectConstants;
import controller.AccountManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The window where users can sign up for service.
 */
public class SignupView extends JFrame {

    private final String TITLE = "Shopazon - Signup";

    private final JTextField fullName;
    private final JTextField userName;
    private final JPasswordField password;
    private final JTextField streetAddress;
    private final JTextField city;
    private final JTextField state;
    private final JTextField zip;

    /**
     * Constructs and shows the signup view
     */
    public SignupView() {
        setTitle(TITLE);
        setSize(ProjectConstants.WINDOW_WIDTH, ProjectConstants.WINDOW_HEIGHT);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        //Open the window in the center of the screen.
        setLocationRelativeTo(null);

        //Make the main JPanel to use in the Frame
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(
                new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        //Create the heading
        JLabel heading = new JLabel("Thank you for choosing Shopazon! Please fill in the following information to get started!");

        //Create the JTextFields
        fullName = Common.createTextField("Full Name");
        userName = Common.createTextField("User Name");
        streetAddress = Common.createTextField("Street Address");
        city = Common.createTextField("City");
        state = Common.createTextField("State");
        zip = Common.createTextField("Zip");

        //Create the password field
        password = new JPasswordField("Password");
        password.setMaximumSize(
                new Dimension(ProjectConstants.TEXTFIELD_WIDTH, password.getPreferredSize().height));
        password.setEchoChar((char)0);

        //The will highlight the text and hide it when it gets focus since its pre-populated.
        password.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        password.selectAll();
                        password.setEchoChar('*');
                    }
                });
            }
        });


        //Create the radio button group
        JPanel radioPanel = new JPanel(new GridLayout(0, 1));

        //Create the buttons
        final JRadioButton buyerButton = new JRadioButton("I am a Buyer");
        buyerButton.setMnemonic(KeyEvent.VK_B);
        buyerButton.setSelected(true);
        final JRadioButton sellerButton = new JRadioButton("I am a Seller");
        sellerButton.setMnemonic(KeyEvent.VK_S);

        //Create the button group
        ButtonGroup group = new ButtonGroup();
        group.add(buyerButton);
        group.add(sellerButton);

        //Add the buttons to the panel
        radioPanel.add(buyerButton);
        radioPanel.add(sellerButton);

        //Set panel specs
        radioPanel.setMaximumSize(new Dimension(100, 40));
        radioPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Create the Register button.
        final JButton registerBtn = new JButton("Register");
        //Allow enter to press the button at any time.
        this.getRootPane().setDefaultButton(registerBtn);
        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //If the input is valid, send the data to create a user.
                if (validateFields()) {
                    //Check if they are registering as a buyer or seller.
                        AccountManager.getInstance().createUser(
                                AccountManager.getInstance().getNewUserId(),
                                fullName.getText(),
                                userName.getText(),
                                new String(password.getPassword()),
                                streetAddress.getText(),
                                city.getText(),
                                state.getText(),
                                zip.getText(),
                                sellerButton.isSelected()
                        );

                    JOptionPane.showMessageDialog(null, "Success! Please log in.","", JOptionPane.PLAIN_MESSAGE);

                    //Save users to file
                    AccountManager.getInstance().writeUsersToFile();

                    //Close the window
                    dispose();

                //Otherwise give the error message and let them try again.
                } else {
                    JOptionPane.showMessageDialog(null, "Registration Failed! Please fill in all the forms properly!", "", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        //Add all the components to the main panel
        int fillerX = ProjectConstants.FILLER_X;
        int fillerY = 50;

        mainPanel.add(Common.getFiller(fillerX, fillerY));
        mainPanel.add(heading);
        heading.setAlignmentX(Component.CENTER_ALIGNMENT);

        fillerY = ProjectConstants.FILLER_Y;
        mainPanel.add(Common.getFiller(fillerX, fillerY));
        mainPanel.add(fullName);

        fillerY = ProjectConstants.FILLER_Y;
        mainPanel.add(Common.getFiller(fillerX, fillerY));
        mainPanel.add(userName);

        mainPanel.add(Common.getFiller(fillerX, fillerY));
        mainPanel.add(password);
        password.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(Common.getFiller(fillerX, fillerY));
        mainPanel.add(streetAddress);

        mainPanel.add(Common.getFiller(fillerX, fillerY));
        mainPanel.add(city);

        mainPanel.add(Common.getFiller(fillerX, fillerY));
        mainPanel.add(state);

        mainPanel.add(Common.getFiller(fillerX, fillerY));
        mainPanel.add(zip);

        mainPanel.add(Common.getFiller(fillerX, fillerY));
        mainPanel.add(radioPanel);

        mainPanel.add(Common.getFiller(fillerX, fillerY));
        mainPanel.add(registerBtn);
        registerBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

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
        return (
                //fullName.getText().matches("[a-zA-Z]+[ ][a-zA-Z]+") &&
                //userName.getText().matches("[A-Za-z0-9]+") &&
                //password.getPassword().toString().matches("") &&
                //streetAddress.getText().matches("[0-9]+[ ][A-Za-z0-9]+(.+)?") &&
                //city.getText().matches("[A-Za-z[ ]]+") &&
                //state.getText().matches("[A-Za-z]+") &&
                zip.getText().matches("[0-9]{5}")
                );
    }
}
