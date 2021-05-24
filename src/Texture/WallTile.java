package Texture;

import java.awt.image.BufferedImage;

public class WallTile extends Tile {
    public WallTile(BufferedImage texture, int type) {
        super(texture, type);
    }

    @Override
    public boolean isSolid() {
        return true;
    }
}
