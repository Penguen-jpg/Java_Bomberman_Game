package Texture;

import Graphics.AssetManager;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {
    public static final int TILE_WIDTH = 64;
    public static final int TILE_HEIGHT = 64;
    protected BufferedImage texture;
    protected final int id;
    public static Tile[] tiles = new Tile[256];
    //public static Tile grassTile = new GrassTile(0);
    //public static Tile dirtTile = new DirtTile(1);
    //public static Tile stoneTile = new StoneTile(2);
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
    public static Tile leftSideFloor= new CornerTile(AssetManager.leftSideFloor, 10);
    public static Tile rightSideFloor = new CornerTile(AssetManager.rightSideFloor, 11);

    public Tile(BufferedImage texture, int id) {
        this.texture = texture;
        this.id = id;
        //將tiles[id]指定給呼叫method的Tile
        tiles[id] = this;
    }

    public void tick() {

    }

    public void render(Graphics graphics, int x, int y) {
        graphics.drawImage(texture, x, y, TILE_WIDTH, TILE_HEIGHT, null);
    }

    public boolean isSolid() {
        return false;
    }
}
