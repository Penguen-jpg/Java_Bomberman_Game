package Entity.Static;

import Item.Item;
import Texture.Tile;
import Utility.Handler;
import Graphics.AssetManager;

import java.awt.*;
import java.util.Random;

public class BreakableBox extends StaticEntity {
    public BreakableBox(Handler handler, float x, float y) {
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
        graphics.setColor(Color.GREEN);
        graphics.fillRect((int)position.x, (int)position.y, boundingRect.width, boundingRect.height);
    }

    @Override
    public void onDestroy() {
        Random random = new Random();
        int itemType = random.nextInt(3);
        if(itemType == 0) {
            handler.getItemManager()
                    .addItem(Item.powerUpItem.createItem((int)(position.x + 16), (int)(position.y + 16)));
        }else if(itemType == 1) {
            handler.getItemManager()
                    .addItem(Item.speedUpItem.createItem((int)(position.x + 16), (int)(position.y + 16)));
        }else if(itemType == 2) {
            handler.getItemManager()
                    .addItem(Item.ammoUpItem.createItem((int)(position.x + 16), (int)(position.y + 16)));
        }
    }

    @Override
    public boolean isBreakable() {
        return true;
    }
}
