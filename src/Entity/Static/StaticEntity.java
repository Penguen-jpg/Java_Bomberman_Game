package Entity.Static;

import Entity.Entity;
import Texture.Tile;
import Utility.Handler;

public abstract class StaticEntity extends Entity {
    //以格子為單位的座標
    protected int gridX;
    protected int gridY;

    public StaticEntity(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        destroyed = false;

        //設定gridX,gridY
        gridX = (int) ((position.x + Tile.TILE_WIDTH / 2) / Tile.TILE_WIDTH);
        gridY = (int) ((position.y + Tile.TILE_HEIGHT / 2) / Tile.TILE_HEIGHT);

        //重新定位位置
        position.x = gridX * Tile.TILE_WIDTH;
        position.y = gridY * Tile.TILE_HEIGHT;
    }

    public int getGridX() {
        return gridX;
    }

    public int getGridY() {
        return gridY;
    }
}
