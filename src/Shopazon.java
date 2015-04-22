import cop4331.controller.AccountManager;

import java.awt.*;

/**
 * The entry point for the program.
 */
public class Shopazon {

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                //Start the program
                AccountManager.getInstance();
            }
        });
    }
}
