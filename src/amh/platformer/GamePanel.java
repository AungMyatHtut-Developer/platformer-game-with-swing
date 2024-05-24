package amh.platformer;

import amh.gun.Ball;
import amh.handler.KeyboardHandler;
import amh.handler.MouseHandler;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GamePanel extends JPanel {

    private MouseHandler mouseHandler;
    private float xDelta = 10, yDelta = 10;
    private double xDir = 0.5, yDir = 0.5;
    private Color color = new Color(12,210,12);


    private int startLineX= 300, startLineY = 300, endLineX = 700, endLineY = 300;
    private List<Ball> balls = new ArrayList<>();

    int frames = 0;
    long lastChecked = 0;

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

        g.fillRect(10, 10, 200, 5);

        g.fillOval((int) xDelta, (int) yDelta, 50, 50);

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));
        g.drawLine(startLineX, startLineY, endLineX, endLineY);

        g2.setColor(Color.BLACK);
        List<Ball> toRemove = new ArrayList<>();
        for (Ball ball : balls) {
            ball.update();
            if (ball.reachedTarget(endLineX, endLineY)) {
                toRemove.add(ball);
            } else {
                g2.fill(ball.getShape());
            }
        }
        balls.removeAll(toRemove);

        frames++;
        if (System.currentTimeMillis() - lastChecked >= 1000) {
            lastChecked = System.currentTimeMillis();
            System.out.println("FPS : "+ frames);
            frames = 0;
        }

        repaint();
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

    public void updateLinePointing(int x, int y) {
        this.endLineX = x;
        this.endLineY = y;
    }

    public void shootBall() {
        double angle = Math.atan2(endLineY - startLineY, endLineX - startLineX);
        balls.add(new Ball(startLineX, startLineY, angle));
    }

    public void updateLineBase(int x, int y) {
        startLineX += x;
        startLineY += y;
    }

}
