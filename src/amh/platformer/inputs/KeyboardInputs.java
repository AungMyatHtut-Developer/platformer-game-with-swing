package amh.platformer.inputs;

import amh.platformer.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static amh.platformer.util.Constants.Directions.*;

public class KeyboardInputs implements KeyListener {

    private GamePanel gamePanel;

    public KeyboardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                gamePanel.getGame().getPlayer().setUp(true);//
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                gamePanel.getGame().getPlayer().setDown(true);//down
                break;
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                gamePanel.getGame().getPlayer().setLeft(true);//back
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                gamePanel.getGame().getPlayer().setRight(true);//front
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP: gamePanel.getGame().getPlayer().setUp(false);break;

            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT: gamePanel.getGame().getPlayer().setLeft(false);break;

            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:gamePanel.getGame().getPlayer().setDown(false);break;

            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:gamePanel.getGame().getPlayer().setRight(false);break;

        }

    }
}
