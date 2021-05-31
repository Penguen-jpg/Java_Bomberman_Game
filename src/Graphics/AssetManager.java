package Graphics;

import Texture.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AssetManager {
    private static final int TILE_WIDTH = 32, TILE_HEIGHT = 32;
    private static final int PLAYER_WIDTH = 64, PLAYER_HEIGHT = 64;
    private static final int ITEM_WIDTH = 64, ITEM_HEIGHT = 64;
    private static final int BUTTON_WIDTH = 128, BUTTON_HEIGHT = 64;
    private static final int BOMB_WIDTH = 64, BOMB_HEIGHT = 64;
    private static final int MENU_WIDTH = 832, MENU_HEIGHT = 640;
    public static Font font120;
    public static BufferedImage breakableBox, unbreakableBox, floor1, floor2, wall;
    public static BufferedImage[][] player1Animation;
    public static BufferedImage[][] player2Animation;
    public static BufferedImage[] bombAnimation;
    public static BufferedImage centralExplosion;
    public static BufferedImage horizontalExplosion;
    public static BufferedImage verticalExplosion;
    public static BufferedImage powerUp, speedUp, ammoUp, pierce;
    public static BufferedImage[] startButton, exitButton;
    public static BufferedImage menu;

    public static void init() {
        //font
        font120 = FontLoader.loadFont("src/res/fonts/Silver.ttf", 120);

        //sheet
        SpriteSheet entityAndTileSheet = new SpriteSheet(ImageLoader.loadImage("/textures/entity_and_tile_sheet.png"));
        SpriteSheet itemSheet = new SpriteSheet(ImageLoader.loadImage("/textures/item_sheet.png"));
        SpriteSheet menuSheet = new SpriteSheet(ImageLoader.loadImage("/textures/menu_sheet.png"));

        //tile
        breakableBox = entityAndTileSheet.clip(0, 0, TILE_WIDTH, TILE_HEIGHT);
        unbreakableBox = entityAndTileSheet.clip(TILE_WIDTH, 0, TILE_WIDTH, TILE_HEIGHT);
        floor1 = entityAndTileSheet.clip(TILE_WIDTH * 2, 0, TILE_WIDTH, TILE_HEIGHT);
        floor2 = entityAndTileSheet.clip(0, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
        wall = entityAndTileSheet.clip(TILE_WIDTH, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);

        //player1
        player1Animation = new BufferedImage[5][];
        player1Animation[0] = new BufferedImage[4];
        player1Animation[1] = new BufferedImage[4];
        player1Animation[2] = new BufferedImage[6];
        player1Animation[3] = new BufferedImage[6];
        player1Animation[4] = new BufferedImage[2];
        player1Animation[0][0] = entityAndTileSheet.clip(0, PLAYER_HEIGHT, PLAYER_WIDTH, PLAYER_HEIGHT);
        player1Animation[0][1] = entityAndTileSheet.clip(PLAYER_WIDTH, PLAYER_HEIGHT, PLAYER_WIDTH, PLAYER_HEIGHT);
        player1Animation[0][2] = entityAndTileSheet.clip(PLAYER_WIDTH * 2, PLAYER_HEIGHT, PLAYER_WIDTH, PLAYER_HEIGHT);
        player1Animation[0][3] = entityAndTileSheet.clip(PLAYER_WIDTH * 3, PLAYER_HEIGHT, PLAYER_WIDTH, PLAYER_HEIGHT);
        player1Animation[1][0] = entityAndTileSheet.clip(0, PLAYER_HEIGHT * 2, PLAYER_WIDTH, PLAYER_HEIGHT);
        player1Animation[1][1] = entityAndTileSheet.clip(PLAYER_WIDTH, PLAYER_HEIGHT * 2, PLAYER_WIDTH, PLAYER_HEIGHT);
        player1Animation[1][2] = entityAndTileSheet.clip(PLAYER_WIDTH * 2, PLAYER_HEIGHT * 2, PLAYER_WIDTH, PLAYER_HEIGHT);
        player1Animation[1][3] = entityAndTileSheet.clip(PLAYER_WIDTH * 3, PLAYER_HEIGHT * 2, PLAYER_WIDTH, PLAYER_HEIGHT);
        player1Animation[2][0] = entityAndTileSheet.clip(0, PLAYER_HEIGHT * 3, PLAYER_WIDTH, PLAYER_HEIGHT);
        player1Animation[2][1] = entityAndTileSheet.clip(PLAYER_WIDTH, PLAYER_HEIGHT * 3, PLAYER_WIDTH, PLAYER_HEIGHT);
        player1Animation[2][2] = entityAndTileSheet.clip(PLAYER_WIDTH * 2, PLAYER_HEIGHT * 3, PLAYER_WIDTH, PLAYER_HEIGHT);
        player1Animation[2][3] = entityAndTileSheet.clip(PLAYER_WIDTH * 3, PLAYER_HEIGHT * 3, PLAYER_WIDTH, PLAYER_HEIGHT);
        player1Animation[2][4] = entityAndTileSheet.clip(PLAYER_WIDTH * 4, PLAYER_HEIGHT * 3, PLAYER_WIDTH, PLAYER_HEIGHT);
        player1Animation[2][5] = entityAndTileSheet.clip(PLAYER_WIDTH * 5, PLAYER_HEIGHT * 3, PLAYER_WIDTH, PLAYER_HEIGHT);
        player1Animation[3][0] = entityAndTileSheet.clip(0, PLAYER_HEIGHT * 4, PLAYER_WIDTH, PLAYER_HEIGHT);
        player1Animation[3][1] = entityAndTileSheet.clip(PLAYER_WIDTH, PLAYER_HEIGHT * 4, PLAYER_WIDTH, PLAYER_HEIGHT);
        player1Animation[3][2] = entityAndTileSheet.clip(PLAYER_WIDTH * 2, PLAYER_HEIGHT * 4, PLAYER_WIDTH, PLAYER_HEIGHT);
        player1Animation[3][3] = entityAndTileSheet.clip(PLAYER_WIDTH * 3, PLAYER_HEIGHT * 4, PLAYER_WIDTH, PLAYER_HEIGHT);
        player1Animation[3][4] = entityAndTileSheet.clip(PLAYER_WIDTH * 4, PLAYER_HEIGHT * 4, PLAYER_WIDTH, PLAYER_HEIGHT);
        player1Animation[3][5] = entityAndTileSheet.clip(PLAYER_WIDTH * 5, PLAYER_HEIGHT * 4, PLAYER_WIDTH, PLAYER_HEIGHT);
        player1Animation[4][0] = entityAndTileSheet.clip(0, PLAYER_HEIGHT * 5, PLAYER_WIDTH, PLAYER_HEIGHT);
        player1Animation[4][1] = entityAndTileSheet.clip(PLAYER_WIDTH, PLAYER_HEIGHT * 5, PLAYER_WIDTH, PLAYER_HEIGHT);

        //player2
        player2Animation = new BufferedImage[5][];
        player2Animation[0] = new BufferedImage[4];
        player2Animation[1] = new BufferedImage[4];
        player2Animation[2] = new BufferedImage[6];
        player2Animation[3] = new BufferedImage[6];
        player2Animation[4] = new BufferedImage[2];
        player2Animation[0][0] = entityAndTileSheet.clip(0, PLAYER_HEIGHT * 6, PLAYER_WIDTH, PLAYER_HEIGHT);
        player2Animation[0][1] = entityAndTileSheet.clip(PLAYER_WIDTH, PLAYER_HEIGHT * 6, PLAYER_WIDTH, PLAYER_HEIGHT);
        player2Animation[0][2] = entityAndTileSheet.clip(PLAYER_WIDTH * 2, PLAYER_HEIGHT * 6, PLAYER_WIDTH, PLAYER_HEIGHT);
        player2Animation[0][3] = entityAndTileSheet.clip(PLAYER_WIDTH * 3, PLAYER_HEIGHT * 6, PLAYER_WIDTH, PLAYER_HEIGHT);
        player2Animation[1][0] = entityAndTileSheet.clip(0, PLAYER_HEIGHT * 7, PLAYER_WIDTH, PLAYER_HEIGHT);
        player2Animation[1][1] = entityAndTileSheet.clip(PLAYER_WIDTH, PLAYER_HEIGHT * 7, PLAYER_WIDTH, PLAYER_HEIGHT);
        player2Animation[1][2] = entityAndTileSheet.clip(PLAYER_WIDTH * 2, PLAYER_HEIGHT * 7, PLAYER_WIDTH, PLAYER_HEIGHT);
        player2Animation[1][3] = entityAndTileSheet.clip(PLAYER_WIDTH * 3, PLAYER_HEIGHT * 7, PLAYER_WIDTH, PLAYER_HEIGHT);
        player2Animation[2][0] = entityAndTileSheet.clip(0, PLAYER_HEIGHT * 8, PLAYER_WIDTH, PLAYER_HEIGHT);
        player2Animation[2][1] = entityAndTileSheet.clip(PLAYER_WIDTH, PLAYER_HEIGHT * 8, PLAYER_WIDTH, PLAYER_HEIGHT);
        player2Animation[2][2] = entityAndTileSheet.clip(PLAYER_WIDTH * 2, PLAYER_HEIGHT * 8, PLAYER_WIDTH, PLAYER_HEIGHT);
        player2Animation[2][3] = entityAndTileSheet.clip(PLAYER_WIDTH * 3, PLAYER_HEIGHT * 8, PLAYER_WIDTH, PLAYER_HEIGHT);
        player2Animation[2][4] = entityAndTileSheet.clip(PLAYER_WIDTH * 4, PLAYER_HEIGHT * 8, PLAYER_WIDTH, PLAYER_HEIGHT);
        player2Animation[2][5] = entityAndTileSheet.clip(PLAYER_WIDTH * 5, PLAYER_HEIGHT * 8, PLAYER_WIDTH, PLAYER_HEIGHT);
        player2Animation[3][0] = entityAndTileSheet.clip(0, PLAYER_HEIGHT * 9, PLAYER_WIDTH, PLAYER_HEIGHT);
        player2Animation[3][1] = entityAndTileSheet.clip(PLAYER_WIDTH, PLAYER_HEIGHT * 9, PLAYER_WIDTH, PLAYER_HEIGHT);
        player2Animation[3][2] = entityAndTileSheet.clip(PLAYER_WIDTH * 2, PLAYER_HEIGHT * 9, PLAYER_WIDTH, PLAYER_HEIGHT);
        player2Animation[3][3] = entityAndTileSheet.clip(PLAYER_WIDTH * 3, PLAYER_HEIGHT * 9, PLAYER_WIDTH, PLAYER_HEIGHT);
        player2Animation[3][4] = entityAndTileSheet.clip(PLAYER_WIDTH * 4, PLAYER_HEIGHT * 9, PLAYER_WIDTH, PLAYER_HEIGHT);
        player2Animation[3][5] = entityAndTileSheet.clip(PLAYER_WIDTH * 5, PLAYER_HEIGHT * 9, PLAYER_WIDTH, PLAYER_HEIGHT);
        player2Animation[4][0] = entityAndTileSheet.clip(0, PLAYER_HEIGHT * 10, PLAYER_WIDTH, PLAYER_HEIGHT);
        player2Animation[4][1] = entityAndTileSheet.clip(PLAYER_WIDTH, PLAYER_HEIGHT * 10, PLAYER_WIDTH, PLAYER_HEIGHT);

        //bomb
        bombAnimation = new BufferedImage[4];
        bombAnimation[0] = entityAndTileSheet.clip(0, PLAYER_HEIGHT * 11, BOMB_WIDTH, BOMB_HEIGHT);
        bombAnimation[1] = entityAndTileSheet.clip(BOMB_WIDTH, PLAYER_HEIGHT * 11, BOMB_WIDTH, BOMB_HEIGHT);
        bombAnimation[2] = entityAndTileSheet.clip(BOMB_WIDTH * 2, PLAYER_HEIGHT * 11, BOMB_WIDTH, BOMB_HEIGHT);
        bombAnimation[3] = entityAndTileSheet.clip(BOMB_WIDTH * 3, PLAYER_HEIGHT * 11, BOMB_WIDTH, BOMB_HEIGHT);

        //explosion
        centralExplosion = entityAndTileSheet.clip(0, PLAYER_HEIGHT * 12, TILE_WIDTH, TILE_HEIGHT);
        verticalExplosion = entityAndTileSheet.clip(TILE_WIDTH, PLAYER_HEIGHT * 12, TILE_WIDTH, TILE_HEIGHT);
        horizontalExplosion = entityAndTileSheet.clip(TILE_WIDTH * 2, PLAYER_HEIGHT * 12, TILE_WIDTH, TILE_HEIGHT);

        //item
        powerUp = itemSheet.clip(0, ITEM_HEIGHT * 3, ITEM_WIDTH, ITEM_HEIGHT);
        speedUp = itemSheet.clip(0, ITEM_HEIGHT, ITEM_WIDTH, ITEM_HEIGHT);
        ammoUp = itemSheet.clip(0, ITEM_HEIGHT * 2, ITEM_WIDTH, ITEM_HEIGHT);
        pierce = itemSheet.clip(ITEM_WIDTH * 2, 0, ITEM_WIDTH, ITEM_HEIGHT);

        //button
        startButton = new BufferedImage[2];
        exitButton = new BufferedImage[2];
        startButton[0] = menuSheet.clip(0, 0, BUTTON_WIDTH, BUTTON_HEIGHT);
        startButton[1] = menuSheet.clip(BUTTON_WIDTH, 0, BUTTON_WIDTH, BUTTON_HEIGHT);
        exitButton[0] = menuSheet.clip(0, BUTTON_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);
        exitButton[1] = menuSheet.clip(BUTTON_WIDTH, BUTTON_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);

        //menu
        menu = menuSheet.clip(0, BUTTON_HEIGHT * 2, MENU_WIDTH, MENU_HEIGHT);
    }
}
