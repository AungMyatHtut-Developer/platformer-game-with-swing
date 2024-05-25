package amh.levels;

import amh.platformer.Game;
import amh.util.SpriteLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LevelManager {
    private Game game;
    private BufferedImage[] levelSprite;
    private Level levelOne;


    public LevelManager(Game game) {
        this.game = game;
        importLevelSprites();
        levelOne = new Level(SpriteLoader.getLevelData());
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
        System.out.println(levelSprite.toString());
    }

    public void draw(Graphics g) {
        for (int j = 0; j < Game.TOTAL_TILES_IN_HEIGHT; j++) {
            for (int i = 0; i < Game.TOTAL_TILES_IN_WIDTH; i++) {
                int index = levelOne.getSpriteIndex(i, j);
                g.drawImage(levelSprite[index], Game.TILE_SIZE * i, Game.TILE_SIZE * j, Game.TILE_SIZE,Game.TILE_SIZE,null);
            }
        }

    }

    public void update() {

    }
}
