package amh.gun;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ball {

    private static final int SIZE = 30;
    private static final int SPEED = 5;
    private double x,y;
    private double angle;

    public Ball(double x, double y, double angle) {
        this.x = x;
        this.y = y;
        this.angle = angle;
    }

    public Ball() {

    }

    public void update() {
        x += SPEED * Math.cos(angle);
        y += SPEED * Math.sin(angle);
    }

    public boolean reachedTarget(int targetX, int targetY) {
        return Math.hypot(x - targetX, y - targetY) <= SIZE;
    }

    public Shape getShape() {
        return new Ellipse2D.Double(x - SIZE / 2, y - SIZE / 2, SIZE, SIZE);
    }
}
