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

    private boolean isMusicOn = true;
    private boolean isSFXOn = true;

    private MusicButton[] musicButtons = new MusicButton[2];


    public PausedOverlay(Game game) {
        super(game);
        this.game = game;
        loadBackgroundImage();
        loadMusicButton();
    }

    private void loadMusicButton() {
        musicButtons[0]  = new MusicButton( GAME_WIDTH / 2 + 50,    250, 0, "musicButton");
        musicButtons[1]  = new MusicButton(GAME_WIDTH / 2 + 50,317, 0, "sfxButton");
    }

    private void loadBackgroundImage() {
        pausedBackgroundImage = SpriteLoader.getSprite(SpriteLoader.PAUSED_BACKGROUND);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(pausedBackgroundImage, GAME_WIDTH / 2  - ( (182 * 2) /2 ), 50, (int) (128 * 3f), 192 * 3, null);
        for (MusicButton musicButton : musicButtons) {
            musicButton.draw(g, musicButton.getButtonType());
        }
    }

    @Override
    public void update() {
        for (MusicButton musicButton : musicButtons) {
            musicButton.update();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (MusicButton musicButton : musicButtons) {
            if (isIn(e, musicButton)) {
                musicButton.setMousePressed(true);
                break;
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for (MusicButton musicButton : musicButtons) {
            if (isIn(e, musicButton)) {
                if (musicButton.isMousePressed()) {
                    System.out.println(musicButton.getButtonName());

                    if (musicButton.getButtonName().contentEquals("musicButton")) {
                        isMusicOn = !isMusicOn;
                        musicButton.toggleButtonType();
                    }

                    if (musicButton.getButtonName().contentEquals("sfxButton")) {
                        isSFXOn = !isSFXOn;
                        musicButton.toggleButtonType();
                    }

                    break;
                }
            }
            musicButton.setMousePressed(false);
        }
        resetButtons();
    }

    private void resetButtons() {
        for (MusicButton musicButton : musicButtons) {
            musicButton.resetButtonBools();
        }
    }

    @Override
    public void mouseDrag(MouseEvent e) {

    }

    @Override
    public void mouseMove(MouseEvent e) {
        for (MusicButton musicButton : musicButtons) {
            musicButton.setMouseOver(false);
        }

        for (MusicButton musicButton : musicButtons) {
            if (isIn(e, musicButton)) {
                musicButton.setMouseOver(true);
                break;
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
