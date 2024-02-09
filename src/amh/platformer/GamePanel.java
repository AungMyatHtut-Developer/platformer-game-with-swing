package amh.platformer;

import amh.platformer.inputs.KeyboardInputs;
import amh.platformer.inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel{

    private MouseInputs mouseInputs;
    private Game game;

    public GamePanel(Game game) {
        this.game = game;

        mouseInputs = new MouseInputs(this);
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
        
        setPanelSize();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        game.render(g);
    }

    public void updateGame(){

    }

    public Game getGame(){
        return this.game;
    }

    //set the Panel Size
    private void setPanelSize() {
        // 1280/32 = 40 images wide
        // 800/32 = 25 images height

        Dimension size = new Dimension(1280,800);

        setMinimumSize(size);
        setMaximumSize(size);
        setPreferredSize(size);
    }


}
