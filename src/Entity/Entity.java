package Entity;

import Entity.Static.Explosion;
import Utility.Handler;
import Utility.Position;

import java.awt.*;

public abstract class Entity {
    //位置
    protected Position position;
    //寬 高
    protected int width, height;
    protected Handler handler;
    //bounding box
    protected Rectangle boundingRect;
    //是否被破壞
    protected boolean destroyed;

    public Entity(Handler handler, float x, float y, int width, int height) {
        this.handler = handler;
        position = new Position(x, y);
        this.width = width;
        this.height = height;
        boundingRect = new Rectangle(0, 0, width, height);
    }

    public abstract void tick();
    public abstract void render(Graphics graphics);

    //取得目前的bounding box
    public Rectangle getCollisionRect(float xOffset, float yOffset) {
        return new Rectangle((int)(position.x + boundingRect.x + xOffset),
                (int)(position.y + boundingRect.y + yOffset), boundingRect.width, boundingRect.height);
    }

    //檢查是否和其他Entity產生碰撞
    public boolean checkEntityCollision(float xOffset, float yOffset) {
        //檢查entity之間的碰撞
        for(Entity entity : handler.getMap().getEntityManager().getEntities()) {
            //不檢查自己
            if(entity.destroyed || entity.equals(this)) {
                continue;
            }

            //檢查別人是否跟自己產生碰撞
            if(entity.getCollisionRect(0.0f, 0.0f).intersects(getCollisionRect(xOffset, yOffset))) {
                return true;
            }
        }
        return false;
    }

    //被破壞時做的動作
    public abstract void onDestroy();

    //是否可被破壞
    public boolean isBreakable() {
        return false;
    }

    //是否被破壞
    public boolean isDestroyed() {
        return destroyed;
    }

    //getters/setters
    public Position getPosition() {
        return position;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setPosition(float x, float y) {
        position.x = x;
        position.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }
}
