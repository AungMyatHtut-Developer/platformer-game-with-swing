package amh.character;

import amh.platformer.Game;
import amh.util.SpriteLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

import static amh.platformer.Game.GAME_HEIGHT;
import static amh.util.Constant.PlayerConstant.*;
import static amh.util.Constant.PlayerConstant.IDLE;
import static amh.util.HelperMethods.CanMoveHere;

public class Player extends Character {

    private BufferedImage[][] playerAnimations;

    // player animation
    public static final byte PLAYER_IMG_WIDTH = 92;
    public static final byte PLAYER_IMG_HEIGHT = 92;
    private int aniSpeed = 10, aniTick, aniIndex;
    private int playerAction = CLIMBING;
    private boolean isPlayerMovingRight = true;
    private boolean moving = false, attack = false, isHurt = false;
    private boolean climbing = false;
    private boolean left, up, right, down, jump;
    private float playerSpeed = 2.0f;
    private byte attackNumber = 0;
    private static final byte Y_OFFSET = 20;

    private int [] levelData;


    // Gravity and Jumping
    private static final float GRAVITY = 0.38f;
    private static final float JUMP_STRENGTH = 10.0f;
    private float yVelocity = 0;
    private boolean onGround = false;
    private boolean isJumping = false;


    public Player(float x, float y) {
        super(x, y, PLAYER_IMG_WIDTH, PLAYER_IMG_HEIGHT);
        loadAnimations();
        initHitBox(PLAYER_IMG_WIDTH - 58, PLAYER_IMG_HEIGHT - 22 , Y_OFFSET);
    }

    @Override
    public void update() {
        updatePosition();
        setAnimation();
        updateAnimation();
    }

    @Override
    public void render(Graphics g) {
        if (isPlayerMovingRight) {
            // Draw original image
//            if(!climbing){
//                y -= Y_OFFSET;
//            }
            g.drawImage(playerAnimations[playerAction][aniIndex], (int) x - 10, (int) y, PLAYER_IMG_WIDTH, PLAYER_IMG_HEIGHT, null);
//            g.drawRect((int) x, (int) y,PLAYER_IMG_WIDTH, PLAYER_IMG_HEIGHT);
        }

        if (!isPlayerMovingRight) {

            Graphics2D g2D = (Graphics2D) g;

            // Save the current transform
            g2D.translate((int) x + PLAYER_IMG_WIDTH / 2, (int) y + PLAYER_IMG_HEIGHT / 2);
            g2D.scale(-1, 1); // Flip horizontally
            g2D.translate(-(int) x - PLAYER_IMG_WIDTH / 2, -(int) y - PLAYER_IMG_HEIGHT / 2);

//            if(!climbing){
//                y -= Y_OFFSET;
//            }
            // Draw the flipped image
            g2D.drawImage(playerAnimations[playerAction][aniIndex], (int) x + 44, (int) y, PLAYER_IMG_WIDTH, PLAYER_IMG_HEIGHT, null);
//            g.drawRect((int) x, (int) y,PLAYER_IMG_WIDTH, PLAYER_IMG_HEIGHT);

            // Reset transform
            g2D.setTransform(g2D.getDeviceConfiguration().getDefaultTransform());
        }

        drawHitBox( g);

    }

    @Override
    public void loadAnimations() {
        BufferedImage playerImage = SpriteLoader.getSprite(SpriteLoader.PLAYER_ATLAS);

        playerAnimations = new BufferedImage[12][8];
        for (int i = 0; i < playerAnimations.length; i++) {
            for (int j = 0; j < playerAnimations[i].length; j++) {
                playerAnimations[i][j] = playerImage.getSubimage(j * 48, i * 48, 48, 48);
            }
        }
    }

    public void loadLevelData(int[] levelData) {
        this.levelData = levelData;
    }

    private void updateAnimation() {
        aniTick++;
        if (aniTick > aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(playerAction)) {
                attack = false;
                aniIndex = 0;
                isHurt = false;
            }
        }
    }

    private void setAnimation() {
        int startAnimation = playerAction;

        aniSpeed = 10;

        if (moving) {
            playerAction = RUNNING;
            aniSpeed = 7;
        }

        if (climbing) {
            playerAction = CLIMBING;
            aniSpeed = 10;
        }

        if (!moving && !climbing) {
            playerAction = IDLE;
        }

        if (attack && attackNumber == 1) {
            playerAction = ATTACK_1;
            aniSpeed = 3;
        }

        if (attack && attackNumber == 2) {
            playerAction = ATTACK_2;
            aniSpeed = 3;
        }

        if (isJumping) {
            playerAction = JUMPING;
        }

        if (startAnimation != playerAction) {
            resetAnimationTick();
        }
    }

    private void resetAnimationTick() {
        aniTick = 0;
        aniIndex = 0;
    }

    private void updatePosition() {
        moving = false;
        climbing = false;

        float futureX = hitBox.x;
        float futureY = hitBox.y;


        if (left && !right && !down && !up && !isHurt) {
            futureX = hitBox.x - playerSpeed;
            if (CanMoveHere((int) futureX, (int) hitBox.y, hitBox.width, hitBox.height, levelData)) {
                x = futureX;
                hitBox.x = (int) x;
                moving = true;
                isPlayerMovingRight = false;
            }
        } else if (right && !left && !down && !up && !isHurt) {
            futureX = hitBox.x + playerSpeed;
            if (CanMoveHere((int) futureX, (int) hitBox.y, hitBox.width, hitBox.height, levelData)) {
                x = futureX;
                hitBox.x = (int) x;
                moving = true;
                isPlayerMovingRight = true;
            }
            moving = true;
        }

        if (up && !down && !right && !left) {
            futureY = hitBox.y - playerSpeed;
            if (CanMoveHere((int) hitBox.x, (int) futureY, hitBox.width, hitBox.height, levelData)) {
                y = futureY - Y_OFFSET;
                hitBox.y = (int) y + Y_OFFSET;
                climbing = true;
            }
        } else if (down && !up && !right && !left) {
            futureY = hitBox.y + playerSpeed;
            if (CanMoveHere((int) hitBox.x, (int) futureY, hitBox.width, hitBox.height, levelData)) {
                y = futureY - Y_OFFSET;
                hitBox.y = (int) y + Y_OFFSET;
                climbing = true;
            }
        }


        // Apply gravity
        yVelocity += GRAVITY;
        futureY += yVelocity;

        // Jumping logic
        if (jump && onGround) {
            yVelocity = -JUMP_STRENGTH;
            futureY += yVelocity;
            onGround = false;
            isJumping = true;
        }

        // Check vertical collision
        if (CanMoveHere((int) hitBox.x, (int) futureY, hitBox.width, hitBox.height, levelData)) {
            y = futureY - Y_OFFSET;
            hitBox.y = (int) y + Y_OFFSET;
        } else {
            // If collides, stop vertical movement
            if (yVelocity > 0) { // Falling
                onGround = true;
                yVelocity = 0;
                isJumping = false;
            }

            y = hitBox.y - Y_OFFSET;
        }




        // Ensure player doesn't go out of bounds
        if (hitBox.x < 0) {
            hitBox.x = 0;
            x = 0;
        } else if (hitBox.x > Game.GAME_WIDTH - hitBox.width) {
            hitBox.x = Game.GAME_WIDTH - hitBox.width;
            x = hitBox.x;
        }
        if (hitBox.y < 0) {
            hitBox.y = 0;
            y = -Y_OFFSET;
        } else if (hitBox.y > GAME_HEIGHT - hitBox.height) {
            hitBox.y = GAME_HEIGHT - hitBox.height;
            y = hitBox.y - Y_OFFSET;
        }
    }

    public void setAttack(boolean attack, byte attackNumber) {
        this.attack = attack;
        this.attackNumber = attackNumber;
        aniIndex = 0;
        aniTick = 0;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void stopActions() {
        moving = false;
        climbing = false;
        left = false;
        right = false;
        up = false;
        down = false;
    }

    public void setJump(boolean jump) {
        this.jump = jump;
    }

}
