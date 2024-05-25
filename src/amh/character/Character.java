package amh.character;

import java.awt.*;

public abstract class Character {

    protected float x;
    protected float y;

    public Character(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public abstract void update();

    public abstract void render(Graphics g);

    public abstract void loadAnimations();
}
