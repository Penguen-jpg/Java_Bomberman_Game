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
    public static Tile floorTile3 = new FloorTile(AssetManager.floor3, 2);
    public static Tile floorTile4 = new FloorTile(AssetManager.floor4, 3);
    public static Tile topLeftCorner = new CornerTile(AssetManager.topLeftCorner, 4);
    public static Tile topFloor = new CornerTile(AssetManager.topFloor, 5);
    public static Tile topRightCorner = new CornerTile(AssetManager.topRightCorner, 6);
    public static Tile bottomLeftCorner = new CornerTile(AssetManager.bottomLeftCorner, 7);
    public static Tile bottomFloor = new CornerTile(AssetManager.bottomFloor, 8);
    public static Tile bottomRightFloor = new CornerTile(AssetManager.bottomRightCorner, 9);
    public static Tile leftSideFloor = new CornerTile(AssetManager.leftSideFloor, 10);
    public static Tile rightSideFloor = new CornerTile(AssetManager.rightSideFloor, 11);

    public Tile(BufferedImage texture, int type) {
        this.texture = texture;
        this.type = type;
        //將tiles[type]指定給呼叫method的tile
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
