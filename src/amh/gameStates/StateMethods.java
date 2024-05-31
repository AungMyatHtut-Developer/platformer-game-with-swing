package amh.gameStates;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface StateMethods {

    void render(Graphics g);
    void update();

    void mouseClicked(MouseEvent e);
    void mouseReleased(MouseEvent e);
    void mouseDrag(MouseEvent e);
    void mouseMove(MouseEvent e);

    void keyPressed(KeyEvent e);
    void keyReleased(KeyEvent e);

}
