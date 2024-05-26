package amh.levels;

public class Level {

    public static int[] getLevelData(byte levelNumber) {
        switch (levelNumber) {
            case 2: return LevelConstants.LEVEL_TWO;
            default:
                return LevelConstants.LEVEL_ONE;
        }
    }
}
