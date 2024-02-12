package amh.platformer.entities;

import amh.platformer.util.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static amh.platformer.util.Constants.PlayerConstants.*;
import static amh.platformer.util.LoadSave.getAtlas;

public class Player extends Entity {

    BufferedImage playerImage;
    BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 10;
    private int playerAction = IDLE;
    private boolean moving = false, attacking = false;
    private boolean left, right, up, down;
    private float playerMovingSpeed = 2.0f;

    public Player(float x, float y, int width, int height) {
        super(x, y, width,height);

        loadPirateAnimation();
    }

    public void update() {
        updatePosition();
        updateAnimation();
        setAnimation();
    }

    //load All Pirate Animations into Array
    private void loadPirateAnimation() {

            playerImage = getAtlas(LoadSave.PLAYER_ATLAS);

            animations = new BufferedImage[9][6];

            for (int j = 0; j < animations.length; j++)
                for (int i = 0; i < animations[j].length; i++)
                    animations[j][i] = playerImage.getSubimage(i * 64, j * 40, 64, 40);

    }

    public void render(Graphics g) {
        g.drawImage(animations[playerAction][aniIndex], (int) x, (int) y, width, height, null);
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

        if(playerAction == ATTACK_1){
            aniSpeed = 3;
        }

        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= getSpriteAmounts(playerAction)){
                aniIndex = 0;
                attacking = false;
                aniSpeed = 10;
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

    public void resetDirBooleans() {
        left = false;
        right = false;
        up = false;
        down = false;
    }
}
