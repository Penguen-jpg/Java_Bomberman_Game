package Texture;

import java.awt.image.BufferedImage;

public class CornerTile extends Tile {
    public CornerTile(BufferedImage texture, int type) {
        super(texture, type);
    }

    @Override
    public boolean isSolid() {
        return true;
    }
}
