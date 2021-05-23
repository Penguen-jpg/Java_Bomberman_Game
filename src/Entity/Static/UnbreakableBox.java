package Entity.Static;

import Texture.Tile;
import Utility.Handler;
import Graphics.AssetManager;

import java.awt.*;

public class UnbreakableBox extends StaticEntity {
    public UnbreakableBox(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);

        //設定bounding box
        boundingRect.x = 0;
        boundingRect.y = 0;
        boundingRect.width = Tile.TILE_WIDTH;
        boundingRect.height = Tile.TILE_HEIGHT;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(AssetManager.breakableBox, (int)position.x, (int)position.y, width, height, null);
        graphics.setColor(Color.CYAN);
        graphics.fillRect((int)position.x, (int)position.y, boundingRect.width, boundingRect.height);
    }

    @Override
    public void onDestroy() {

    }
}
