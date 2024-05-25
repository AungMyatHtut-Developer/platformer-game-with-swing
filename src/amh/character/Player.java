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
    private int aniSpeed = 10, aniTick, aniIndex;
    private int playerAction = CLIMBING;
    private byte playerDir = -1;
    private boolean moving = false;
    private boolean climbing = false;
    private boolean isFront = true;


    public Player(float x, float y) {
        super(x, y);
        loadAnimations();
    }

    @Override
    public void update() {
        setAnimation();
        updateAnimation();
        updatePos();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(playerAnimations[playerAction][aniIndex], (int) x, (int) y,92,92, null);
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
                aniIndex = 0;
            }
        }
    }

    private void setAnimation() {
        if (moving) {
            playerAction = RUNNING;
        }

        if (climbing) {
            playerAction = CLIMBING;
        }

        if(!moving && !climbing){
            playerAction = IDLE;
        }
    }

    private void updatePos() {
        if (moving && !climbing) {
            switch (playerDir) {
                case LEFT :
                    x -= 2;
                    isFront = false;
                    break;
                case UP: y -= 2;
                    break;
                case RIGHT: x += 2;
                    isFront = true;
                    break;
                case DOWN: y += 2;
            }
        }

        if (climbing && !moving) {
            switch (playerDir) {
                case UP: x -= 2;
                    break;
                case DOWN: y += 2;
            }
        }
    }

    public void setDirection(byte direction) {
        playerDir = direction;
        moving = true;
    }

    public void setMoving(boolean status) {
        moving = status;
    }
}
