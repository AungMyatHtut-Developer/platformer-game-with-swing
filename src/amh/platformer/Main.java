package amh.platformer;

public class Main {
    public static void main(String[] args) {
        new Game();
    }
}

/*
  - Build Needed Classes - Game,GamePanel,GameWindow
  - Show Game Window
  - Draw Rectangle on Game Panel
  - Add Action Listeners on Game Panel (KeyListener, MouseListener, MouseMotionListener)
  - Drawing Basics Images and Shapes
  - Adding GameListeners (Mouse Inputs, Mouse Motion Inputs, KeyBoard Inputs)
  - Creating game loop by using repaint()
  - Refix game loop to become stable
  - Importing Sprites by using image.getSubImage()
  - Adding Sprite Images into Arrays to make player animations
  - Creating game constants
  - Refix game loop by using stable nanoTime() and Separate FPS and UPS and use multithreading for game loops
  - Connecting sprites with keyboard actions
  - Refix lagging issue on keyboard actions
  - Refix Tricky foolproof issues
  - Separating Player Entity by extending from Entity Class and usage of abstract and protected
  - Adding common image import method
  - Making Dimension more precisely
  - Adding game levels and LevelManager
  - Adding levelSprite into Level Array
  - Collision and Detection (Adding Hit Box on our character)
 */