package Graphics;

import Texture.SpriteSheet;

import java.awt.image.BufferedImage;

public class AssetManager {
    private static final int TILE_WIDTH = 32, TILE_HEIGHT = 32;
    private static final int PLAYER_WIDTH = 32, PLAYER_HEIGHT = 32;
    public static BufferedImage breakableBox, unbreakableBox1, unbreakableBox2,
            floor1, floor2, floor3, floor4, topLeftCorner, topFloor, topRightCorner,
            bottomLeftCorner, bottomFloor, bottomRightCorner, leftSideFloor, rightSideFloor;
    public static BufferedImage[][] player1Animation;
    public static BufferedImage[][] player2Animation;

    public static void init() {
        SpriteSheet tileSheet = new SpriteSheet(ImageLoader.loadImage("/textures/tile_sheet.png"));
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
        unbreakableBox1 = tileSheet.clip(TILE_WIDTH, 0, TILE_WIDTH, TILE_HEIGHT);
        unbreakableBox2 = tileSheet.clip(TILE_WIDTH * 2, 0, TILE_WIDTH, TILE_HEIGHT);
        floor1 = tileSheet.clip(TILE_WIDTH * 3, 0, TILE_WIDTH, TILE_HEIGHT);
        floor2 = tileSheet.clip(TILE_WIDTH * 4, 0, TILE_WIDTH, TILE_HEIGHT);
        floor3 = tileSheet.clip(0, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
        floor4 = tileSheet.clip(TILE_WIDTH, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
        topLeftCorner = tileSheet.clip(TILE_WIDTH * 2, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
        topFloor = tileSheet.clip(TILE_WIDTH * 3, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
        topRightCorner = tileSheet.clip(TILE_WIDTH * 4, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
        bottomLeftCorner = tileSheet.clip(0, TILE_HEIGHT * 2, TILE_WIDTH, TILE_HEIGHT);
        bottomFloor = tileSheet.clip(TILE_WIDTH, TILE_HEIGHT * 2, TILE_WIDTH, TILE_HEIGHT);
        bottomRightCorner = tileSheet.clip(TILE_WIDTH * 2, TILE_HEIGHT * 2, TILE_WIDTH, TILE_HEIGHT);
        leftSideFloor = tileSheet.clip(TILE_WIDTH * 3, TILE_HEIGHT * 2, TILE_WIDTH, TILE_HEIGHT);
        rightSideFloor = tileSheet.clip(TILE_WIDTH * 4, TILE_HEIGHT * 2, TILE_WIDTH, TILE_HEIGHT);
    }
}
