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
        initHitBox();
    }

    private void initHitBox() {
        hitBox = new Rectangle((int) x, (int) y, width, height);
    }

    protected void updateHitBox() {
        hitBox.x = (int) this.x;
        hitBox.y = (int) this.y;
    }

    public Rectangle getHitBox() {
        return this.hitBox;
    }

    public void drawHitBox(Graphics graphics) {
        graphics.setColor(Color.blue);
        graphics.drawRect((int) hitBox.x, (int) hitBox.y, hitBox.width, hitBox.height);
    }

    public abstract void update();

    public abstract void render(Graphics g);

    public abstract void loadAnimations();
}
