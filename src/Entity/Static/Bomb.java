package Entity.Static;

import Entity.Creature.Player;
import Entity.Entity;
import Texture.Tile;
import Utility.Handler;
import Graphics.AssetManager;

import java.awt.*;

//待完成
public class Bomb extends StaticEntity {
    private Player player;
    //計時器
    private long timer;
    //丟下炸彈的時間點
    private long droppedTime;
    //炸彈威力
    private int power;

    public Bomb(Handler handler, Player player, int power) {
        super(handler, player.getPosition().x, player.getPosition().y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
        this.player = player;

        //設定bounding box
        boundingRect.x = 0;
        boundingRect.y = 0;
        boundingRect.width = Tile.TILE_WIDTH;
        boundingRect.height = Tile.TILE_HEIGHT;

        //設定計時器
        timer = 0;
        droppedTime = System.currentTimeMillis();

        //設定炸彈數值
        this.power = power;
    }

    @Override
    public void tick() {
        timer = System.currentTimeMillis() - droppedTime;

        if(timer > 2000) {
            explode();
        }
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(AssetManager.unbreakableBox2, (int)position.x, (int)position.y,
                Tile.TILE_WIDTH, Tile.TILE_HEIGHT, null);
        graphics.setColor(Color.RED);
        //center of bomb
        /*graphics.fillRect((int)(position.x + Tile.TILE_WIDTH / 2 - 4),
                (int)(position.y + Tile.TILE_HEIGHT / 2 - 4), 10, 10);*/
        graphics.fillRect((int)(position.x + boundingRect.x), (int)(position.y + boundingRect.y),
                boundingRect.width, boundingRect.height);
    }

    private void explode() {
        //System.out.println("Bom!!!");
        if(!destroyed) {
            //水平爆炸
            handler.getMap().getEntityManager().addExplosion(new Explosion.HorizontalExplosion(handler, this));
            //垂直爆炸
            handler.getMap().getEntityManager().addExplosion(new Explosion.VerticalExplosion(handler, this));
            player.restoreAmmo();
            destroyed = true;
        }
    }

    //檢查炸彈與其他東西的碰撞
    public boolean checkBombCollision() {
        //檢查與entity的碰撞
        for(Entity entity : handler.getMap().getEntityManager().getEntities()) {
            //不檢查要放下的玩家
            if(entity.isDestroyed() || entity.equals(player)) {
                continue;
            }

            //檢查別的entity是否跟自己產生碰撞
            if(entity.getCollisionRect(0.0f, 0.0f).intersects(getCollisionRect(0.0f, 0.0f))) {
                return true;
            }
        }

        //檢查炸彈之間的碰撞
        for(Bomb bomb : handler.getMap().getEntityManager().getBombs()) {
            if(bomb.isDestroyed() || bomb.equals(this)) {
                continue;
            }

            //檢查別人是否跟自己產生碰撞
            if(bomb.getCollisionRect(0.0f, 0.0f).intersects(getCollisionRect(0.0f, 0.0f))) {
                return true;
            }
        }

        return false;
    }

    //getters
    public int getPower() {
        return power;
    }

    public long getTimer() {
        return timer;
    }

    public Player getPlayer() {
        return player;
    }
}
