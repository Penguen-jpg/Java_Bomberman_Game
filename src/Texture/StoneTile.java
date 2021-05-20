package Texture;

import Graphics.AssetManager;

import java.awt.image.BufferedImage;

public class StoneTile extends Tile {
    public StoneTile(int id) {
        super(AssetManager.stone, id);
    }

    @Override
    public boolean isSolid() {
        return true;
    }
}
