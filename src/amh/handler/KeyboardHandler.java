package amh.handler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardHandler implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {
        System.out.printf("User typed %s key \n",e.getKeyChar());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.printf("User pressed %s key \n", e.getKeyChar());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.printf("User released %s key \n", e.getKeyChar());
    }
}
