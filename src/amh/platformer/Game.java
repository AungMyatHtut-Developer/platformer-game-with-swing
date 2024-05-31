package amh.platformer;

import amh.character.Player;
import amh.gameStates.GameState;
import amh.gameStates.Menu;
import amh.gameStates.Playing;
import amh.levels.LevelManager;

import java.awt.*;

public class Game implements Runnable {

    private GamePanel gamePanel;
    private Thread gameThread;

    private final byte FPS = 60;
    private final short UPS = 120;

    private Playing playing;
    private Menu menu;

    public static final int DEFAULT_TILE_SIZE = 32;
    public static final float SCALE = 1.5f;
    public static final int TOTAL_TILES_IN_WIDTH = 25;
    public static final int TOTAL_TILES_IN_HEIGHT = 15;
    public static final int TILE_SIZE = (int) (DEFAULT_TILE_SIZE * SCALE);
    public static final int GAME_WIDTH = TOTAL_TILES_IN_WIDTH * TILE_SIZE;
    public static final int GAME_HEIGHT = TOTAL_TILES_IN_HEIGHT * TILE_SIZE;

    public Game() {
        initClasses();

        gamePanel = new GamePanel(this);
        new GameWindow(gamePanel);
        gamePanel.requestFocus(true);

        startGameLoop();
    }

    private void initClasses() {
        playing = new Playing(this);
        menu = new Menu(this);
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    private void update() {
        switch (GameState.state) {
            case PLAYING:
                playing.update();
                break;
            case MENU:
                menu.update();
                break;
        }
    }

    public void render(Graphics g) {
        switch (GameState.state) {
            case PLAYING:
                playing.render(g);
                break;
            case MENU:
                menu.render(g);
                break;
        }
    }

    @Override
    public void run() {
        // Frames Per Second (FPS)
        double timePerFrame = 1_000_000_000.0 / FPS; // Time per frame in nanoseconds
        long frameCountStartTime = System.currentTimeMillis(); // Start time for counting frames
        long frameCount = 0; // Number of frames rendered

        // Updates Per Second (UPS)
        double timePerUpdate = 1_000_000_000.0 / UPS; // Time per update in nanoseconds
        long lastUpdateTime = System.nanoTime(); // Last recorded time for updates and frames

        long updateCount = 0; // Number of updates
        double updateDelta = 0; // Time delta for updates
        double frameDelta = 0; // Time delta for frames

        // Main game loop
        while (true) {
            if (true) {

                long currentTime = System.nanoTime(); // Current time in nanoseconds

                // Calculate time delta for updates and frames
                updateDelta += (currentTime - lastUpdateTime) / timePerUpdate;
                frameDelta += (currentTime - lastUpdateTime) / timePerFrame;
                lastUpdateTime = currentTime;

                // Perform game state updates
                if (updateDelta >= 1) {
                    update(); // Update game logic
                    updateCount++;
                    updateDelta--;
                }

                // Render the frames
                if (frameDelta >= 1) {
                    gamePanel.repaint(); // Render the game frame
                    frameCount++;
                    frameDelta--;
                }

                // Monitor the FPS and UPS every second
                if (System.currentTimeMillis() - frameCountStartTime >= 1_000) {
                    frameCountStartTime = System.currentTimeMillis();
                    frameCount = 0;
                    updateCount = 0;
                }
            } else {
                lastUpdateTime = System.nanoTime();
            }
        }

    }

    public void windowFocusLost() {
        if(GameState.state == GameState.PLAYING)
            getPlaying().getPlayer().stopActions();
    }

    public Playing getPlaying() {
        return this.playing;
    }

    public Menu getMenu() {
        return this.menu;
    }

}
