package amh.platformer;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    public GamePanel() {
        setPreferredSize(new Dimension(100,100));
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect(10,10,200,200);
    }
}
