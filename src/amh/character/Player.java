package amh.character;

import amh.levels.LevelManager;
import amh.platformer.Game;
import amh.util.HelperMethods;
import amh.util.SpriteLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

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
    private boolean left, up, right, down;
    private float playerSpeed = 2.0f;
    private byte attackNumber = 0;

    private int [] levelData;


    public Player(float x, float y) {
        super(x, y, PLAYER_IMG_WIDTH, PLAYER_IMG_HEIGHT);
        loadAnimations();
    }

    @Override
    public void update() {
        updatePosition();
        updateHitBox();
        setAnimation();
        updateAnimation();
    }

    @Override
    public void render(Graphics g) {
        if (isPlayerMovingRight) {
            // Draw original image
            g.drawImage(playerAnimations[playerAction][aniIndex], (int) x, (int) y, PLAYER_IMG_WIDTH, PLAYER_IMG_HEIGHT, null);
        }

        if (!isPlayerMovingRight) {

            Graphics2D g2D = (Graphics2D) g;

            // Save the current transform
            g2D.translate((int) x + PLAYER_IMG_WIDTH / 2, (int) y + PLAYER_IMG_HEIGHT / 2);
            g2D.scale(-1, 1); // Flip horizontally
            g2D.translate(-(int) x - PLAYER_IMG_WIDTH / 2, -(int) y - PLAYER_IMG_HEIGHT / 2);

            // Draw the flipped image
            g2D.drawImage(playerAnimations[playerAction][aniIndex], (int) x, (int) y, PLAYER_IMG_WIDTH, PLAYER_IMG_HEIGHT, null);

            // Reset transform
            g2D.setTransform(g2D.getDeviceConfiguration().getDefaultTransform());
        }

        drawHitBox(g);
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
//                hurtTime = 0;
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

        if (isHurt) {
            playerAction = HURT;
            aniSpeed = 25;
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

        int futureX = (int) x;
        int futureY = (int) y;

        if (left && !right && !down && !up && !isHurt) {
            futureX = (int) (x - playerSpeed);
            if (CanMoveHere(futureX, (int) y, levelData)) {
                x = futureX;
                moving = true;
                isPlayerMovingRight = false;
            }
        } else if (right && !left && !down && !up && !isHurt) {
            futureX = (int) (x + playerSpeed);
            if (CanMoveHere(futureX, (int) y, levelData)) {
                x = futureX;
                moving = true;
                isPlayerMovingRight = true;
            }
        }

        if (up && !down && !right && !left) {
            futureY = (int) (y - playerSpeed);
            if (CanMoveHere((int) x, futureY, levelData)) {
                y = futureY;
                climbing = true;
            }
        } else if (down && !up && !right && !left) {
            futureY = (int) (y + playerSpeed);
            if (CanMoveHere((int) x, futureY, levelData)) {
                y = futureY;
                climbing = true;
            }
        }



        // Ensure player doesn't go out of bounds
        if (x < 0) {
            x = 0;
        } else if (x > Game.GAME_WIDTH - PLAYER_IMG_WIDTH) {
            x = Game.GAME_WIDTH - PLAYER_IMG_WIDTH;
        }
        if (y < 0) {
            y = 0;
        } else if (y > Game.GAME_HEIGHT - PLAYER_IMG_HEIGHT) {
            y = Game.GAME_HEIGHT - PLAYER_IMG_HEIGHT;
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

    public void isHurt(boolean isHurt) {
        this.isHurt = isHurt;
    }
}
