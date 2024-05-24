package amh.platformer;

import amh.handler.KeyboardHandler;
import amh.handler.MouseHandler;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GamePanel extends JPanel {

    private MouseHandler mouseHandler;
    private float xDelta = 10, yDelta = 10;
    private double xDir = 2, yDir = 2;
    private Color color = new Color(12,210,12);


    public GamePanel() {
        mouseHandler = new MouseHandler(this);

        addKeyListener(new KeyboardHandler(this));
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        updateRect();
        g.setColor(color);
        g.fillRect((int) xDelta, (int) yDelta, 200, 5);
    }

    private void updateRect() {
        xDelta+=xDir;
        if(xDelta > 200 || xDelta < 10){
            xDir *= -1;
            color = getRandomColor();
        }

        yDelta+=yDir;
        if (yDelta > 500 || yDelta < 10) {
            yDir *= -1;
            color = getRandomColor();
        }
    }

    private Color getRandomColor() {
        Random random = new Random();
        short red = (short) random.nextInt(255);
        short green = (short) random.nextInt(255);
        short blue = (short) random.nextInt(255);
        return new Color(red, green, blue);
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
