package amh.platformer.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LoadSave {

    public static String PLAYER_ATLAS = "player_sprites.png";
    public static String MAP_ATLAS = "atlas.png";
    public static String ENEMY_ATLAS = "enemy.png";


    public static BufferedImage getAtlas(String fileName){

        InputStream inputStream = LoadSave.class.getResourceAsStream("/"+fileName);
        BufferedImage playerImage;

        try {
            playerImage = ImageIO.read(inputStream);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return playerImage;
    }
}
