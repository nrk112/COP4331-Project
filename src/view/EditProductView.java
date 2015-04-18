package view;

import Resources.Common;
import Resources.ProjectConstants;
import controller.InventoryManager;
import model.DiscountedProduct;
import model.Product;
import model.Seller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 *
 */
public class EditProductView extends JDialog {

    private final String TITLE = "Shopazon - Edit Existing Product";

    private final JTextField name;
    private final JTextField description;
    private final JTextField cost;
    private final JTextField price;
    private final JTextField quantity;
    private final JTextField discountedBy;
    private final Seller seller;
    private final JTextField image;
    private final JFileChooser fileChooser;
    private File imageFile = null;
    private Product product;

    /**
     * Constructs and shows the add product view view
     * @param user Current instance of the seller object
     */
    public EditProductView(int productID, Seller user) {

        product = InventoryManager.getInstance().getProductByID(productID);

        setModal(true);
        this.seller = user;
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
        JLabel heading = new JLabel("Please fill in the following information for your product:");

        //Create the JTextFields
        name = Common.createTextField(product.getName());
        description = Common.createTextField(product.getDescription());
        cost = Common.createTextField(Double.toString(product.getCost()));
        price = Common.createTextField(Double.toString(product.getPrice()));
        quantity = Common.createTextField(Integer.toString(product.getQuantity()));
        discountedBy = Common.createTextField("");
        if (product instanceof DiscountedProduct) {
            String discountTitle = Double.toString(((DiscountedProduct)product).getDiscountedBy());
            discountedBy.setText(discountTitle);
        }

        image = Common.createTextField("Image file name (image.jpg)");

        //Create the file chooser.
        fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));

        //Create the Save button.
        final JButton btnSave = new JButton("Save");
        //Allow enter to press the button at any time.
        this.getRootPane().setDefaultButton(btnSave);
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (validateFields()) {
                    //TODO: Figure out where to edit the product. Here or in inventory manager.
                    /*InventoryManager.getInstance().editProduct(productID,
                            seller.getID(),
                            name.getText(),
                            description.getText(),
                            Double.parseDouble(cost.getText()),
                            Double.parseDouble(price.getText()),
                            Integer.parseInt(quantity.getText()),
                            Double.parseDouble(discountedBy.getText()),
                            imageFile.getPath()
                    );*/
                    dispose();
                    //Otherwise give the error message and let them try again.
                } else {
                    JOptionPane.showMessageDialog(null, "Could not save product, please verify all information is provided", "", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        //Create the Cancel button.
        final JButton btnCancel = new JButton("Cancel");
        //Allow enter to press the button at any time.
        this.getRootPane().setDefaultButton(btnCancel);
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        //Image chooser button
        final JButton btnImageChooser = new JButton("Select Image");
        btnImageChooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = fileChooser.showDialog(getContentPane(),"Open");
                if(result == JFileChooser.APPROVE_OPTION) {
                    imageFile = fileChooser.getSelectedFile();
                }
            }
        });

        //JPanel textFieldPanel = new JPanel(new GridLayout(0,2, 20, ProjectConstants.FILLER_Y));
        //textFieldPanel.add(new JLabel("Name:"));
        //textFieldPanel.add(name);

        //Add all the components to the main panel
        int fillerX = ProjectConstants.FILLER_X;
        int fillerY = ProjectConstants.FILLER_Y;

        mainPanel.add(Common.getFiller(fillerX, fillerY));
        mainPanel.add(heading);
        heading.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(Common.getFiller(fillerX, fillerY));
        mainPanel.add(name);

        mainPanel.add(Common.getFiller(fillerX, fillerY));
        mainPanel.add(description);

        mainPanel.add(Common.getFiller(fillerX, fillerY));
        mainPanel.add(cost);

        mainPanel.add(Common.getFiller(fillerX, fillerY));
        mainPanel.add(price);

        mainPanel.add(Common.getFiller(fillerX, fillerY));
        mainPanel.add(quantity);

        mainPanel.add(Common.getFiller(fillerX, fillerY));
        mainPanel.add(discountedBy);

        mainPanel.add(Common.getFiller(fillerX, fillerY));
        mainPanel.add(btnImageChooser);
        btnImageChooser.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(Common.getFiller(fillerX, fillerY));
        mainPanel.add(btnSave);
        btnSave.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(Common.getFiller(fillerX, fillerY));
        mainPanel.add(btnCancel);
        btnCancel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Add the created panel to the main Frame
        //this.add(textFieldPanel, BorderLayout.NORTH);
        this.add(mainPanel, BorderLayout.CENTER);

        //make the window visible.
        setVisible(true);
    }

    private void loadProductInfo() {

    }

    /**
     * Validates the registration text fields.
     * @return True if all fields are valid.
     */
    private boolean validateFields() {
        if (imageFile == null) {
            imageFile = new File(ProjectConstants.DEFAULT_IMAGE_FILE);
        }
        return (
                !name.getText().isEmpty() &&
                        cost.getText().matches("\\d+(\\.\\d{1,2})?") &&
                        price.getText().matches("\\d+(\\.\\d{1,2})?") &&
                        discountedBy.getText().matches("\\d+(\\.\\d{1,2})?") &&
                        quantity.getText().matches("\\d+") &&
                        !image.getText().isEmpty()
        );
    }

    /**
     * Creates a JTextField that will highlight the text when it gets focus.
     * @param label The temporary filler text.
     * @return the constructed JTextField.
     */
    public static JPanel createTextField(String name, String label) {

        final JPanel panel = new JPanel();
        panel.setMinimumSize(new Dimension(400, 28));
        final JLabel nameLabel = new JLabel(name);
        final JTextField textField = new JTextField(label);
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
        //textField.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(nameLabel);
        panel.add(textField);
        return panel;
    }
}
