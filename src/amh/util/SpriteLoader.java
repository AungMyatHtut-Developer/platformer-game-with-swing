package amh.util;

import amh.platformer.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class SpriteLoader {

    public static final String PLAYER_ATLAS = "myhero.png";
    public static final String TILESET = "Tileset.png";
    public static final String MENU_BUTTON = "menu-button.png";
    public static final String MENU_BACKGROUND = "menu.png";
    public static final String MENU_PRETTY_BACKGROUND = "menu-background.png";
    public static final String PAUSED_BACKGROUND = "paused-background.png";
    public static final String MUSIC_BUTTON = "volume-buttons.png";

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
