package amh.util;

import amh.character.Player;
import amh.platformer.Game;

public class HelperMethods {

    public static boolean CanMoveHere(int x, int y, int[] levelData) {
        int tileX1 = x / Game.TILE_SIZE;
        int tileY1 = y / Game.TILE_SIZE;
        int tileX2 = (x + Player.PLAYER_IMG_WIDTH - 1) / Game.TILE_SIZE;
        int tileY2 = (y + Player.PLAYER_IMG_HEIGHT - 1) / Game.TILE_SIZE;

        if (tileX1 < 0 || tileX2 >= Game.TOTAL_TILES_IN_WIDTH || tileY1 < 0 || tileY2 >= Game.TOTAL_TILES_IN_HEIGHT) {
            return false; // Out of bounds
        }

        int tileIndex1 = levelData[tileY1 * Game.TOTAL_TILES_IN_WIDTH + tileX1];
        int tileIndex2 = levelData[tileY1 * Game.TOTAL_TILES_IN_WIDTH + tileX2];
        int tileIndex3 = levelData[tileY2 * Game.TOTAL_TILES_IN_WIDTH + tileX1];
        int tileIndex4 = levelData[tileY2 * Game.TOTAL_TILES_IN_WIDTH + tileX2];

        return !isSolidTile(tileIndex1) && !isSolidTile(tileIndex2) && !isSolidTile(tileIndex3) && !isSolidTile(tileIndex4);
    }

    private static boolean isSolidTile(int tileIndex) {
        return tileIndex >= 0;
    }
}
