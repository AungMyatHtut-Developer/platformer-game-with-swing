package amh.platformer;

public class Game implements Runnable{

    private GameWindow gameWindow;
    private GamePanel gamePanel;

    private Thread gameThread;
    private final static int FPS_SET = 120;
    public Game() {
        gamePanel = new GamePanel();
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();

        startTheGame();
    }

    public void startTheGame(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double timePerFrame = 1_000_000_000.0/FPS_SET;
        long lastFrame = System.nanoTime();
        long now;
        int frames = 0;
        long lastCheck = System.currentTimeMillis();

        while (true){
            now = System.nanoTime();
            if(now - lastFrame >= timePerFrame){
                gamePanel.repaint();
                lastFrame = now;
                frames++;
            }

            // checking fps per second
            if(System.currentTimeMillis() - lastCheck >= 1000){
                lastCheck = System.currentTimeMillis();
                System.out.println("Frames : "+ frames);
                frames = 0;
            }
        }



    }
}
