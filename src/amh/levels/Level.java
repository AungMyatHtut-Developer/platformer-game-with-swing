package amh.levels;

public class Level {

    private int [][] levelData;

    public Level(int [][] levelData) {
        this.levelData = levelData;
    }

    public int getSpriteIndex(int x, int y) {
        return levelData[y][x];
    }
}
