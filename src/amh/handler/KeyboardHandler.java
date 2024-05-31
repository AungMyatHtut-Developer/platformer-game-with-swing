package amh.handler;

import amh.gameStates.GameState;
import amh.platformer.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

import static amh.util.Constant.Directions.*;

public class KeyboardHandler implements KeyListener {

    private GamePanel gamePanel;

    public KeyboardHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (GameState.state) {

            case PLAYING:
                gamePanel.getOurGame().getPlaying().keyPressed(e);
                break;
            case MENU:
                gamePanel.getOurGame().getMenu().keyPressed(e);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (GameState.state) {

            case PLAYING:
                gamePanel.getOurGame().getPlaying().keyReleased(e);
                break;
            case MENU:
                gamePanel.getOurGame().getMenu().keyReleased(e);
                break;
        }
    }
}
