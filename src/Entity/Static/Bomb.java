package Entity.Static;

import Entity.Creature.Player;
import Entity.Entity;
import Graphics.Animation;
import Graphics.AssetManager;
import Texture.Tile;
import Utility.Handler;

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
    //是否可穿透箱子
    private boolean penetration;
    //動畫
    private Animation bombAnimation;


    public Bomb(Handler handler, Player player, float x, float y, int power, boolean pierce) {
        super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
        this.player = player;

        //設定bounding box
        boundingRect.x = 0;
        boundingRect.y = 0;
        boundingRect.width = Tile.TILE_WIDTH;
        boundingRect.height = Tile.TILE_HEIGHT;

        //設定動畫
        bombAnimation = new Animation(500, AssetManager.bombAnimation);

        //設定計時器
        timer = 0;
        droppedTime = System.currentTimeMillis();

        //設定炸彈數值
        this.power = power;
        this.penetration = pierce;
    }

    @Override
    public void tick() {
        bombAnimation.tick();
        //計時
        timer = System.currentTimeMillis() - droppedTime;

        if (timer > 2000) {
            explode();
        }
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(bombAnimation.getCurrentFrame(), (int) position.x, (int) position.y
                , Tile.TILE_WIDTH, Tile.TILE_HEIGHT, null);
        /*graphics.setColor(Color.RED);
        graphics.fillRect((int) (position.x + boundingRect.x), (int) (position.y + boundingRect.y)
                , boundingRect.width, boundingRect.height);*/
    }

    @Override
    public void onDestroy() {

    }

    //引爆炸彈
    private void explode() {
        //水平爆炸
        handler.getEntityManager().addExplosion(new Explosion.HorizontalExplosion(handler, this));
        //垂直爆炸
        handler.getEntityManager().addExplosion(new Explosion.VerticalExplosion(handler, this));
        player.restoreAmmo();
        destroyed = true;
    }

    //檢查炸彈與其他東西的碰撞
    public boolean checkBombCollision() {
        //檢查與entity的碰撞
        for (Entity entity : handler.getEntityManager().getEntities()) {
            //不檢查要放下的玩家
            if (entity.equals(player)) {
                continue;
            }

            //檢查別的entity是否跟自己產生碰撞
            if (entity.getCollisionRect(0.0f, 0.0f).intersects(getCollisionRect(0.0f, 0.0f))) {
                return true;
            }
        }

        //檢查炸彈之間的碰撞
        for (Bomb bomb : handler.getEntityManager().getBombs()) {
            if (bomb.equals(this)) {
                continue;
            }

            //檢查別的炸彈是否跟這顆炸彈產生碰撞
            if (bomb.getCollisionRect(0.0f, 0.0f).intersects(getCollisionRect(0.0f, 0.0f))) {
                return true;
            }
        }

        return false;
    }

    //getters
    public int getPower() {
        return power;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean hasPenetration() {
        return penetration;
    }
}
