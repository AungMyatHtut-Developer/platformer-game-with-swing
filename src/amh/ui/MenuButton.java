package amh.ui;

import amh.gameStates.GameState;
import amh.util.SpriteLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

import static amh.util.Constant.UI.Buttons.*;

public class MenuButton {

    private int xPos, yPos, rowIndex, index;
    private int xOffset = BUTTON_WIDTH/2;
    private GameState gameState;
    private BufferedImage[] menuButtons;
    private boolean mouseOver, mousePressed;
    private Rectangle bounds;

    public MenuButton(int xPos, int yPos, int rowIndex, GameState gameState) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.gameState = gameState;
        this.rowIndex = rowIndex;
        loadMenuButtonImage();
        initBounds();
    }

    private void initBounds() {
        bounds = new Rectangle(xPos - xOffset, yPos, BUTTON_WIDTH, BUTTON_HEIGHT);
    }

    public void draw(Graphics g) {
        g.drawImage(menuButtons[index], xPos - xOffset, yPos, BUTTON_WIDTH  , BUTTON_HEIGHT , null);
        g.drawRect(xPos - xOffset, yPos, BUTTON_WIDTH, BUTTON_HEIGHT);
    }

    public void update(){
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

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void applyGameState() {
        GameState.state = gameState;
    }

    public void resetButtonBools() {
        mouseOver = false;
        mousePressed = false;
    }

    private void loadMenuButtonImage() {
        menuButtons = new BufferedImage[3];
        BufferedImage image = SpriteLoader.getSprite(SpriteLoader.MENU_BUTTON);

        for (int i = 0; i < menuButtons.length; i++) {
            menuButtons[i] = image.getSubimage(
                    i * BUTTON_WIDTH_DEFAULT,
                    rowIndex * BUTTON_HEIGHT_DEFAULT,
                    BUTTON_WIDTH_DEFAULT, BUTTON_HEIGHT_DEFAULT);
        }
    }
}
