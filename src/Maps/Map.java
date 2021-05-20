package Maps;

import Entity.Creature.Player;
import Entity.EntityManager;
import Entity.Static.Tree;
import Texture.Tile;
import Utility.Handler;
import Utility.Helper;
import Graphics.AssetManager;

import java.awt.*;

public class Map {
    private int width, height;
    private int spawnX, spawnY;
    private int tileTypes[][];
    private Handler handler;
    //entity manager
    private EntityManager entityManager;

    public Map(Handler handler, String path) {
        this.handler = handler;
        loadMap(path);
        entityManager = new EntityManager(handler,
                new Player(handler, 0.0f, 0.0f, handler.getKeyboardManager(0), AssetManager.player1Animation),
                new Player(handler, 200.0f, 0.0f, handler.getKeyboardManager(1), AssetManager.player1Animation));
        entityManager.getPlayer1().setPosition(spawnX, spawnY);
        entityManager.addEntity(new Tree(handler, 100.0f, 200.0f));
        entityManager.addEntity(new Tree(handler, 100.0f, 300.0f));
        entityManager.addEntity(new Tree(handler, 100.0f, 400.0f));
        entityManager.addEntity(new Tree(handler, 300.0f, 200.0f));

    }

    private void loadMap(String path) {
        String file = Helper.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        width = Helper.parseInt(tokens[0]);
        height = Helper.parseInt(tokens[1]);
        spawnX = Helper.parseInt(tokens[2]);
        spawnY = Helper.parseInt(tokens[3]);

        tileTypes = new int[width][height];
        for(int y=0;y<height;y++) {
            for(int x=0;x<width;x++) {
                tileTypes[x][y] = Helper.parseInt(tokens[(x + y * width) + 4]);
            }
        }
    }

    public void tick() {
        entityManager.tick();
    }

    public void render(Graphics graphics) {
        for(int y=0;y<height;y++) {
            for(int x=0;x<width;x++) {
                getTile(x,y).render(graphics, x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT);
            }
        }

        entityManager.render(graphics);
    }

    //getters
    public Tile getTile(int x, int y) {
        if(x < 0 || y < 0 || x >= width || y >= height)
            return Tile.grassTile;

        Tile tile = Tile.tiles[tileTypes[x][y]];
        if(tile == null) {
            return Tile.dirtTile;
        }
        return tile;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
