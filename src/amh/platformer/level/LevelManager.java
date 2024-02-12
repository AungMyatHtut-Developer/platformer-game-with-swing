package amh.platformer.level;

import amh.platformer.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

import static amh.platformer.Game.TILES_SIZE;
import static amh.platformer.util.LoadSave.*;

public class LevelManager {

    private Game game;
    private BufferedImage[] levelImage;
    private Level levelOne;

    public LevelManager(Game game){
        this.game = game;
        importOutsideSprites();

        levelOne = new Level(getLevelData());
    }

    private void importOutsideSprites() {
        BufferedImage img = getAtlas(LEVEL_ATLAS);
        levelImage = new BufferedImage[48];

        for(int j=0 ; j<4; j++)
            for(int i=0; i<12 ; i++){
                int index = j * 12 +i;
                levelImage[index] = img.getSubimage(i*32,j*32,32,32);
            }

    }

    public void draw(Graphics g){

        for(int j=0; j < Game.TILES_IN_HEIGHT ; j++){
            for (int i = 0; i< Game.TILES_IN_WIDTH; i++){
                int index = levelOne.getSpriteIndex(i,j);
                g.drawImage(levelImage[index], TILES_SIZE*i,TILES_SIZE*j,TILES_SIZE, TILES_SIZE,null);
            }
        }

    }

    public void update(){

    }
}
