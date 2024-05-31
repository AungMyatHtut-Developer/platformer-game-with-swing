package amh.handler;

import amh.gameStates.GameState;
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
        switch (GameState.state) {
            case MENU: gamePanel.getOurGame().getMenu().mouseClicked(e);
            break;
            case PLAYING: gamePanel.getOurGame().getPlaying().mouseClicked(e);
            break;
            default:
                break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (GameState.state) {
            case MENU: gamePanel.getOurGame().getMenu().mousePressed(e);
                break;
            case PLAYING: gamePanel.getOurGame().getPlaying().mousePressed(e);
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch (GameState.state) {
            case MENU: gamePanel.getOurGame().getMenu().mouseReleased(e);
                break;
            case PLAYING: gamePanel.getOurGame().getPlaying().mouseReleased(e);
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        switch (GameState.state) {
            case MENU: gamePanel.getOurGame().getMenu().mouseMove(e);
                break;
            case PLAYING: gamePanel.getOurGame().getPlaying().mouseMove(e);
                break;
            default:
                break;
        }
    }
}
