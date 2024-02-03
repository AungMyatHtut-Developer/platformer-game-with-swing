package amh.platformer;

import amh.platformer.inputs.KeyboardInputs;
import amh.platformer.inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GamePanel extends JPanel{

    private MouseInputs mouseInputs;
    private float xDelta = 100, yDelta = 100;
    private float xDir = 2f, yDir = 2f;
    private Color color = new Color(123,221,12);
    private int frames = 0;
    private long lastCheck = 0;
    private Random random;

    public GamePanel() {
        random = new Random();
        mouseInputs = new MouseInputs(this);

        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    /**
     * This method will draw graphics on JPanel
     * JPanel can't draw without the help of graphics
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        updateRectangle();
        g.setColor(color);
        g.fillRect( (int) xDelta, (int) yDelta,50,50);

    }

    public void changeXDelta(int value) {
        xDelta+= value;
    }
    public void changeYDelta(int value) {
        yDelta+= value;
    }

    public void setRecPos(int x, int y) {
        this.xDelta = x;
        this.yDelta = y;
    }

    public void updateRectangle() {
        xDelta += xDir;
        if(xDelta>400 || xDelta<0){
            xDir*= -1;
            color = getRandomColor();
        }

        yDelta += yDir;
        if (yDelta > 400 || yDelta < 0) {
            yDir*= -1;
            color = getRandomColor();
        }


    }

    public Color getRandomColor(){
        int r=random.nextInt(255) ,g=random.nextInt(255) ,b=random.nextInt(255);
        return new Color(r,g,b);
    }
}
