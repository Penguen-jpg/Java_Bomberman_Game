package Maps;

import Entity.Creature.Player;
import Entity.EntityManager;
import Entity.Static.BreakableBox;
import Entity.Static.UnbreakableBox;
import Graphics.AssetManager;
import Item.ItemManager;
import Texture.Tile;
import Utility.Handler;
import Utility.Helper;

import java.awt.*;

public class Map {
    private int width, height;
    private int spawnX1, spawnY1;
    private int spawnX2, spawnY2;
    private int tileTypes[][];
    private Tile[][] tiles;
    private String[] layout;
    private Handler handler;
    //entity manager
    private EntityManager entityManager;
    //item manager
    private ItemManager itemManager;

    public Map(Handler handler, String path) {
        this.handler = handler;
        loadMap(path);
        entityManager = new EntityManager(handler,
                new Player(handler, spawnX1, spawnY1
                        , handler.getKeyboardManager(0), AssetManager.player1Animation, 1),
                new Player(handler, spawnX2, spawnY2
                        , handler.getKeyboardManager(1), AssetManager.player1Animation, 2));

        createBasicLayout("src/res/maps/layout1.txt");
        placeBoxes();

        itemManager = new ItemManager(handler);
    }

    //研究中，可能不會用到
    public void init() {
        entityManager.setPlayer1(new Player(handler, spawnX1, spawnY1
                , handler.getKeyboardManager(0), AssetManager.player1Animation, 1));
        entityManager.setPlayer2(new Player(handler, spawnX2, spawnY2
                , handler.getKeyboardManager(1), AssetManager.player1Animation, 2));

        placeBoxes();
    }

    private void loadMap(String path) {
        String file = Helper.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        width = Helper.parseInt(tokens[0]);
        height = Helper.parseInt(tokens[1]);
        spawnX1 = Helper.parseInt(tokens[2]);
        spawnY1 = Helper.parseInt(tokens[3]);
        spawnX2 = Helper.parseInt(tokens[4]);
        spawnY2 = Helper.parseInt(tokens[5]);

        tileTypes = new int[width][height];
        tiles = new Tile[width][height];

        //設定tile
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tileTypes[x][y] = Helper.parseInt(tokens[(x + y * width) + 6]);
                tiles[x][y] = Tile.tiles[tileTypes[x][y]];
            }
        }
    }

    public void tick() {
        itemManager.tick();
        entityManager.tick();
    }

    public void render(Graphics graphics) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tiles[x][y].render(graphics, x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT);
            }
        }

        itemManager.render(graphics);
        entityManager.render(graphics);
    }

    //設定要新增的box的位置(從檔案讀取)
    private void createBasicLayout(String path) {
        //x:no box here, b:breakable box, u:unbreakable box
        String file = Helper.loadFileAsString(path);
        layout = file.split("\\s+");

    }

    //擺放box物件
    private void placeBoxes() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (layout[x + y * width].equals("b")) {
                    entityManager.addEntity(new BreakableBox(handler, x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT));
                } else if (layout[x + y * width].equals("u")) {
                    entityManager.addEntity(new UnbreakableBox(handler, x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT));
                }
            }
        }
    }

    //是否為solid tile
    public boolean isSolidTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= width) {
            return Tile.floorTile1.isSolid();
        }
        return tiles[x][y].isSolid();
    }

    //getters
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public ItemManager getItemManager() {
        return itemManager;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
