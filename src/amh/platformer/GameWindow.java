package amh.platformer;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public class GameWindow {

    private JFrame jFrame;

    public GameWindow(GamePanel gamePanel) {
        jFrame = new JFrame();

        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.add(gamePanel);
        jFrame.setResizable(false);
        jFrame.pack(); //Causes this Window to be sized to fit the preferred size and layouts of its subcomponents
        jFrame.setLocationRelativeTo(null); // this will show our game window in the center of the screen
        jFrame.setVisible(true);
        jFrame.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                System.out.println("Got Focus Back!");
                gamePanel.requestFocusInWindow();
            }

            @Override
            public void windowLostFocus(WindowEvent e) {
               gamePanel.getOurGame().windowFocusLost();
            }
        });
    }
}
