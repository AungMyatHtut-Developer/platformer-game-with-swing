package amh.platformer;

import amh.handler.KeyboardHandler;
import amh.handler.MouseHandler;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private MouseHandler mouseHandler;
    private Game ourGame;

    public GamePanel(Game ourGame) {
        this.ourGame = ourGame;

        mouseHandler = new MouseHandler(this);
        addKeyListener(new KeyboardHandler(this));
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);

        setGamePanelSize();
    }

    private void setGamePanelSize() {
        Dimension gameDimension = new Dimension(Game.GAME_WIDTH, Game.GAME_HEIGHT);
        System.out.println("Game Width : "+ Game.GAME_WIDTH + " Game Height : "+ Game.GAME_HEIGHT);
        setMaximumSize(gameDimension);
        setPreferredSize(gameDimension);
        setMaximumSize(gameDimension);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (this.ourGame.isGamePaused()) {
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.setColor(Color.RED);
            g.drawString("PAUSED", getWidth() /2 - 50, getHeight()/2);
        }

        ourGame.render(g);
    }

    public Game getOurGame() {
        return ourGame;
    }
}
