package amh.gameStates;

import amh.platformer.Game;
import amh.ui.MenuButton;
import amh.util.SpriteLoader;
import javafx.scene.input.MouseButton;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static amh.platformer.Game.GAME_WIDTH;
import static amh.platformer.Game.SCALE;

public class Menu extends State implements StateMethods{

    private MenuButton[] menuButtons = new MenuButton[3];
    private BufferedImage menuBackground;

    public Menu(Game game) {
        super(game);
        loadMenuBackground();
        loadButtons();
    }

    private void loadMenuBackground() {
        menuBackground = SpriteLoader.getSprite(SpriteLoader.MENU_BACKGROUND);
    }

    private void loadButtons() {
        menuButtons[0] = new MenuButton(GAME_WIDTH / 2, (int) (90 * SCALE),0, GameState.PLAYING);
        menuButtons[1] = new MenuButton(GAME_WIDTH / 2, (int) (190 * SCALE),1, GameState.OPTION);
        menuButtons[2] = new MenuButton(GAME_WIDTH / 2, (int) (290 * SCALE),2, GameState.QUIT);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(menuBackground, GAME_WIDTH / 2  - ( (182 * 2) /2 ), 30, (int) (128 * 3f), 192 * 3, null);
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
        for (MenuButton menuButton : menuButtons) {
            if (isIn(e, menuButton)) {
                if (menuButton.isMousePressed()) {
                    menuButton.applyGameState();
                    break;
                }

            }
        }
        resetButtons();
    }

    private void resetButtons() {
        for (MenuButton menuButton : menuButtons) {
            menuButton.resetButtonBools();
        }
    }


    @Override
    public void mouseReleased(MouseEvent e) {

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
