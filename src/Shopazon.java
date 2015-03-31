import view.LoginView;

import java.awt.*;

/**
 * Created by Nick on 3/30/2015.
 */
public class Shopazon {

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                LoginView mainProgram = new LoginView();
            }
        });

    }

}
