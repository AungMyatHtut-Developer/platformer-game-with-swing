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
}