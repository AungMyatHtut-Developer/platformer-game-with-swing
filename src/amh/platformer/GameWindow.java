package amh.platformer;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public class GameWindow {

    private JFrame jFrame;
    private static final int WIDTH = 700;
    private static final int HEIGHT = 700;

    public GameWindow(GamePanel gamePanel) {
        jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setSize(WIDTH, HEIGHT);
        jFrame.setLocationRelativeTo(null); // this will show our game window in the center of the screen
        jFrame.add(gamePanel);
        jFrame.setVisible(true);
    }
}
