package amh.platformer.inputs;

import amh.platformer.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
            case KeyEvent.VK_W -> gamePanel.changeYDelta(-5);//up
            case KeyEvent.VK_S -> gamePanel.changeYDelta(5);//down

            case KeyEvent.VK_A -> gamePanel.changeXDelta(-5);//back
            case KeyEvent.VK_D -> gamePanel.changeXDelta(5);//front
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {


    }
}
