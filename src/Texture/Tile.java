package Texture;

import Graphics.AssetManager;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {
    //tile的寬 高
    public static final int TILE_WIDTH = 64;
    public static final int TILE_HEIGHT = 64;
    protected BufferedImage texture;
    //tile的種類
    protected final int type;
    //儲存所有種類的tile
    public static Tile[] tiles = new Tile[256];
    //目前有的tile種類
    public static Tile floorTile1 = new FloorTile(AssetManager.floor1, 0);
    public static Tile floorTile2 = new FloorTile(AssetManager.floor2, 1);
    public static Tile wallTile = new WallTile(AssetManager.wall, 2);

    public Tile(BufferedImage texture, int type) {
        this.texture = texture;
        this.type = type;
        //將目前的tile存進tiles[type]內
        tiles[type] = this;
    }

    public void tick() {

    }

    public void render(Graphics graphics, int x, int y) {
        graphics.drawImage(texture, x, y, TILE_WIDTH, TILE_HEIGHT, null);
    }

    //是否為solid
    public boolean isSolid() {
        return false;
    }
}
