package amh.handler;

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
        System.out.println("Kep Press Work");
        switch (e.getKeyCode()) {
            //Right
            case KeyEvent.VK_D:
                gamePanel.getOurGame().getPlayer().setRight(true);
                break;
            //Left
            case KeyEvent.VK_A:
                gamePanel.getOurGame().getPlayer().setLeft(true);
                break;
            //UP
            case KeyEvent.VK_W:
                gamePanel.getOurGame().getPlayer().setUp(true);
                break;
            //Down
            case KeyEvent.VK_S:
                gamePanel.getOurGame().getPlayer().setDown(true);
                break;
            case KeyEvent.VK_SPACE:
                gamePanel.getOurGame().getPlayer().isHurt(true);
                break;
            case KeyEvent.VK_C:
                gamePanel.getOurGame().changeLevel();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            //Right
            case KeyEvent.VK_D:
                gamePanel.getOurGame().getPlayer().setRight(false);
                break;
            //Left
            case KeyEvent.VK_A:
                gamePanel.getOurGame().getPlayer().setLeft(false);
                break;
            //UP
            case KeyEvent.VK_W:
                gamePanel.getOurGame().getPlayer().setUp(false);
                break;
            //Down
            case KeyEvent.VK_S:
                gamePanel.getOurGame().getPlayer().setDown(false);
                break;
        }
    }
}
