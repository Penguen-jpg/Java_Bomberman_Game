package Texture;

import java.awt.image.BufferedImage;

public class CornerTile extends Tile {
    public CornerTile(BufferedImage texture, int id) {
        super(texture, id);
    }

    @Override
    public boolean isSolid() {
        return true;
    }
}
