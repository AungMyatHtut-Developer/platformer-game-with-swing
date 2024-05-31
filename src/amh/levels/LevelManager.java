package amh.levels;

import amh.platformer.Game;
import amh.util.SpriteLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LevelManager {
    private BufferedImage[] levelSprite;

    private int[] currentLevel;

    public LevelManager( byte levelNumber) {
        importLevelSprites();
        currentLevel = Level.getLevelData(levelNumber);
    }

    private void importLevelSprites() {
        BufferedImage tempImage = SpriteLoader.getSprite(SpriteLoader.TILESET);
        levelSprite = new BufferedImage[96];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 12; j++) {
                int index = (i * 12) + j;
                    levelSprite[index] = tempImage.getSubimage(j * 32, i * 32, 32, 32);
            }
        }
    }

    public void draw(Graphics g) {
        // Iterate over the array to draw the tiles
        for (int j = 0; j < Game.TOTAL_TILES_IN_HEIGHT; j++) {
            for (int i = 0; i < Game.TOTAL_TILES_IN_WIDTH; i++) {
                int index = currentLevel[j * Game.TOTAL_TILES_IN_WIDTH + i]; // Calculate the index from the array
                if (index != -1) { // Only draw tiles that have a valid index
                    g.drawImage(levelSprite[index], Game.TILE_SIZE * i, Game.TILE_SIZE * j, Game.TILE_SIZE, Game.TILE_SIZE, null);
                }
            }
        }
    }

    public void update(byte levelNumber) {
        currentLevel = Level.getLevelData(levelNumber);
    }

    public int[] getCurrentLevelData() {
        return this.currentLevel;
    }
}
