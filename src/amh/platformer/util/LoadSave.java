package amh.platformer.util;

import amh.platformer.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class LoadSave {

    public static String PLAYER_ATLAS = "player_sprites.png";
    public static String LEVEL_ATLAS = "outside_sprites.png";
    public static String LEVEL_ONE_DATA = "level_one_data.png";
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

    public static int[][] getLevelData(){
        int[][] lvlData = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];
        BufferedImage img = getAtlas(LEVEL_ONE_DATA);

        for(int j = 0; j < img.getHeight(); j++){
            for(int i = 0; i<img.getWidth(); i++){
                Color color = new Color(img.getRGB(i,j));
                int value = color.getRed();
                if (value >= 48) {
                    value = 0;
                }

                lvlData[j][i] = value;
            }
        }

        System.out.println(Arrays.toString(lvlData));

        return lvlData;

    }
}
