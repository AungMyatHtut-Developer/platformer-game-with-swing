package amh.handler;

import amh.platformer.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
            case KeyEvent.VK_D: gamePanel.changeXDelta(5);
                break;
                //Left
            case KeyEvent.VK_A: gamePanel.changeXDelta(-5);
                break;
                //UP
            case KeyEvent.VK_W: gamePanel.changeYDelta(-5);
                break;
                //Down
            case KeyEvent.VK_S: gamePanel.changeYDelta(5);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.printf("User released %s key \n", e.getKeyChar());
    }
}
