package amh.character;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static amh.util.Constant.Directions.*;
import static amh.util.Constant.Directions.DOWN;
import static amh.util.Constant.PlayerConstant.*;
import static amh.util.Constant.PlayerConstant.IDLE;

public class Player extends Character{

    private float x;
    private float y;
    private BufferedImage[][] playerAnimations;

    // player animation
    private static final byte PLAYER_IMG_WIDTH = 92;
    private static final byte PLAYER_IMG_HEIGHT = 92;
    private int aniSpeed = 10, aniTick, aniIndex;
    private int playerAction = CLIMBING;
    private boolean isPlayerMovingRight = true;
    private boolean moving = false, attack = false;
    private boolean climbing = false;
    private boolean left,up,right,down;
    private float playerSpeed = 2.0f;
    private byte attackNumber = 0;



    public Player(float x, float y) {
        super(x, y);
        loadAnimations();
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
            g.drawImage(playerAnimations[playerAction][aniIndex], (int) x, (int) y,PLAYER_IMG_WIDTH,PLAYER_IMG_HEIGHT, null);
        }

        if (!isPlayerMovingRight) {

            Graphics2D g2D = (Graphics2D) g;

            // Save the current transform
            g2D.translate((int) x + PLAYER_IMG_WIDTH / 2, (int) y + PLAYER_IMG_HEIGHT / 2);
            g2D.scale(-1, 1); // Flip horizontally
            g2D.translate(-(int) x - PLAYER_IMG_WIDTH / 2, -(int) y - PLAYER_IMG_HEIGHT / 2);

            // Draw the flipped image
            g2D.drawImage(playerAnimations[playerAction][aniIndex], (int) x, (int) y, PLAYER_IMG_WIDTH, PLAYER_IMG_HEIGHT, null);
        }
    }

    @Override
    public void loadAnimations() {
        try (InputStream inputStream = getClass().getResourceAsStream("/img/myhero.png")){
            if (inputStream == null) {
                throw new IOException("Image not found!");
            }

            BufferedImage playerImage = ImageIO.read(inputStream);
            System.out.println("We found the image");

            playerAnimations = new BufferedImage[12][8];
            for (int i = 0; i < playerAnimations.length; i++) {
                for(int j = 0; j < playerAnimations[i].length ; j++){
                    playerAnimations[i][j] = playerImage.getSubimage(j * 48, i * 48,48,48);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateAnimation() {
        aniTick++;
        if (aniTick > aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(playerAction)) {
                attack = false;
                aniIndex = 0;
            }
        }
    }

    private void setAnimation() {
        int startAnimation = playerAction;

        aniSpeed = 10;

        if (moving) {
            playerAction = RUNNING;
            aniSpeed = 5;
        }

        if (climbing) {
            playerAction = CLIMBING;
            aniSpeed = 10;
        }

        if(!moving && !climbing){
            playerAction = IDLE;
        }

        if (attack && attackNumber == 1) {
            playerAction = ATTACK_1;
            aniSpeed = 2;
        }

        if (attack && attackNumber == 2) {
            playerAction = ATTACK_2;
            aniSpeed = 2;
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

        if( (left && up)  || (left && down)){
            x-=playerSpeed;
            climbing = true;
        } else if ((right && up) || (right && down)) {
            x+=playerSpeed;
            climbing = true;
        }

        if (left && !right && !down && !up) {
            x-= (playerSpeed * 2);
            moving = true;
            isPlayerMovingRight = false;
        } else if (right && !left && !down && !up) {
            x+= (playerSpeed * 2);
            moving = true;
            isPlayerMovingRight = true;
        }

        if (up && !down && !right && !left) {
            y-=playerSpeed;
            climbing = true;
        } else if (down && !up && !right && !left) {
            y+=playerSpeed;
            climbing = true;
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
}
