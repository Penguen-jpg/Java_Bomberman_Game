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
    private char[][] layout;
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
        entityManager.getPlayer1().setPosition(spawnX1, spawnY1);
        entityManager.getPlayer2().setPosition(spawnX2, spawnY2);
        placeBoxes();

        /*entityManager.addEntity(new BreakableBox(handler, 64.0f, 170.0f));
        entityManager.addEntity(new BreakableBox(handler, 128.0f, 170.0f));
        entityManager.addEntity(new BreakableBox(handler, 170.0f, 170.0f));
        entityManager.addEntity(new BreakableBox(handler, 300.0f, 150.0f));
        entityManager.addEntity(new BreakableBox(handler, 512.0f, 192.0f));
        entityManager.addEntity(new BreakableBox(handler, 576.0f, 192.0f));
        entityManager.addEntity(new BreakableBox(handler, 640.0f, 192.0f));
        entityManager.addEntity(new UnbreakableBox(handler, 600.0f, 400.0f));
        entityManager.addEntity(new UnbreakableBox(handler, 450.0f, 400.0f));
        entityManager.addEntity(new UnbreakableBox(handler, 380.0f, 400.0f));*/

        itemManager = new ItemManager(handler);
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

    //設定要新增的box的位置
    private void createBasicLayout() {
        layout = new char[width][height];

        //x:no box here, b:breakable box, u:unbreakable box
        for (int y = 1; y < height - 1; y++) {
            for (int x = 1; x < width - 1; x++) {
                if ((y == 2 || y == 4 || y == 7) && x % 2 == 0) {
                    layout[x][y] = 'u';
                } else {
                    layout[x][y] = 'b';
                }
            }
        }

        //排出空格
        for (int x = 0; x < width; x++) {
            layout[x][0] = 'x';
            layout[x][height - 1] = 'x';
        }
        for (int y = 1; y < height; y++) {
            layout[0][y] = 'x';
            layout[width - 1][y] = 'x';
        }
        layout[1][1] = layout[2][1] = layout[5][1] = layout[10][1] = layout[13][1] = 'x';
        layout[1][2] = layout[13][2] = 'x';
        layout[1][3] = layout[4][3] = layout[6][3] = layout[8][3] = 'x';
        layout[5][4] = layout[7][4] = layout[11][4] = 'x';
        layout[5][6] = layout[6][6] = layout[11][6] = layout[13][6] = 'x';
        layout[3][7] = layout[9][7] = layout[13][7] = 'x';
        layout[12][8] = layout[13][8] = 'x';
    }

    //擺放box物件
    private void placeBoxes() {
        createBasicLayout();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (layout[x][y] == 'b') {
                    entityManager.addEntity(new BreakableBox(handler, x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT));
                } else if (layout[x][y] == 'u') {
                    entityManager.addEntity(new UnbreakableBox(handler, x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT));
                }
            }
        }
    }

    //getters
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public ItemManager getItemManager() {
        return itemManager;
    }

    public boolean isSolidTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= width) {
            return Tile.floorTile1.isSolid();
        }

        return tiles[x][y].isSolid();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
