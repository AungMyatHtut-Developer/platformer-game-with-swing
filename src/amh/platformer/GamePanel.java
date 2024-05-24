package amh.platformer;

import amh.handler.KeyboardHandler;
import amh.handler.MouseHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class GamePanel extends JPanel {

    private MouseHandler mouseHandler;
    private float xDelta = 10, yDelta = 10;

    private final short WIDTH = 1440;
    private final short HEIGHT = 720;

    private BufferedImage image;
    private BufferedImage [] idleAnimation;

    private int aniSpeed = 20, aniTick, aniIndex;

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
        idleAnimation = new BufferedImage[4];
        for (int i = 0; i < idleAnimation.length; i++) {
           idleAnimation[i] = image.getSubimage(i * 48, 48 * 3 ,48,48);
        }
    }

    private void updateAnimation() {
        aniTick++;
        if (aniTick > aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= idleAnimation.length) {
                aniIndex = 0;
            }
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        updateAnimation();
        g.drawImage(idleAnimation[aniIndex], (int) xDelta, (int) yDelta,92,92, null);
    }


    public void moveHero(int x, int y) {
        xDelta += x;
        yDelta += y;
    }

    public void moveHeroByMouse(int x, int y) {
        xDelta = x;
        yDelta = y;
    }

}
