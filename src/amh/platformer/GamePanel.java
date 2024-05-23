package amh.platformer;

import amh.handler.KeyboardHandler;
import amh.handler.MouseHandler;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private MouseHandler mouseHandler;
    private int xDelta = 0, yDelta = 0;

    public GamePanel() {
        mouseHandler = new MouseHandler(this);

        addKeyListener(new KeyboardHandler(this));
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.fillRect(xDelta, yDelta,200,100);

        repaint();
    }

    public void changeXDelta(int value){
        this.xDelta+=value;
    }

    public void changeYDelta(int value) {
        this.yDelta+=value;
    }

    public void changeRecPosition(int x, int y) {
        this.xDelta = x;
        this.yDelta = y;
    }

}
