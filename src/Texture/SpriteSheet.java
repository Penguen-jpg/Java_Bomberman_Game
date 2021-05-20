package Texture;

import java.awt.image.BufferedImage;

public class SpriteSheet {
    private BufferedImage sheet;

    public SpriteSheet(BufferedImage sheet) {
        this.sheet = sheet;
    }

    //取得sheet中的一部分
    public BufferedImage clip(int x, int y, int width, int height) {
        return sheet.getSubimage(x, y, width, height);
    }
}
