package amh.platformer;

public class Game implements Runnable {

    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;

    private boolean isGameRunning = true;
    private final byte FPS = 120;

    public Game() {
        gamePanel = new GamePanel();
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus(true);

        startGameLoop();
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double timePerFrame = 1_000_000_000.0 / FPS;
        long lastCheck = System.nanoTime();
        long now = 0;

        long lastCheckForFPS = 0;
        long frames = 0;

        // create game loop
        while (isGameRunning) {

            // render the frames
            now = System.nanoTime();
            if(now - lastCheck >= timePerFrame){
                gamePanel.repaint();
                lastCheck = now;
                frames++;
            }

            // monitor the FPS
            if (System.currentTimeMillis() - lastCheckForFPS >= 1_000) {
                System.out.println("FPS : "+ frames);
                lastCheckForFPS = System.currentTimeMillis();
                frames = 0;
            }

        }

    }
}
