package Resources;

import java.awt.*;

/**
 * Holds any common constant variables that will be used throughout the project.
 */
public interface ProjectConstants {

    /**
     * @WINDOW_WIDTH The standard window width
     */
    int WINDOW_WIDTH = 640;

    /**
     * @WINDOW_HEIIGHT The standard window height
     */
    int WINDOW_HEIGHT = 480;

    /**
     * @TEXTFIELD_WIDTH The standard width for most JTextField objects
     */
    int TEXTFIELD_WIDTH = 350;

    /**
     * @FILLER_X The amount of pixels on the X axis to use as spacing between view objects.
     */
    int FILLER_X = 5;

    /**
     * @FILLER_Y The amount of pixels on the Y axis to use as spacing between view objects.
     */
    int FILLER_Y = 10;
    
    /**
     * @SALES_TAX the sales tax in Florida
     */
    double SALES_TAX = .06;

    Font TITLE_FONT = new Font("Calibri", Font.BOLD, 24);

    /**
     * @USER_FILE The location and name of the text file to store the users information in.
     */
    String USER_FILE = "users.txt";

    /**
     * @TRANSACTION_FILE The location and name of the text file to store the transaction information in.
     */
    String TRANSACTION_FILE = "transactions.txt";

    /**
     * @PRODUCT_FILE The location and name of the text file to store the product information in.
     */
    String PRODUCT_FILE = "products.txt";

    /**
     * @SCROLL_SPEED The speed that the scroll bars will scroll.
     */
    int SCROLL_SPEED = 16;


}
