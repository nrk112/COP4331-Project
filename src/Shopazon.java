import controller.AccountManager;

import java.awt.*;

/**
 * The entry point for the program.
 */
public class Shopazon {

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                //Call the getInstance function to have it create an instance of itself.
                AccountManager.getInstance();
                //Construct the Account manager and load the login window.
                AccountManager.getInstance();
            }
        });

    }

}
