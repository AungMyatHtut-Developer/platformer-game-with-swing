package amh.platformer;

import javax.swing.*;

public class GameWindow {

    private JFrame jFrame;

    public GameWindow(GamePanel gamePanel) {
        jFrame = new JFrame();

        jFrame.setSize(400, 400);
        jFrame.setDefaultCloseOperation(3);
        jFrame.setLocationRelativeTo(null);
        jFrame.add(gamePanel);
        jFrame.setVisible(true);

    }

}
