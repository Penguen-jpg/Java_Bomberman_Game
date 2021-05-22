package Entity.Creature;

import Entity.Entity;
import Entity.Static.Bomb;
import Entity.Static.Explosion;
import Texture.Tile;
import Utility.Handler;
import Utility.Vector2D;

import java.awt.*;

public abstract class Creature extends Entity {
    public static final int DEFAULT_HEALTH = 10;
    public static final float DEFAULT_SPEED = 3.0f;
    public static final int DEFAULT_CREATURE_WIDTH = 64;
    public static final int DEFAULT_CREATURE_HEIGHT = 64;
    protected int health;
    //移動速度
    protected float speed;
    //速度向量
    protected Vector2D velocity;

    public Creature(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        health = DEFAULT_HEALTH;
        speed = DEFAULT_SPEED;
        velocity = new Vector2D(0.0f, 0.0f);
    }

    public void move() {
        if(!checkEntityCollision(velocity.x * speed, 0.0f)
                && !hasCollisionWithBomb(velocity.x * speed, 0.0f)) {
            moveX();
        }
        if(!checkEntityCollision(0.0f, velocity.y * speed)
                && !hasCollisionWithBomb(0.0f, velocity.y * speed)) {
            moveY();
        }
    }

    //x方向的移動和碰撞
    public void moveX() {
        if(velocity.x > 0.0f) { //往右
            int gridX = (int)(position.x + velocity.x + boundingRect.x + boundingRect.width) / Tile.TILE_WIDTH;
            //檢查右上角和右下角的碰撞
            if(!hasCollisionWithTile(gridX, (int)(position.y + boundingRect.y) / Tile.TILE_HEIGHT)
                    && !hasCollisionWithTile(gridX, (int)(position.y + boundingRect.y + boundingRect.height))) {
                position.x += velocity.x;
            }else {
                position.x = gridX * Tile.TILE_WIDTH - boundingRect.x - boundingRect.width - 1;
            }
        }else if(velocity.x < 0.0f) { //往左
            int gridX = (int)(position.x + velocity.x + boundingRect.x) / Tile.TILE_WIDTH;
            //檢查左上角和左下角的碰撞
            if(!hasCollisionWithTile(gridX, (int)(position.y + boundingRect.y) / Tile.TILE_HEIGHT)
                    && !hasCollisionWithTile(gridX, (int)(position.y + boundingRect.y + boundingRect.height))) {
                position.x += velocity.x;
            }else {
                position.x = gridX * Tile.TILE_WIDTH + Tile.TILE_WIDTH - boundingRect.x;
            }
        }
    }

    //y方向的移動和碰撞
    public void moveY() {
        if(velocity.y > 0.0f) { //往下
            int gridY = (int)(position.y + velocity.y + boundingRect.y + boundingRect.height) / Tile.TILE_HEIGHT;
            //檢查左下角和右下角
            if(!hasCollisionWithTile((int)(position.x + boundingRect.x) / Tile.TILE_WIDTH, gridY)
                    && !hasCollisionWithTile((int)(position.x + boundingRect.x + boundingRect.width) / Tile.TILE_WIDTH, gridY)) {
                position.y += velocity.y;
            }else {
                position.y = gridY * Tile.TILE_HEIGHT - boundingRect.y - boundingRect.height - 1;
            }
        }else if(velocity.y < 0.0f) { //往上
            int gridY = (int)(position.y + velocity.y + boundingRect.y) / Tile.TILE_HEIGHT;
            //檢查左上角和右上角
            if(!hasCollisionWithTile((int)(position.x + boundingRect.x) / Tile.TILE_WIDTH, gridY)
                    && !hasCollisionWithTile((int)(position.x + boundingRect.x + boundingRect.width) / Tile.TILE_WIDTH, gridY)) {
                position.y += velocity.y;
            }else {
                position.y = gridY * Tile.TILE_HEIGHT + Tile.TILE_HEIGHT - boundingRect.y;
            }
        }
    }

    //檢查與solid tile的碰撞
    private boolean hasCollisionWithTile(int x, int y) {
        return handler.getMap().isSolidTile(x, y);
    }

    //檢查與炸彈的碰撞
    protected boolean hasCollisionWithBomb(float xOffset, float yOffset) {
        for(Bomb bomb : handler.getEntityManager().getBombs()) {
            if(!bomb.isDestroyed()
                    && bomb.getCollisionRect(0.0f, 0.0f).intersects(getCollisionRect(xOffset, yOffset)))
            {
                return true;
            }
        }
        return false;
    }

    //檢查與爆炸的碰撞
    protected void checkCollisionWithExplosion() {
        for(Explosion explosion : handler.getEntityManager().getExplosions()) {
            if(!explosion.isDestroyed()
                    && explosion.getCollisionRect(0.0f, 0.0f)
                    .intersects(getCollisionRect(0.0f, 0.0f))) {
                Rectangle temp = explosion.getCollisionRect(0.0f, 0.0f);
                System.out.println(temp);
                destroyed = true;
            }
        }
    }

    @Override
    public boolean isBreakable() {
        return true;
    }

    //getters/setters
    public int getHealth() {
        return health;
    }

    public float getSpeed() {
        return speed;
    }

    public Vector2D getVelocity() {
        return velocity;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setVector(float x, float y){
        velocity.x = x;
        velocity.y = y;
    }
}
