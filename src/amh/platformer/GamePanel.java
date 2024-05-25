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

//        if(isFront){
//            g.drawImage(animations[playerAction][aniIndex], (int) xDelta, (int) yDelta,92,92, null);
//        }

//        if (!isFront) {
//            Graphics2D g2d = (Graphics2D) g;
//
//            // Flip image horizontally
//            int imgWidth = 92;
//            int imgHeight = 92;
//            BufferedImage image = animations[playerAction][aniIndex];
//
//            // Save the current transform
//            g2d.translate((int) xDelta + imgWidth / 2, (int) yDelta + imgHeight / 2);
//            g2d.scale(-1, 1); // Flip horizontally
//            g2d.translate(-(int) xDelta - imgWidth / 2, -(int) yDelta - imgHeight / 2);
//
//            // Draw the flipped image
//            g2d.drawImage(image, (int) xDelta, (int) yDelta, imgWidth, imgHeight, null);
//
//            // Reset transform
//            g2d.setTransform(g2d.getDeviceConfiguration().getDefaultTransform());
//        }
    }

    public Game getOurGame() {
        return ourGame;
    }
}
