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
            case KeyEvent.VK_W -> gamePanel.setDirection(UP);//up
            case KeyEvent.VK_S -> gamePanel.setDirection(DOWN);//down

            case KeyEvent.VK_A -> gamePanel.setDirection(LEFT);//back
            case KeyEvent.VK_D -> gamePanel.setDirection(RIGHT);//front
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W,
                    KeyEvent.VK_A,
                    KeyEvent.VK_S,
                    KeyEvent.VK_D -> gamePanel.setMoving(false);
        }

    }
}
