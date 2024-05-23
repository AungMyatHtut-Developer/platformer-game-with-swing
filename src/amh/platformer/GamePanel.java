package amh.platformer;

import amh.handler.KeyboardHandler;
import amh.handler.MouseHandler;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private MouseHandler mouseHandler;

    public GamePanel() {
        mouseHandler = new MouseHandler();

        addKeyListener(new KeyboardHandler());
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(10,10,200,100);
    }
}
