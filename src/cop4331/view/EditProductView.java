package cop4331.view;

import cop4331.Resources.Common;
import cop4331.Resources.ProjectConstants;
import cop4331.controller.InventoryManager;
import cop4331.model.DiscountedProduct;
import cop4331.model.Product;
import cop4331.model.Seller;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Displays the cop4331.view where the user can edit a product for sale.
 */
public class EditProductView extends JDialog {

    private final String TITLE = "Shopazon - Edit Existing Product";

    //Labels for the text fields.
    private final JLabel nameLabel;
    private final JLabel descriptionLabel;
    private final JLabel costLabel;
    private final JLabel priceLabel;
    private final JLabel quantityLabel;
    private final JLabel discountedByLabel;

    //Strings for the text field labels
    private final String nameString = "Name: ";
    private final String descriptionString = "Description: ";
    private final String costString = "Cost: ";
    private final String priceString = "Price: ";
    private final String quantityString = "Quantity: ";
    private final String discountedByString = "% Discount: ";

    //The text fields
    private final JTextField name;
    private final JTextField description;
    private final JTextField cost;
    private final JTextField price;
    private final JTextField quantity;
    private final JTextField discountedBy;

    //Other
    private final JFileChooser fileChooser;
    private final JLabel imageThumbnail;
    private File imageFile = null;
    private Product product;
    private String imagePath;

    /**
     * Constructs and shows the add product cop4331.view cop4331.view
     * @param user Current instance of the seller object
     * @param productID The ID of the product to edit.
     */
    public EditProductView(final int productID, final Seller user) {

        //Get the reference to the product to edit.
        product = InventoryManager.getInstance().getProductByID(productID);

        //Set the window properties
        this.setModal(true);
        this.setTitle(TITLE);
        this.setSize(ProjectConstants.WINDOW_WIDTH, ProjectConstants.WINDOW_HEIGHT);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);

        //Create the heading
        JLabel heading = new JLabel("Editing: " + product.getName() + ".");
        heading.setFont(ProjectConstants.TITLE_FONT);

        //Set up the JTextFields
        int columns = 60;
        name = Common.createTextField(product.getName());
        name.setColumns(columns);

        description = Common.createTextField(product.getDescription());
        description.setColumns(columns);

        cost = Common.createTextField(Double.toString(product.getCost()));
        cost.setColumns(columns);

        price = Common.createTextField(Double.toString(product.getPrice()));
        price.setColumns(columns);

        quantity = Common.createTextField(Integer.toString(product.getQuantity()));
        quantity.setColumns(columns);

        discountedBy = Common.createTextField("0.0");
        if (product instanceof DiscountedProduct) {
            String discountTitle = Double.toString(((DiscountedProduct)product).getDiscountedBy());
            discountedBy.setText(discountTitle);
        }
        discountedBy.setColumns(columns);

        //Set up the labels for the text fields
        nameLabel = new JLabel(nameString);
        nameLabel.setLabelFor(name);
        nameLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);

        descriptionLabel = new JLabel(descriptionString);
        descriptionLabel.setLabelFor(description);
        descriptionLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);

        costLabel = new JLabel(costString);
        costLabel.setLabelFor(cost);
        costLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);

        priceLabel = new JLabel(priceString);
        priceLabel.setLabelFor(price);
        priceLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);

        quantityLabel = new JLabel(quantityString);
        quantityLabel.setLabelFor(quantity);
        quantityLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);

        discountedByLabel = new JLabel(discountedByString);
        discountedByLabel.setLabelFor(discountedBy);
        discountedByLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);

        int fillerX = ProjectConstants.FILLER_X;
        int fillerY = ProjectConstants.FILLER_Y + 4;
        //Put the labels in a panel.
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
        labelPanel.add(nameLabel);
        labelPanel.add(Common.getFiller(fillerX, fillerY));
        labelPanel.add(descriptionLabel);
        labelPanel.add(Common.getFiller(fillerX, fillerY));
        labelPanel.add(costLabel);
        labelPanel.add(Common.getFiller(fillerX, fillerY));
        labelPanel.add(priceLabel);
        labelPanel.add(Common.getFiller(fillerX, fillerY));
        labelPanel.add(quantityLabel);
        labelPanel.add(Common.getFiller(fillerX, fillerY));
        labelPanel.add(discountedByLabel);
        fillerY = ProjectConstants.FILLER_Y + 27;
        labelPanel.add(Common.getFiller(fillerX, fillerY));

        fillerY = ProjectConstants.FILLER_Y;
        //Put the text fields in a panel.
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.Y_AXIS));
        fieldPanel.add(name);
        fieldPanel.add(Common.getFiller(fillerX, fillerY));
        fieldPanel.add(description);
        fieldPanel.add(Common.getFiller(fillerX, fillerY));
        fieldPanel.add(cost);
        fieldPanel.add(Common.getFiller(fillerX, fillerY));
        fieldPanel.add(price);
        fieldPanel.add(Common.getFiller(fillerX, fillerY));
        fieldPanel.add(quantity);
        fieldPanel.add(Common.getFiller(fillerX, fillerY));
        fieldPanel.add(discountedBy);
        fieldPanel.add(Common.getFiller(fillerX, fillerY));

        //Put the labels and fields together
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));
        centerPanel.add(labelPanel);
        //centerPanel.add(Common.getFiller(fillerX, fillerY));
        centerPanel.add(fieldPanel);

        //Create the image icon
        imageThumbnail = new JLabel();
        imagePath = product.getImage();
        ImageIcon imageIcon = new ImageIcon(imagePath);
        imageThumbnail.setIcon(imageIcon);

        //Create the file chooser.
        fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif");
        fileChooser.setFileFilter(filter);
        File workingDirectory = new File(System.getProperty("user.dir"));
        fileChooser.setCurrentDirectory(workingDirectory);

        //Create the Save button.
        final JButton btnSave = new JButton("Save");
        //Allow enter to press the button at any time.
        this.getRootPane().setDefaultButton(btnSave);
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (validateFields()) {
                    InventoryManager.getInstance().editProductByID(
                            productID,
                            name.getText(),
                            description.getText(),
                            Double.parseDouble(cost.getText()),
                            Double.parseDouble(price.getText()),
                            Integer.parseInt(quantity.getText()),
                            Double.parseDouble(discountedBy.getText()),
                            imagePath
                    );
                    user.populateTransactions();
                    dispose();
                    //Otherwise give the error message and let them try again.
                } else {
                    JOptionPane.showMessageDialog(null, "There was a problem editing your product. Please make sure you filled in all the fields with valid information.", "", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        //Image chooser button
        final JButton btnImageChooser = new JButton("Select Image");
        btnImageChooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = fileChooser.showDialog(getContentPane(), "Open");
                if (result == JFileChooser.APPROVE_OPTION) {
                    imageFile = fileChooser.getSelectedFile();
                    imagePath = imageFile.getPath();
                    //Update the image
                    ImageIcon imageIcon = new ImageIcon(imageFile.getPath());
                    imageThumbnail.setIcon(imageIcon);
                }
            }
        });


        //Make the button panel.
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());
        //buttonPanel.add(btnImageChooser);
        buttonPanel.add(btnSave, BorderLayout.EAST);

        fieldPanel.add(btnImageChooser);
        btnImageChooser.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Make the main JPanel to use in the Frame
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setLayout(new BorderLayout());

        //Add all the components to the main panel
        mainPanel.add(heading, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(imageThumbnail, BorderLayout.EAST);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        //Add the created panel to the main Frame
        this.add(mainPanel);

        //make the window visible.
        setVisible(true);
    }

    /**
     * Validates the text fields.
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
                        quantity.getText().matches("\\d+")
        );
    }
}
