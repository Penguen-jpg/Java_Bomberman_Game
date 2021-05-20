package Entity.Static;

import Texture.Tile;
import Utility.Handler;
import Graphics.AssetManager;

import java.awt.*;

//TODO:待完成
public class VerticalExplosion extends StaticEntity {
    private Bomb bomb;
    private int waveRange;

    public VerticalExplosion(Handler handler, Bomb bomb) {
        super(handler, bomb.getPosition().x, bomb.getPosition().y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
        this.bomb = bomb;
        waveRange = bomb.getPower() * 2 + 1;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics graphics) {
        if(!destroyed) {
            graphics.drawImage(AssetManager.dirt, (int)position.x,
                    (int)(position.y - Tile.TILE_HEIGHT * bomb.getPower()), Tile.TILE_WIDTH,
                    Tile.TILE_HEIGHT * waveRange, null);
            destroyed = true;
        }
    }

    public void explode() {

    }
}
