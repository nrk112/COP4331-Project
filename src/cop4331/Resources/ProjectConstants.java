package cop4331.Resources;

import java.awt.*;

/**
 * Holds any common constant variables that will be used throughout the project.
 */
public interface ProjectConstants {

    /**
     * The standard window width
     */
    int WINDOW_WIDTH = 800;

    /**
     * The standard window height
     */
    int WINDOW_HEIGHT = 600;

    /**
     * The standard width for most JTextField objects
     */
    int TEXTFIELD_WIDTH = 350;

    /**
     * The amount of pixels on the X axis to use as spacing between cop4331.view objects.
     */
    int FILLER_X = 5;

    /**
     * The amount of pixels on the Y axis to use as spacing between cop4331.view objects.
     */
    int FILLER_Y = 10;

    /**
     * the sales tax in Florida
     */
    double SALES_TAX = .06;

    /**
     * the font to use for large title text.
     */
    Font TITLE_FONT = new Font("Calibri", Font.BOLD, 24);

    /**
     * a medium sized font for tables and such.
     */
    Font MEDIUM_FONT = new Font("Calibri", Font.PLAIN, 16);

    /**
     * the height to use for all table rows.
     */
    int TABLE_HEIGHT = 28;

    /**
     * The location and name of the text file to store the users information in.
     */
    String USER_FILE = "database\\users.txt";

    /**
     * The location and name of the text file to store the transaction information in.
     */
    String TRANSACTION_FILE = "database\\transactions.txt";

    /**
     * The location and name of the text file to store the product information in.
     */
    String PRODUCT_FILE = "database\\products.txt";

    /**
     * The speed that the scroll bars will scroll.
     */
    int SCROLL_SPEED = 16;

    /**
     * Sets the default image file for use when one is not supplied.
     */
    String DEFAULT_IMAGE_FILE = "database\\images\\default.jpg";
}
