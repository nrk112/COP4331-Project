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
                try {
                    //Thread.sleep(3000);
                } catch (Exception e) {

                }
                //Start the program
                AccountManager.getInstance();
            }
        });

    }

}
