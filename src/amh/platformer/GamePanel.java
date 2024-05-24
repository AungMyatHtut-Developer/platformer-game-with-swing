package amh.platformer;

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
    private double xDir = 5, yDir = 5;
    private Color color = new Color(12,210,12);
    private Random random =  new Random();

    List<MyRectangle> rectList = new ArrayList<>();


    public GamePanel() {
        mouseHandler = new MouseHandler(this);

        addKeyListener(new KeyboardHandler(this));
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //Temp Rect
        for (MyRectangle myRectangle : rectList) {
            myRectangle.updateRect();
            myRectangle.draw(g);
        }

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

    public void spawnRect(int x, int y) {
        rectList.add(new MyRectangle(x,y));
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

    public class MyRectangle{
        int x,y,w,h;
        int xDir = 5, yDir = 5;
        Color color;

        public MyRectangle(int x, int y) {
            this.x = x;
            this.y = y;
            w = 10 + random.nextInt(70);
            h = w;
        }

        public void updateRect() {
            this.x += xDir;
            this.y += yDir;

            if ((x + w) > 700 || x < 0) {
                xDir *= -1;
                color = getNewColor();
            }

            if ((y + h) > 700 || y < 0) {
                yDir *= -1;
                color = getNewColor();
            }
        }

        public Color getNewColor(){
            int red = random.nextInt(255);
            int green = random.nextInt(255);
            int blue = random.nextInt(255);
            return new Color(red, green, blue);
        }

        public void draw(Graphics g) {
            g.setColor(color);
            g.fillRect(x, y, w, h);
        }

    }


}
