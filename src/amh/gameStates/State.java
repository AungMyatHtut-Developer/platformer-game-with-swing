package amh.gameStates;

import amh.platformer.Game;
import amh.ui.MenuButton;
import javafx.scene.input.MouseButton;

import java.awt.event.MouseEvent;

import static amh.util.Constant.UI.Buttons.BUTTON_WIDTH;

public class State{

    protected Game game;

    public State(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public boolean isIn(MouseEvent e, MenuButton menuButton) {
        System.out.println(menuButton.getBounds());
        return menuButton.getBounds().contains(e.getX(), e.getY());
    }
}
