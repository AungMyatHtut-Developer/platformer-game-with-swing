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
  - Make Drawn Rectangle movable by using handlers
  - Drawing Basics Images and Shapes
  - Adding GameListeners (Mouse Inputs, Mouse Motion Inputs, KeyBoard Inputs)
  - FPS introduce and How many frames in paintComponent?
  - Create Stable Game Loop with FPS 120 and monitor FPS
  - Add random rectangles
  - Make game window size to fit with JPanel and Import Image and Draw imported Image
  - Add our Image into Animation array to show animation. And Make start idle animation for our hero
  - Add all player animations
  - Show player animation based on actions and movements
  - Make to improve our game loop and Added pausing the game logic
  - Add Flip player animation
  - Add Game Update Method separately and Create player class to become more assessable
  - Update Player Movement by using boolean
    Deals with loosing focus window error (While pressing d key, switch to other window by using alt+tab, player will go infinitive)
    Fix attacking delays
  - Make CommonLoadSave Class for image resources. Create Game levels and Level Manager
    Load Level Image Data and render the level data (used tiled to build levels)
  - Add HitBox for player and add collision and detection function
  - Make HitBox smaller and implement on actual collision and detection
  - Add Jump Logic and gravity
  - Add Game State Parent Class and related Classes (This added features for Game Menu, Pause and Reset)
 */