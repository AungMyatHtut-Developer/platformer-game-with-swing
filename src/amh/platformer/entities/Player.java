package amh.platformer.entities;

import amh.platformer.Game;
import amh.platformer.util.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static amh.platformer.util.Constants.PlayerConstants.*;
import static amh.platformer.util.HelpMethods.canMoveHere;
import static amh.platformer.util.LoadSave.getAtlas;

public class Player extends Entity {

    BufferedImage playerImage;
    BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 10;
    private int playerAction = IDLE;
    private boolean moving = false, attacking = false;
    private boolean left, right, up, down;
    private float playerMovingSpeed = 2.0f;
    private int[][] lvlData;
    private float xDrawOffset = 21 * Game.SCALE;
    private float yDrawOffset = 4 * Game.SCALE;

    public Player(float x, float y, int width, int height) {
        super(x, y, width,height);

        loadPirateAnimation();
        initHitBox(x,y, 20*Game.SCALE, 28*Game.SCALE);
    }

    public void update() {
        updatePosition();
//        updateHitBox();
        updateAnimation();
        setAnimation();
    }

    public void render(Graphics g) {
        g.drawImage(animations[playerAction][aniIndex], (int) (hitBox.x -  xDrawOffset), (int)(hitBox.y -  yDrawOffset), width, height, null);
        drawHitBox(g);
    }

    //load All Pirate Animations into Array
    private void loadPirateAnimation() {

            playerImage = getAtlas(LoadSave.PLAYER_ATLAS);

            animations = new BufferedImage[9][6];

            for (int j = 0; j < animations.length; j++)
                for (int i = 0; i < animations[j].length; i++)
                    animations[j][i] = playerImage.getSubimage(i * 64, j * 40, 64, 40);

    }




    private void updatePosition(){

        moving = false;

        if(!left & !right & !up & !down)
            return;

        float xSpeed = 0, ySpeed = 0;

        if(left & !right)
            xSpeed = -playerMovingSpeed;
        else if(right && !left)
            xSpeed = playerMovingSpeed;

        if(up & !down)
            ySpeed = -playerMovingSpeed;
        else if(down & !up)
            ySpeed = playerMovingSpeed;


//        if(canMoveHere(x+xSpeed, y+ySpeed, width, height, lvlData)){
//            this.x += xSpeed;
//            this.y += ySpeed;
//            moving = true;
//        }

        if(canMoveHere(hitBox.x+xSpeed, hitBox.y+ySpeed, hitBox.width, hitBox.height, lvlData)){
            hitBox.x += xSpeed;
            hitBox.y += ySpeed;
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

    public void loadLvlData(int[][] lvlData){
        this.lvlData = lvlData;
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
