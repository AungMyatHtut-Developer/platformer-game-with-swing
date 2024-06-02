package amh.gameStates;

import amh.character.Player;
import amh.levels.LevelManager;
import amh.platformer.Game;
import amh.ui.PausedOverlay;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Playing extends State implements StateMethods{

    private Player player;
    private LevelManager levelManager;

    private PausedOverlay pausedOverlay;
    private boolean paused;

    private byte currentLevel = 1;

    public Playing(Game game) {
        super(game);
        initCharacters(game);
        pausedOverlay = new PausedOverlay(game);
    }

    private void initCharacters(Game game) {
        levelManager = new LevelManager( currentLevel);
        player = new Player(76, 390);
        player.loadLevelData(levelManager.getCurrentLevelData());
    }

    @Override
    public void render(Graphics g) {
        switch (GameState.state) {
            case PLAYING:
                if(!paused){
                    levelManager.draw(g);
                    player.render(g);
                }else{
                    levelManager.draw(g);
                    player.render(g);
                    pausedOverlay.render(g);
                }
                break;
            case MENU:
                break;
        }
    }

    @Override
    public void update() {
        switch (GameState.state) {
            case PLAYING:
                if(!paused){
                    player.update();
                    levelManager.update(currentLevel);
                }else{
                    levelManager.update(currentLevel);
                    pausedOverlay.update();
                }
                break;
            case MENU:
                break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

            if(e.getButton() == MouseEvent.BUTTON1)
                player.setAttack(true, (byte) 1);
            else if (e.getButton() == MouseEvent.BUTTON3)
                player.setAttack(true, (byte) 2);

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
        switch (e.getKeyCode()) {
            //Right
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                player.setRight(true);
                break;
            //Left
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                player.setLeft(true);
                break;
            //UP
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                player.setUp(true);
                break;
            //Down
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                player.setDown(true);
                break;
            case KeyEvent.VK_SPACE:
                player.setJump(true);
                break;
            case KeyEvent.VK_B:
                GameState.state  = GameState.MENU;
                stopPlayerMovement();
                break;
            case KeyEvent.VK_P:
                paused = !paused;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            //Right
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                player.setRight(false);
                break;
            //Left
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                player.setLeft(false);
                break;
            //UP
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                player.setUp(false);
                break;
            //Down
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                player.setDown(false);
                break;
            case KeyEvent.VK_SPACE:
                player.setJump(false);
                break;
        }
    }

    public Player getPlayer() {
        return this.player;
    }

    public void stopPlayerMovement() {
        player.setLeft(false);
        player.setRight(false);
        player.setJump(false);
    }

    public boolean isGamePaused() {
        return paused;
    }
}
