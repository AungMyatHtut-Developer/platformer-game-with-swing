package amh.platformer;

import amh.platformer.inputs.KeyboardInputs;
import amh.platformer.inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static amh.platformer.util.Constants.Directions.*;
import static amh.platformer.util.Constants.PlayerConstants.*;

public class GamePanel extends JPanel{

    private MouseInputs mouseInputs;
    private float xDelta = 0, yDelta = 0;
    private BufferedImage img;
    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSubIndex, aniSpeed = 20;
    private int playerAction = IDLE;
    private int playerDirection = -1;
    private boolean moving = false;

    public GamePanel() {
        mouseInputs = new MouseInputs(this);
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
        
        setPanelSize();
        importImage();
        loadPirateAnimation();
    }

    /**
     * This method will draw graphics on JPanel
     * JPanel can't draw without the help of graphics
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        updateAnimation();
        setAnimation();
        updatePosition();

        g.drawImage( animations[playerAction][aniIndex],(int) xDelta, (int) yDelta, 128,80,null);

    }

    private void updatePosition() {
        if(moving){
            switch (playerDirection) {
                case LEFT: xDelta-=5;
                    break;
                case RIGHT: xDelta+=5;
                    break;
                case UP: yDelta-=5;
                    break;
                case DOWN:yDelta+=5;
                    break;
            }
        }
    }

    private void setAnimation() {
        if(moving)
            playerAction = RUNNING;
        else
            playerAction = IDLE;
    }

    //update animation
    private void updateAnimation() {
        aniTick++;
        if(aniTick >= aniSpeed){
            aniTick = 0;
            aniIndex++;
            if(aniIndex >= getSpriteAmounts(playerAction))
                aniIndex = 0;
        }
    }


    //load All Pirate Animations into Array
    private void loadPirateAnimation(){
        animations = new BufferedImage[9][6];

        for(int j = 0; j<animations.length; j++)
            for (int i = 0; i < animations[j].length; i++)
                animations[j][i] = img.getSubimage(i*64,j*40,64,40);
    }
    //import required sprites
    private void importImage() {
        InputStream inputStream = getClass().getResourceAsStream("/player_sprites.png");
        InputStream is = getClass().getResourceAsStream("/character.png");
        try {
            img = ImageIO.read(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                inputStream.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    //set the Panel Size
    private void setPanelSize() {
        // 1280/32 = 40 images wide
        // 800/32 = 25 images height

        Dimension size = new Dimension(1280,800);

        setMinimumSize(size);
        setMaximumSize(size);
        setPreferredSize(size);
    }
    //change x value for component
    public void changeXDelta(int value) {
        xDelta+= value;
    }
    //change y value of component
    public void changeYDelta(int value) {
        yDelta+= value;
    }
    //change x and y position of component
    public void setRecPos(int x, int y) {
        this.xDelta = x;
        this.yDelta = y;
    }

    //player direction
    public void setDirection(int direction) {
        this.playerDirection = direction;
        moving = true;
    }

    //player moving
    public void setMoving(boolean moving){
        this.moving = moving;
    }

}
