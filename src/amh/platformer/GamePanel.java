package amh.platformer;

import amh.handler.KeyboardHandler;
import amh.handler.MouseHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static amh.util.Constant.Directions.*;
import static amh.util.Constant.PlayerConstant.*;

public class GamePanel extends JPanel {

    private MouseHandler mouseHandler;
    private float xDelta = 10, yDelta = 10;

    private final short WIDTH = 1440;
    private final short HEIGHT = 720;

    private BufferedImage image;
    private BufferedImage [] [] animations;

    // player animation
    private int aniSpeed = 10, aniTick, aniIndex;
    private int playerAction = CLIMBING;
    private byte playerDir = -1;
    private boolean moving = false;
    private boolean climbing = false;

    public GamePanel() {
        mouseHandler = new MouseHandler(this);

        importImage();
        loadAnimation();

        addKeyListener(new KeyboardHandler(this));
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);

        setGamePanelSize();
    }

    private void setGamePanelSize() {
        Dimension gameDimension = new Dimension(WIDTH, HEIGHT);
        setMaximumSize(gameDimension);
        setPreferredSize(gameDimension);
        setMaximumSize(gameDimension);
    }

    private void importImage() {
        try (InputStream inputStream = getClass().getResourceAsStream("/img/myhero.png")){
            if (inputStream == null) {
                throw new IOException("Image not found!");
            }

            image = ImageIO.read(inputStream);
            System.out.println("We found the image");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadAnimation() {
        animations = new BufferedImage[12][8];
        for (int i = 0; i < animations.length; i++) {
            for(int j = 0; j < animations[i].length ; j++){
                animations[i][j] = image.getSubimage(j * 48, i * 48,48,48);
            }
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
        if (moving) {
            switch (playerDir) {
                case LEFT : xDelta -= 2;
                break;
                case UP: yDelta -= 2;
                break;
                case RIGHT: xDelta += 2;
                break;
                case DOWN: yDelta += 2;
            }
        }

        if (climbing) {
            switch (playerDir) {
                case LEFT : xDelta -= 2;
                    break;
                case UP: yDelta -= 2;
                    break;
                case RIGHT: xDelta += 2;
                    break;
                case DOWN: yDelta += 2;
            }
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        updateAnimation();
        setAnimation();
        updatePos();

        g.drawImage(animations[playerAction][aniIndex], (int) xDelta, (int) yDelta,92,92, null);
    }

    public void setDirection(byte direction) {
        playerDir = direction;
        moving = true;
    }

    public void setClimbingDirection(byte direction) {
        playerDir = direction;
        climbing = true;
    }

    public void setClimbing(boolean status) {
        climbing = status;
    }

    public void setMoving(boolean status) {
        moving = status;
    }

}
