package amh.util;

import amh.character.Player;
import amh.platformer.Game;

public class HelperMethods {

    public static boolean CanMoveHere(int x, int y, int width, int height, int[] levelData) {
        int tileX1 = x / Game.TILE_SIZE;
        int tileY1 = y / Game.TILE_SIZE;
        int tileX2 = (x + width - 1) / Game.TILE_SIZE;
        int tileY2 = (y + height - 1) / Game.TILE_SIZE;

        if (tileX1 < 0 || tileX2 >= Game.TOTAL_TILES_IN_WIDTH || tileY1 < 0 || tileY2 >= Game.TOTAL_TILES_IN_HEIGHT) {
            return false; // Out of bounds
        }

        // Check tiles that intersect with the player's sprite
        for (int ty = tileY1; ty <= tileY2; ty++) {
            for (int tx = tileX1; tx <= tileX2; tx++) {
                int tileIndex = levelData[ty * Game.TOTAL_TILES_IN_WIDTH + tx];
                if (isSolidTile(tileIndex)) {
                    return false; // Collision detected
                }
            }
        }

        return true; // No collision detected
    }

    private static boolean isSolidTile(int tileIndex) {
        // Ignore specific tiles (84, 85, and 86)
        if (tileIndex == 84 || tileIndex == 85 || tileIndex == 86) {
            return false;
        }
        return tileIndex >= 0;
    }
}
