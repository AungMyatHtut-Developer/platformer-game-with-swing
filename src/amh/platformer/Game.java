package amh.platformer;

import amh.platformer.entities.Player;
import amh.platformer.level.LevelManager;

import java.awt.*;

public class Game implements Runnable {

    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;

    private final int FPS_SET = 120;
    private final int UPS_SET = 120;

    private Player player;
    private LevelManager levelManager;

    public final static int DEFAULT_TILES_SIZE = 32;
    public final static float SCALE = 2f;
    public final static int TILES_IN_WIDTH = 26;
    public final static int TILES_IN_HEIGHT = 14;

    public final static int TILES_SIZE = (int) (DEFAULT_TILES_SIZE * SCALE);
    public final static int GAME_WIDTH = TILES_IN_WIDTH * TILES_SIZE;
    public final static int GAME_HEIGHT = TILES_IN_HEIGHT * TILES_SIZE;

    public Game() {
        initClasses();

        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();

        startTheGame();
    }

    public void initClasses() {
        levelManager = new LevelManager(this);
        player = new Player(200, 200,(int) (64 * SCALE), (int) (40 * SCALE));

        player.loadLvlData(levelManager.getCurrentLevel().getLvlData());
    }

    public void startTheGame() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        int frames = 0;
        int updates = 0;

        double timePerFrame = 1_000_000_000.0 / FPS_SET;
        double timePerUpdate = 1_000_000_000.0 / UPS_SET;

        long previousTimeUPS = System.nanoTime();
        long previousTimeFPS = System.nanoTime();

        long lastCheckForTimeTracking = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        while (true) {

            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTimeUPS) / timePerUpdate;
            deltaF += (currentTime - previousTimeFPS) / timePerFrame;

            previousTimeUPS = currentTime;
            previousTimeFPS = currentTime;

            if (deltaU >= 1) {
                update();
                updates++;
                deltaU--;
            }

            if (deltaF >= 1) {
                gamePanel.repaint();
                frames++;
                deltaF--;
            }

            // checking fps per second
            if (System.currentTimeMillis() - lastCheckForTimeTracking >= 1000) {
                lastCheckForTimeTracking = System.currentTimeMillis();
                System.out.println("Frames : " + frames + " | UPS : " + updates);
                frames = 0;
                updates = 0;
            }

        }

    }

    public void update() {
        levelManager.update();
        player.update();
    }

    public void render(Graphics graphics) {
        levelManager.draw(graphics);
        player.render(graphics);
    }

    public Player getPlayer() {
        return player;
    }

    public void windowFocusLost(){
        player.resetDirBooleans();
    }
}
