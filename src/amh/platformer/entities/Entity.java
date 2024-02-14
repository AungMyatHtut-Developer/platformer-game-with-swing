package amh.platformer.entities;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public abstract class Entity {

    protected float x,y;
    protected int width, height;
    protected Rectangle2D.Float hitBox;

    public Entity(float x, float y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    protected void initHitBox(float x, float y, float width, float height) {
        hitBox = new Rectangle2D.Float((int)x,(int)y,width,height);
    }

    protected void drawHitBox(Graphics g) {
        g.setColor(Color.BLUE);
        g.drawRect((int)hitBox.x,(int)hitBox.y, (int)hitBox.width, (int)hitBox.height);
    }

    protected void updateHitBox(){
        hitBox.x = (int) x;
        hitBox.y = (int) y;
    }

    public Rectangle2D.Float getHitBox(){
        return hitBox;
    }
}
