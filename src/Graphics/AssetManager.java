package Graphics;

import Texture.SpriteSheet;

import java.awt.Font;
import java.awt.image.BufferedImage;

public class AssetManager {
    private static final int TILE_WIDTH = 32, TILE_HEIGHT = 32;
    private static final int PLAYER_WIDTH = 32, PLAYER_HEIGHT = 32;
    private static final int ITEM_WIDTH = 64, ITEM_HEIGHT = 64;
    public static Font font120;
    public static BufferedImage breakableBox, unbreakableBox, floor1, floor2, wall;
    public static BufferedImage[][] player1Animation;
    public static BufferedImage[][] player2Animation;
    public static BufferedImage centralExplosion;
    public static BufferedImage horizontalExplosion;
    public static BufferedImage verticalExplosion;
    public static BufferedImage powerUp, speedUp, ammoUp, pierce;

    public static void init() {
        //font
        font120 = FontLoader.loadFont("src/res/fonts/Silver.ttf", 120);

        SpriteSheet tileSheet = new SpriteSheet(ImageLoader.loadImage("/textures/tile_sheet3.png"));
        SpriteSheet playerSheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));
        SpriteSheet explosionSheet = new SpriteSheet(ImageLoader.loadImage("/textures/explosion_sheet.png"));
        SpriteSheet itemSheet = new SpriteSheet(ImageLoader.loadImage("/textures/item_sheet.png"));

        //player
        player1Animation = new BufferedImage[4][2];
        player1Animation[0][0] = playerSheet.clip(PLAYER_WIDTH * 6, 0, PLAYER_WIDTH, PLAYER_HEIGHT);
        player1Animation[0][1] = playerSheet.clip(PLAYER_WIDTH * 7, 0, PLAYER_WIDTH, PLAYER_HEIGHT);
        player1Animation[1][0] = playerSheet.clip(PLAYER_WIDTH * 4, 0, PLAYER_WIDTH, PLAYER_HEIGHT);
        player1Animation[1][1] = playerSheet.clip(PLAYER_WIDTH * 5, 0, PLAYER_WIDTH, PLAYER_HEIGHT);
        player1Animation[2][0] = playerSheet.clip(PLAYER_WIDTH * 6, PLAYER_HEIGHT, PLAYER_WIDTH, PLAYER_HEIGHT);
        player1Animation[2][1] = playerSheet.clip(PLAYER_WIDTH * 7, PLAYER_HEIGHT, PLAYER_WIDTH, PLAYER_HEIGHT);
        player1Animation[3][0] = playerSheet.clip(PLAYER_WIDTH * 4, PLAYER_HEIGHT, PLAYER_WIDTH, PLAYER_HEIGHT);
        player1Animation[3][1] = playerSheet.clip(PLAYER_WIDTH * 5, PLAYER_HEIGHT, PLAYER_WIDTH, PLAYER_HEIGHT);

        //tile
        breakableBox = tileSheet.clip(0, 0, TILE_WIDTH, TILE_HEIGHT);
        unbreakableBox = tileSheet.clip(TILE_WIDTH, 0, TILE_WIDTH, TILE_HEIGHT);
        floor1 = tileSheet.clip(TILE_WIDTH * 2, 0, TILE_WIDTH, TILE_HEIGHT);
        floor2 = tileSheet.clip(0, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
        wall = tileSheet.clip(TILE_WIDTH, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);

        //explosion
        centralExplosion = explosionSheet.clip(0, TILE_HEIGHT * 2, TILE_WIDTH, TILE_HEIGHT);
        horizontalExplosion = explosionSheet.clip(TILE_WIDTH * 2, TILE_HEIGHT * 2, TILE_WIDTH, TILE_HEIGHT);
        verticalExplosion = explosionSheet.clip(TILE_WIDTH, TILE_HEIGHT * 2, TILE_WIDTH, TILE_HEIGHT);

        //item
        powerUp = itemSheet.clip(0, ITEM_HEIGHT * 3, ITEM_WIDTH, ITEM_HEIGHT);
        speedUp = itemSheet.clip(0, ITEM_HEIGHT, ITEM_WIDTH, ITEM_HEIGHT);
        ammoUp = itemSheet.clip(0, ITEM_HEIGHT * 2, ITEM_WIDTH, ITEM_HEIGHT);
        pierce = itemSheet.clip(ITEM_WIDTH * 2, 0, ITEM_WIDTH, ITEM_HEIGHT);
    }
}
