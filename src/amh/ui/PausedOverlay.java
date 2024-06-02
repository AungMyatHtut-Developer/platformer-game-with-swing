package amh.ui;

import amh.gameStates.State;
import amh.gameStates.StateMethods;
import amh.platformer.Game;
import amh.util.SpriteLoader;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static amh.platformer.Game.GAME_WIDTH;

public class PausedOverlay extends State implements StateMethods {

    private Game game;
    private BufferedImage pausedBackgroundImage;


    public PausedOverlay(Game game) {
        super(game);
        this.game = game;
        loadBackgroundImage();
    }

    private void loadBackgroundImage() {
        pausedBackgroundImage = SpriteLoader.getSprite(SpriteLoader.PAUSED_BACKGROUND);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(pausedBackgroundImage, GAME_WIDTH / 2  - ( (182 * 2) /2 ), 50, (int) (128 * 3f), 192 * 3, null);
    }

    @Override
    public void update() {

    }

    @Override
    public void mousePressed(MouseEvent e) {

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

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
