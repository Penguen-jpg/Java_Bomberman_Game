package Entity.Creature;

import Entity.Entity;
import Entity.Static.Bomb;
import Texture.Tile;
import Utility.Handler;
import Utility.Vector2D;

public abstract class Creature extends Entity {
    public static final int DEFAULT_HEALTH = 10;
    public static final float DEFAULT_SPEED = 3.0f;
    public static final int DEFAULT_CREATURE_WIDTH = 64;
    public static final int DEFAULT_CREATURE_HEIGHT = 64;
    protected int health;
    protected float speed;
    protected Vector2D direction;

    public Creature(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        health = DEFAULT_HEALTH;
        speed = DEFAULT_SPEED;
        direction = new Vector2D(0.0f, 0.0f);
    }

    public void move() {
        if(!checkEntityCollision(direction.x * speed, 0.0f)
                && !hasCollisionWithBomb(direction.x * speed, 0.0f)) {
            moveX();
        }
        if(!checkEntityCollision(0.0f, direction.y * speed)
                && !hasCollisionWithBomb(0.0f, direction.y * speed)) {
            moveY();
        }
    }

    //x方向的移動和碰撞
    public void moveX() {
        float xMove = direction.x * speed;

        if(xMove > 0.0f) { //往右
            int tx = (int)(position.x + xMove + boundingRect.x + boundingRect.width) / Tile.TILE_WIDTH;
            //檢查右上角和右下角的碰撞
            if(!hasCollisionWithTile(tx, (int)(position.y + boundingRect.y) / Tile.TILE_HEIGHT)
                    && !hasCollisionWithTile(tx, (int)(position.y + boundingRect.y + boundingRect.height))) {
                position.x += xMove;
            }else {
                position.x = tx * Tile.TILE_WIDTH - boundingRect.x - boundingRect.width - 1;
            }
        }else if(xMove < 0.0f) { //往左
            int tx = (int)(position.x + xMove + boundingRect.x) / Tile.TILE_WIDTH;
            //檢查左上角和左下角的碰撞
            if(!hasCollisionWithTile(tx, (int)(position.y + boundingRect.y) / Tile.TILE_HEIGHT)
                    && !hasCollisionWithTile(tx, (int)(position.y + boundingRect.y + boundingRect.height))) {
                position.x += xMove;
            }else {
                position.x = tx * Tile.TILE_WIDTH + Tile.TILE_WIDTH - boundingRect.x;
            }
        }
    }

    //y方向的移動和碰撞
    public void moveY() {
        float yMove = direction.y * speed;

        if(yMove > 0.0f) { //往下
            int ty = (int)(position.y + yMove + boundingRect.y + boundingRect.height) / Tile.TILE_HEIGHT;
            //檢查左上角和右上角
            if(!hasCollisionWithTile((int)(position.x + boundingRect.x) / Tile.TILE_WIDTH, ty)
                    && !hasCollisionWithTile((int)(position.x + boundingRect.x + boundingRect.width) / Tile.TILE_WIDTH, ty)) {
                position.y += yMove;
            }else {
                position.y = ty * Tile.TILE_HEIGHT - boundingRect.y - boundingRect.height - 1;
            }
        }else if(yMove < 0.0f) { //往上
            int ty = (int)(position.y + yMove + boundingRect.y) / Tile.TILE_HEIGHT;
            //檢查左下角和右下角
            if(!hasCollisionWithTile((int)(position.x + boundingRect.x) / Tile.TILE_WIDTH, ty)
                    && !hasCollisionWithTile((int)(position.x + boundingRect.x + boundingRect.width) / Tile.TILE_WIDTH, ty)) {
                position.y += yMove;
            }else {
                position.y = ty * Tile.TILE_HEIGHT + Tile.TILE_HEIGHT - boundingRect.y;
            }
        }
    }

    //檢查與tile的碰撞
    private boolean hasCollisionWithTile(int x, int y) {
        return handler.getMap().getTile(x, y).isSolid();
    }

    //檢查與炸彈的碰撞
    protected boolean hasCollisionWithBomb(float xOffset, float yOffset) {
        for(Bomb bomb : handler.getMap().getEntityManager().getBombs()) {
            if(!bomb.isDestroyed() &&
                    bomb.getCollisionRect(0.0f, 0.0f).intersects(getCollisionRect(xOffset, yOffset)))
            {
                return true;
            }
        }
        return false;
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

    public Vector2D getVector() {
        return direction;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setVector(float x, float y){
        direction.x = x;
        direction.y = y;
    }
}
