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
    private Game ourGame;
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
    private boolean isFront = true;

    public GamePanel(Game ourGame) {
        mouseHandler = new MouseHandler(this);

        this.ourGame = ourGame;
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
        if (moving && !climbing) {
            switch (playerDir) {
                case LEFT :
                    xDelta -= 2;
                    isFront = false;
                break;
                case UP: yDelta -= 2;
                break;
                case RIGHT: xDelta += 2;
                isFront = true;
                break;
                case DOWN: yDelta += 2;
            }
        }

        if (climbing && !moving) {
            switch (playerDir) {
                case UP: yDelta -= 2;
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

        System.out.println("Is Game paused : "+this.ourGame.isGamePaused());
        if (this.ourGame.isGamePaused()) {
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.setColor(Color.RED);
            g.drawString("PAUSED", getWidth() /2 - 50, getHeight()/2);
        }

        if(isFront){
            g.drawImage(animations[playerAction][aniIndex], (int) xDelta, (int) yDelta,92,92, null);
        }

        if (!isFront) {
            Graphics2D g2d = (Graphics2D) g;

            // Flip image horizontally
            int imgWidth = 92;
            int imgHeight = 92;
            BufferedImage image = animations[playerAction][aniIndex];

            // Save the current transform
            g2d.translate((int) xDelta + imgWidth / 2, (int) yDelta + imgHeight / 2);
            g2d.scale(-1, 1); // Flip horizontally
            g2d.translate(-(int) xDelta - imgWidth / 2, -(int) yDelta - imgHeight / 2);

            // Draw the flipped image
            g2d.drawImage(image, (int) xDelta, (int) yDelta, imgWidth, imgHeight, null);

            // Reset transform
            g2d.setTransform(g2d.getDeviceConfiguration().getDefaultTransform());
        }


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

    public Game getOurGame() {
        return ourGame;
    }

}
