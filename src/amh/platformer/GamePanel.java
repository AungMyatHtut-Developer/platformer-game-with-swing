package amh.platformer;

import amh.handler.KeyboardHandler;
import amh.handler.MouseHandler;
import com.sun.xml.internal.bind.v2.model.core.ID;

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

    private int aniSpeed = 25, aniTick, aniIndex;
    private int playerAction = CLIMBING;
    private byte playerDir = -1;
    private boolean moving = false;

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
        int length = animations.length;
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
        }else{
            playerAction = IDLE;
        }

    }

    private void updatePos() {
        if (moving) {
            switch (playerDir) {
                case LEFT : xDelta -= 5;
                break;
                case UP: yDelta -= 5;
                break;
                case RIGHT: xDelta += 5;
                break;
                case DOWN: yDelta += 5;
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




    public void moveHero(int x, int y) {
        xDelta += x;
        yDelta += y;
    }

    public void moveHeroByMouse(int x, int y) {
        xDelta = x;
        yDelta = y;
    }

    public void setDirection(byte direction) {
        playerDir = direction;
        moving = true;
    }

    public void setMoving(boolean status) {
        moving = status;
    }

}
