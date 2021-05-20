package Entity.Static;

import Texture.Tile;
import Utility.Handler;
import Graphics.AssetManager;

import java.awt.*;

public class Tree extends StaticEntity {

    public Tree(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT * 2);
        boundingRect.x = 10;
        boundingRect.y = (int)(height / 1.5f);
        boundingRect.width = width - 20;
        boundingRect.height = (int)(height - height / 1.5f);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(AssetManager.tree, (int)position.x, (int)position.y, width, height, null);
        /*graphics.setColor(Color.BLUE);
        graphics.fillRect((int)(position.x + boundingRect.x), (int)(position.y + boundingRect.y),
                boundingRect.width, boundingRect.height);*/
    }

    @Override
    public boolean isBreakable() {
        return false;
    }
}
