package amh.handler;

import amh.platformer.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static amh.util.Constant.Directions.*;

public class KeyboardHandler implements KeyListener {

    private GamePanel gamePanel;

    public KeyboardHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.printf("User typed %s key \n",e.getKeyChar());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            //Right
            case KeyEvent.VK_D:
                gamePanel.setDirection(RIGHT);
                break;
                //Left
            case KeyEvent.VK_A:
                gamePanel.setDirection(LEFT);
                break;
                //UP
            case KeyEvent.VK_W:
                gamePanel.setDirection(UP);
                break;
                //Down
            case KeyEvent.VK_S:
                gamePanel.setDirection(DOWN);
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
                gamePanel.setMoving(false);
        }
    }
}
