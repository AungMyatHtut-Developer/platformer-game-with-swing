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
        switch (e.getKeyCode()) {
            //Right
            case KeyEvent.VK_D:
                gamePanel.getOurGame().getPlayer().setDirection(RIGHT);
                break;
                //Left
            case KeyEvent.VK_A:
                gamePanel.getOurGame().getPlayer().setDirection(LEFT);
                break;
                //UP
            case KeyEvent.VK_W:
                gamePanel.getOurGame().getPlayer().setDirection(UP);
                break;
                //Down
            case KeyEvent.VK_S:
                gamePanel.getOurGame().getPlayer().setDirection(DOWN);
                break;
                //Climb UP
            case KeyEvent.VK_E:
//                gamePanel.getOurGame().getPlayer().setCl(UP);
                break;
            case KeyEvent.VK_P:
                gamePanel.getOurGame().pauseTheGame();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            //Right
            case KeyEvent.VK_D:
            //Left
            case KeyEvent.VK_A:
            //UP
            case KeyEvent.VK_W:
            //Down
            case KeyEvent.VK_S:
                gamePanel.getOurGame().getPlayer().setMoving(false);
                break;
            case KeyEvent.VK_E:
//                gamePanel.setClimbing(false);
                break;
        }
    }
}
