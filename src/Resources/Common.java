/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Resources;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author Lenore
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
}
