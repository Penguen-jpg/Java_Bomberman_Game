package Graphics;

import Texture.SpriteSheet;

import java.awt.image.BufferedImage;

public class AssetManager {
    private static final int TILE_WIDTH = 32, TILE_HEIGHT = 32;
    private static final int PLAYER_WIDTH = 32, PLAYER_HEIGHT = 32;
    public static BufferedImage breakableBox, unbreakableBox, floor1, floor2, wall;
    public static BufferedImage[][] player1Animation;
    public static BufferedImage[][] player2Animation;

    public static void init() {
        SpriteSheet tileSheet = new SpriteSheet(ImageLoader.loadImage("/textures/tile_sheet2.png"));
        SpriteSheet playerSheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));

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
    }
}
