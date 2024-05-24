package amh.handler;

import amh.platformer.GamePanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseHandler implements MouseListener, MouseMotionListener {

    private GamePanel gamePanel;

    public MouseHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        gamePanel.shootBall();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("Mouse Pressed");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("Mouse Released!");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("Mouse Entered");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("Mouse Exist");
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        System.out.println("Mouse Dragged");
    }

    @Override
    public void mouseMoved(MouseEvent e) {
//       gamePanel.changeRecPosition(e.getX(), e.getY());
        gamePanel.updateLinePointing(e.getX(), e.getY());
    }
}
