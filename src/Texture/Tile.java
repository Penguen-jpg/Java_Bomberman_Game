package Texture;


import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {
    public static final int TILE_WIDTH = 64;
    public static final int TILE_HEIGHT = 64;
    protected BufferedImage texture;
    protected final int id;
    public static Tile[] tiles = new Tile[256];
    public static Tile grassTile = new GrassTile(0);
    public static Tile dirtTile = new DirtTile(1);
    public static Tile stoneTile = new StoneTile(2);

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
