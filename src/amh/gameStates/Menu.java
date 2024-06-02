package amh.gameStates;

import amh.platformer.Game;
import amh.ui.MenuButton;
import amh.util.SpriteLoader;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static amh.platformer.Game.*;

public class Menu extends State implements StateMethods{

    private MenuButton[] menuButtons = new MenuButton[3];
    private BufferedImage menuBackground;
    private BufferedImage menuPrettyBackground;

    public Menu(Game game) {
        super(game);
        loadMenuBackground();
        loadButtons();
    }

    private void loadMenuBackground() {
        menuBackground = SpriteLoader.getSprite(SpriteLoader.MENU_BACKGROUND);
        menuPrettyBackground = SpriteLoader.getSprite(SpriteLoader.MENU_PRETTY_BACKGROUND);
    }

    private void loadButtons() {
        menuButtons[0] = new MenuButton(GAME_WIDTH / 2, (int) (110 * SCALE),0, GameState.PLAYING);
        menuButtons[1] = new MenuButton(GAME_WIDTH / 2, (int) (210 * SCALE),1, GameState.OPTION);
        menuButtons[2] = new MenuButton(GAME_WIDTH / 2, (int) (310 * SCALE),2, GameState.QUIT);
    }

    public void drawBackground(Graphics g) {
        g.drawImage(menuPrettyBackground, 0, 0, GAME_WIDTH, GAME_HEIGHT, null);
    }

    @Override
    public void render(Graphics g) {
        drawBackground(g);
        g.drawImage(menuBackground, GAME_WIDTH / 2  - ( (182 * 2) /2 ), 50, (int) (128 * 3f), 192 * 3, null);
        for(MenuButton menuButton : menuButtons)
            menuButton.draw(g);
    }

    @Override
    public void update() {
        for (MenuButton menuButton : menuButtons) {
            menuButton.update();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (MenuButton menuButton : menuButtons) {
            if (isIn(e, menuButton)) {
                menuButton.setMousePressed(true);
                break;
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    private void resetButtons() {
        for (MenuButton menuButton : menuButtons) {
            menuButton.resetButtonBools();
        }
    }


    @Override
    public void mouseReleased(MouseEvent e) {
        for (MenuButton menuButton : menuButtons) {
            if (isIn(e, menuButton)) {
                if (menuButton.isMousePressed()) {
                    menuButton.applyGameState();
                    break;
                }
            }
            menuButton.setMousePressed(false);
        }
        resetButtons();
    }

    @Override
    public void mouseDrag(MouseEvent e) {

    }

    @Override
    public void mouseMove(MouseEvent e) {
        for (MenuButton menuButton : menuButtons) {
            menuButton.setMouseOver(false);
        }

        for (MenuButton menuButton : menuButtons) {
            if (isIn(e, menuButton)) {
                menuButton.setMouseOver(true);
                break;
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER)
            GameState.state = GameState.PLAYING;
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
