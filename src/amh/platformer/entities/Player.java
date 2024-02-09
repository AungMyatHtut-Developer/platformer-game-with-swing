package amh.platformer.entities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static amh.platformer.util.Constants.Directions.*;
import static amh.platformer.util.Constants.Directions.DOWN;
import static amh.platformer.util.Constants.PlayerConstants.*;

public class Player extends Entity {

    BufferedImage playerImage;
    BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 10;
    private int playerAction = IDLE;
    private boolean moving = false, attacking = false;
    private boolean left, right, up, down;
    private float playerMovingSpeed = 6.0f;

    public Player(float x, float y) {
        super(x, y);

        loadPirateAnimation();
    }

    public void update() {
        updatePosition();
        updateAnimation();
        setAnimation();

    }

    //load All Pirate Animations into Array
    private void loadPirateAnimation() {

        InputStream inputStream = getClass().getResourceAsStream("/player_sprites.png");

        try {
            playerImage = ImageIO.read(inputStream);

            animations = new BufferedImage[9][6];

            for (int j = 0; j < animations.length; j++)
                for (int i = 0; i < animations[j].length; i++)
                    animations[j][i] = playerImage.getSubimage(i * 64, j * 40, 64, 40);


        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void render(Graphics g) {
        g.drawImage(animations[playerAction][aniIndex], (int) x, (int) y, 128, 80, null);

    }


    private void updatePosition(){

        moving = false;

        if(left && !right){
            x-= playerMovingSpeed;
            moving = true;
        }else if(right && !left){
            x+= playerMovingSpeed;
            moving = true;
        }

        if(up && !down){
            y-= playerMovingSpeed;
            moving = true;
        }else if(down && !up){
            y+= playerMovingSpeed;
            moving = true;
        }
    }

    private void setAnimation() {
        int startAnimation = playerAction;

        if (moving)
            playerAction = RUNNING;
        else
            playerAction = IDLE;

        if(attacking)
            playerAction = ATTACK_1;

        if(startAnimation != playerAction)
            resetAnimationTick();
    }

    private void resetAnimationTick() {
        aniTick = 0;
        aniIndex = 0;
    }

    //update animation
    private void updateAnimation() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= getSpriteAmounts(playerAction)){
                aniIndex = 0;
                attacking = false;
            }

        }
    }

    public void setAttacking(boolean attacking){
        this.attacking =attacking;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return down;
    }

    public void resetDirBooleans() {
        left = false;
        right = false;
        up = false;
        down = false;
    }
}
