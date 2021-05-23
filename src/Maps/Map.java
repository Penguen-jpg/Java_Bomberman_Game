package Maps;

import Entity.Creature.Player;
import Entity.EntityManager;
import Entity.Static.BreakableBox;
import Entity.Static.UnbreakableBox;
import Item.Item;
import Item.ItemManager;
import Texture.Tile;
import Utility.Handler;
import Utility.Helper;
import Graphics.AssetManager;

import java.awt.*;

public class Map {
    private int width, height;
    private int spawnX, spawnY;
    private int tileTypes[][];
    private Tile[][] tiles;
    private Handler handler;
    //entity manager
    private EntityManager entityManager;
    //item manager
    private ItemManager itemManager;

    public Map(Handler handler, String path) {
        this.handler = handler;
        loadMap(path);
        entityManager = new EntityManager(handler,
                new Player(handler, 0.0f, 0.0f, handler.getKeyboardManager(0), AssetManager.player1Animation, 1),
                new Player(handler, 200.0f, 0.0f, handler.getKeyboardManager(1), AssetManager.player1Animation, 2));
        entityManager.getPlayer1().setPosition(spawnX, spawnY);
        entityManager.addEntity(new BreakableBox(handler, 64.0f, 170.0f));
        entityManager.addEntity(new BreakableBox(handler, 128.0f, 170.0f));
        entityManager.addEntity(new BreakableBox(handler, 170.0f, 170.0f));
        entityManager.addEntity(new BreakableBox(handler, 300.0f, 150.0f));
        entityManager.addEntity(new BreakableBox(handler, 512.0f, 192.0f));
        entityManager.addEntity(new BreakableBox(handler, 576.0f, 192.0f));
        entityManager.addEntity(new BreakableBox(handler, 640.0f, 192.0f));
        entityManager.addEntity(new UnbreakableBox(handler, 600.0f, 400.0f));
        entityManager.addEntity(new UnbreakableBox(handler, 450.0f, 400.0f));
        entityManager.addEntity(new UnbreakableBox(handler, 380.0f, 400.0f));

        itemManager = new ItemManager(handler);
    }

    private void loadMap(String path) {
        String file = Helper.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        width = Helper.parseInt(tokens[0]);
        height = Helper.parseInt(tokens[1]);
        spawnX = Helper.parseInt(tokens[2]);
        spawnY = Helper.parseInt(tokens[3]);

        tileTypes = new int[width][height];
        tiles = new Tile[width][height];

        for(int y=0;y<height;y++) {
            for(int x=0;x<width;x++) {
                tileTypes[x][y] = Helper.parseInt(tokens[(x + y * width) + 4]);
                tiles[x][y] = Tile.tiles[tileTypes[x][y]];
            }
        }
    }

    public void tick() {
        itemManager.tick();
        entityManager.tick();
    }

    public void render(Graphics graphics) {
        for(int y=0;y<height;y++) {
            for(int x=0;x<width;x++) {
                tiles[x][y].render(graphics, x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT);
            }
        }

        itemManager.render(graphics);
        entityManager.render(graphics);
    }

    //getters
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public ItemManager getItemManager() {
        return itemManager;
    }

    public boolean isSolidTile(int x, int y) {
        if(x < 0 || y < 0 || x >= width || y>= width) {
            return Tile.floorTile1.isSolid();
        }

        return tiles[x][y].isSolid();
    }

    public Tile getTile(int x, int y) {
        if(x < 0 || y < 0 || x >= width || y >= height) {
            return Tile.topLeftCorner;
        }
        return tiles[x][y];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
