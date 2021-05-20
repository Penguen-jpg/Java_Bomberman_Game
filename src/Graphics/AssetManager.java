package Graphics;

import Texture.SpriteSheet;

import java.awt.image.BufferedImage;

public class AssetManager {
    private static final int WIDTH = 32, HEIGHT = 32;
    public static BufferedImage dirt, grass, stone, tree, rock, breakableBox, unbreakableBox, bomb;
    public static BufferedImage[][] player1Animation;
    public static BufferedImage[][] player2Animation;

    public static void init() {
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));

        player1Animation = new BufferedImage[4][2];

        player1Animation[0][0] = sheet.clip(WIDTH * 6, 0, WIDTH, HEIGHT);
        player1Animation[0][1] = sheet.clip(WIDTH * 7, 0, WIDTH, HEIGHT);
        player1Animation[1][0] = sheet.clip(WIDTH * 4, 0, WIDTH, HEIGHT);
        player1Animation[1][1] = sheet.clip(WIDTH * 5, 0, WIDTH, HEIGHT);
        player1Animation[2][0] = sheet.clip(WIDTH * 6, HEIGHT, WIDTH, HEIGHT);
        player1Animation[2][1] = sheet.clip(WIDTH * 7, HEIGHT, WIDTH, HEIGHT);
        player1Animation[3][0] = sheet.clip(WIDTH * 4, HEIGHT, WIDTH, HEIGHT);
        player1Animation[3][1] = sheet.clip(WIDTH * 5, HEIGHT, WIDTH, HEIGHT);
        dirt = sheet.clip(WIDTH, 0, WIDTH, HEIGHT);
        grass = sheet.clip(WIDTH * 2, 0, WIDTH, HEIGHT);
        stone = sheet.clip(WIDTH * 3, 0, WIDTH, HEIGHT);
        tree = sheet.clip(0, 0, WIDTH, HEIGHT * 2);
        rock = sheet.clip(0, HEIGHT * 2, WIDTH, HEIGHT);
    }
}
