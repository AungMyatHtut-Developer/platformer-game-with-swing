package amh.util;

import amh.platformer.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class SpriteLoader {

    public static final String PLAYER_ATLAS = "myhero.png";
    public static final String TILESET = "Tileset.png";

    public static final String LEVEL_ONE_ATLAS = "level_one.png";

    public static BufferedImage getSprite(String atlasName) {
        BufferedImage image = null;
        try (InputStream inputStream = SpriteLoader.class.getResourceAsStream("/img/"+atlasName)){
            if (inputStream == null) {
                throw new IOException("Image not found!");
            }
            image = ImageIO.read(inputStream);
            System.out.println("We found the image");

        } catch (Exception e) {
            System.out.println("Exception happened when loading Sprite. Error : "+ e);
        }
        return image;
    }

    public static int[][] getLevelData() {
        int[][] levelData = new int[Game.TOTAL_TILES_IN_HEIGHT][Game.TOTAL_TILES_IN_WIDTH];
        BufferedImage levelImage = getSprite(LEVEL_ONE_ATLAS);

        for (int j = 0; j < levelImage.getHeight(); j++) {
            for (int i = 0; i < levelImage.getWidth(); i++) {
                Color color = new Color(levelImage.getRGB(i, j));
                int value = color.getRed();
                if(value >= 96)
                    value = 0;
                levelData[j][i] = value;
            }
        }

        return levelData;
    }
}
