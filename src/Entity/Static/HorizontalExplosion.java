package Entity.Static;

import Entity.Entity;
import Texture.Tile;
import Utility.Handler;
import Graphics.AssetManager;

import java.awt.*;

public class HorizontalExplosion extends StaticEntity {
    private Bomb bomb;
    private int waveRange;
    private int maxRange;

    public HorizontalExplosion(Handler handler, Bomb bomb) {
        super(handler, bomb.getPosition().x, bomb.getPosition().y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
        this.bomb = bomb;

        //設定爆炸數值
        maxRange = bomb.getPower() * 2 + 1;
        waveRange = 0;

        //設定bounding box
        boundingRect.x = (Tile.TILE_WIDTH * bomb.getPower()) * -1;
        boundingRect.y = 0;
        boundingRect.width = Tile.TILE_WIDTH;
        boundingRect.height = Tile.TILE_HEIGHT;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics graphics) {
        int startX = (int)(position.x - Tile.TILE_WIDTH * bomb.getPower());
        int upperY = (int)(position.y + boundingRect.y) / Tile.TILE_HEIGHT;
        int lowerY = (int)(position.y + boundingRect.y + boundingRect.height) / Tile.TILE_HEIGHT;
        waveRange = maxRange;
        for(int n=0;n<waveRange;n++) {
            //檢查是否碰撞到solid tile和unbreakable entity
            /*if(!hasCollisionWithTile(startX / Tile.TILE_WIDTH, upperY)
                    && !hasCollisionWithTile(startX / Tile.TILE_HEIGHT, lowerY)
                    && !hasCollisionWithEntity())*/
            {
                boundingRect.width += Tile.TILE_WIDTH;
                System.out.println("Collide with tile");
            }
        }
        graphics.drawImage(AssetManager.stone, (int)(position.x - Tile.TILE_WIDTH * bomb.getPower()),
                (int)position.y, Tile.TILE_WIDTH * waveRange,
                Tile.TILE_HEIGHT, null);
        graphics.setColor(Color.BLUE);
        graphics.fillRect((int)(position.x + boundingRect.x), (int)(position.y + boundingRect.y),
                boundingRect.width, boundingRect.height);
        destroyed = true;
        waveRange = 0;
    }

    public void explode() {

    }

    /*private boolean checkHorizontalExplosion(float xOffset) {
        for(Entity entity : handler.getMap().getEntityManager().getEntities()) {
            if(!entity.isBreakable()) {
                continue;
            }

            if(entity.getCollisionRect(0.0f, 0.0f).intersects(getCollisionRect(xOffset, 0.0f))) {
                entity.setDestroyed(true);
                return true;
            }
        }
    }*/

    //檢查與tile的碰撞
    private boolean hasCollisionWithTile(int x, int y) {
        return handler.getMap().getTile(x, y).isSolid();
    }

    /*private boolean hasCollisionWithEntity(float x, float y) {

    }*/
}
