package view;

import Resources.ProjectConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The window where users can sign up for service.
 */
public class SignupView extends JFrame {

    private final String TITLE = "Shopazon - Signup";

    /**
     * Constructs and shows the signup view
     */
    public SignupView() {
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
        JLabel heading = new JLabel("Thank you for choosing Shopazon! Please fill in the following information to get started!");

        //Create the JTextFields
        JTextField userName = createTextField("User Name");
        JTextField streetAddress = createTextField("Street Address");
        JTextField city = createTextField("City");
        JTextField state = createTextField("State");
        JTextField zip = createTextField("Zip");

        //Create the password field
        JPasswordField password = new JPasswordField("Password");
        password.setMaximumSize(
                new Dimension(ProjectConstants.TEXTFIELD_WIDTH, password.getPreferredSize().height));

        //The will highlight the text when it gets focus since its pre-populated.
        password.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        password.selectAll();
                    }
                });
            }
        });

        //Create the Register button.
        JButton registerBtn = new JButton("Register");
        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO Check for all the validation of the fields here then call AccountManager.registerUser(). maybe pass this window handle have accountmanger return true when done then close window.

                //userName.getText()
                JOptionPane.showMessageDialog((Component) e.getSource(), userName.getText() + "Registration Failed! Please fill in all the forms!");

                //Close the window
                dispose();
            }
        });


        int fillerX = ProjectConstants.FILLER_X;
        int fillerY = 50;
        mainPanel.add(getFiller(fillerX, fillerY));
        mainPanel.add(heading);
        heading.setAlignmentX(Component.CENTER_ALIGNMENT);

        fillerY = ProjectConstants.FILLER_Y;
        mainPanel.add(getFiller(fillerX, fillerY));
        mainPanel.add(userName);

        mainPanel.add(getFiller(fillerX, fillerY));
        mainPanel.add(password);
        password.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(getFiller(fillerX, fillerY));
        mainPanel.add(streetAddress);

        mainPanel.add(getFiller(fillerX, fillerY));
        mainPanel.add(city);

        mainPanel.add(getFiller(fillerX, fillerY));
        mainPanel.add(state);

        mainPanel.add(getFiller(fillerX, fillerY));
        mainPanel.add(zip);

        mainPanel.add(getFiller(fillerX, fillerY));
        mainPanel.add(registerBtn);
        registerBtn.setAlignmentX(Component.CENTER_ALIGNMENT);



        //Add the created panel to the main Frame
        this.add(mainPanel, BorderLayout.CENTER);


        //make the window visible.
        setVisible(true);


    }

    /**
     * Creates a JTextField that will highlight the text when it gets focus.
     * @param label The temporary filler text.
     * @return the constructed JTextField.
     */
    private JTextField createTextField(String label) {

        JTextField textField = new JTextField(label);
        textField.setMaximumSize(
                new Dimension(ProjectConstants.TEXTFIELD_WIDTH, textField.getPreferredSize().height));

        //The will highlight the text when it gets focus since its pre-populated.
        textField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        textField.selectAll();
                    }
                });
            }
        });

        textField.setAlignmentX(Component.CENTER_ALIGNMENT);
        return textField;
    }

    /**
     * Creates a component that can be used to insert whitespace around GUI elements.
     * @param x the amount of whitespace pixels along the x axis.
     * @param y the amount of whitespace pixels along the y axis.
     * @return the Filler component to add to another component for whitespace.
     */
    private Component getFiller(int x, int y) {
        Dimension minSize = new Dimension(x, y);
        Dimension prefSize = new Dimension(x, y);
        Dimension maxSize = new Dimension(x, y);
        return new Box.Filler(minSize, prefSize, maxSize);
    }
}
