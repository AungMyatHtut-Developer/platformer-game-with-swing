package amh.character;

import java.awt.*;

public abstract class Character {

    protected float x;
    protected float y;
    protected int width, height;
    protected Rectangle hitBox;

    public Character(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;;
        this.height = height;
    }

    protected void initHitBox(int width, int height, int yOffset) {
        hitBox = new Rectangle((int) x, (int) y + yOffset, width, height);
    }

    public Rectangle getHitBox() {
        return this.hitBox;
    }

    public void drawHitBox(Graphics graphics) {
        graphics.setColor(Color.white);
        // currently hide hitbox
        //graphics.drawRect(hitBox.x, hitBox.y, hitBox.width, hitBox.height);
    }

    public abstract void update();

    public abstract void render(Graphics g);

    public abstract void loadAnimations();
}
