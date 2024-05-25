package amh.platformer;

import amh.character.Player;

import java.awt.*;

public class Game implements Runnable {

    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private Player player;

    private boolean isGameRunning = true;
    private boolean isPause = false;
    private final byte FPS = 60;
    private final short UPS = 120;

    public Game() {
        initCharacters();

        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus(true);

        startGameLoop();
    }

    private void initCharacters() {
        player = new Player(20, 20);
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    private void update() {
        player.update();
    }

    public void render(Graphics graphics) {
        player.render(graphics);
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

        int count = 0;

        // Main game loop
        while (isGameRunning) {
            if (!isPause) {

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
                    System.out.println("FPS: " + frameCount + " | UPS: " + updateCount);
                    frameCountStartTime = System.currentTimeMillis();
                    frameCount = 0;
                    updateCount = 0;
                }
            } else {
                lastUpdateTime = System.nanoTime();
            }
        }

    }

    public void pauseTheGame() {
        isPause = !isPause;
        if (isPause) {
            gamePanel.repaint();
        }
    }

    public boolean isGamePaused() {
        return isPause;
    }

    public Player getPlayer() {
        return this.player;
    }

    public void windowFocusLost() {
        player.stopActions();
    }
}
