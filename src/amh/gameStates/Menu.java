package amh.gameStates;

import amh.platformer.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static amh.platformer.Game.GAME_WIDTH;

public class Menu extends State implements StateMethods{

    public Menu(Game game) {
        super(game);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawString("MENU", GAME_WIDTH/2, 50 );
    }

    @Override
    public void update() {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseDrag(MouseEvent e) {

    }

    @Override
    public void mouseMove(MouseEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER)
            GameState.state = GameState.PLAYING;
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
