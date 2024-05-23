package amh.platformer;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public class GameWindow {

    private JFrame jFrame;
    private static final int WIDTH = 500;
    private static final int HEIGHT = 300;

    public GameWindow(GamePanel gamePanel) {
        jFrame = new JFrame();
        jFrame.setSize(WIDTH, HEIGHT);
        jFrame.add(gamePanel);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
}
