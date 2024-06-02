package amh.ui;

import amh.util.SpriteLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

import static amh.util.Constant.UI.MusicButtons.*;

public class MusicButton {

    private int xPos, yPos;
    private int buttonType;
    private final String buttonName;
    private int index;
    private final BufferedImage[] musicOnButton = new BufferedImage[3];
    private final BufferedImage[] musicOffButton = new BufferedImage[3];
    private Rectangle bounds;

    private boolean mouseOver, mousePressed;

    public MusicButton(int xPos, int yPos, int buttonType, String buttonName){
        this.xPos = xPos;
        this.yPos = yPos;
        this.buttonType = buttonType;
        this.buttonName = buttonName;
        loadMusicButtonImage();
        initBounds();
    }

    public void draw(Graphics g, int buttonType) {
        if (buttonType == 0) {
            g.drawImage(musicOnButton[index], xPos, yPos, MUSIC_BUTTON_WIDTH, MUSIC_BUTTON_HEIGHT,null);
//            g.drawRect(xPos,yPos, MUSIC_BUTTON_WIDTH, MUSIC_BUTTON_HEIGHT);
        }else{
            g.drawImage(musicOffButton[index], xPos, yPos, MUSIC_BUTTON_WIDTH, MUSIC_BUTTON_HEIGHT, null);
//            g.drawRect(xPos,yPos, MUSIC_BUTTON_WIDTH, MUSIC_BUTTON_HEIGHT);
        }
    }

    public void update() {
        index = 0;

        if (mouseOver) {
            index = 1;
        }
        if (mousePressed) {
            index = 2;
        }
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public int getButtonType() {
        return this.buttonType;
    }

    public void toggleButtonType() {
        if (this.buttonType == 0) {
            this.buttonType = 1;
        }else{
            this.buttonType = 0;
        }
    }

    public Rectangle getBounds() {
        return this.bounds;
    }

    public void resetButtonBools() {
        mouseOver = false;
        mousePressed = false;
    }

    public String getButtonName() {
        return this.buttonName;
    }

    private void initBounds() {
        bounds = new Rectangle(xPos, yPos, MUSIC_BUTTON_WIDTH, MUSIC_BUTTON_HEIGHT);
    }

    private void loadMusicButtonImage() {
        BufferedImage image = SpriteLoader.getSprite(SpriteLoader.MUSIC_BUTTON);

        for (int i = 0; i < musicOnButton.length; i++) {
            musicOnButton[i] = image.getSubimage(
                    i * MUSIC_BUTTON_WIDTH_DEFAULT,
                     0,
                    MUSIC_BUTTON_WIDTH_DEFAULT,
                    MUSIC_BUTTON_HEIGHT_DEFAULT);
        }

        for (int i = 0; i < musicOffButton.length; i++) {
            musicOffButton[i] = image.getSubimage(
                    i * MUSIC_BUTTON_WIDTH_DEFAULT,
                    MUSIC_BUTTON_HEIGHT_DEFAULT,
                    MUSIC_BUTTON_WIDTH_DEFAULT,
                    MUSIC_BUTTON_HEIGHT_DEFAULT);
        }
    }


}
