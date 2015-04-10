
package Resources;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 *Has functions that are common to more than one other class.
 */
public class Common {
   
    /**
     * Creates a component that can be used to insert whitespace around GUI elements.
     * @param x the amount of whitespace pixels along the x axis.
     * @param y the amount of whitespace pixels along the y axis.
     * @return the Filler component to add to another component for whitespace.
     */
    public static Component getFiller(int x, int y) {
        Dimension size = new Dimension(x, y);
        return new Box.Filler(size, size, size);//Min Max and Preferred are all the same
    }
    
    /**
     * Creates a JTextField that will highlight the text when it gets focus.
     * @param label The temporary filler text.
     * @return the constructed JTextField.
     */
    public static JTextField createTextField(String label) {

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

        textField.setAlignmentX(Component.CENTER_ALIGNMENT);
        return textField;
    }

    /**
     * Write blank files for users, products and transactions.
     * @return true if successful
     */
    public static boolean resetDatabase() {
        boolean success = true;
        boolean deleted;

        try {
            //Reset the user File.
            File file = new File(ProjectConstants.USER_FILE);
            deleted = file.delete();
            if (!file.createNewFile())
                success = false;

            //Reset the Product file.
            file = new File(ProjectConstants.PRODUCT_FILE);
            deleted = file.delete();
            if (!file.createNewFile())
                success = false;

            //Reset the transaction file.
            file = new File(ProjectConstants.TRANSACTION_FILE);
            deleted = file.delete();
            if (!file.createNewFile())
                success = false;

        } catch (IOException e) {
            System.out.println("Reset failed!");
            e.printStackTrace();
        }
        return success;
    }
}
