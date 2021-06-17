package Item;

import Entity.Creature.Player;
import Entity.Static.Explosion;
import Graphics.AssetManager;
import Utility.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Item {
    //儲存所有種類的item
    public static Item[] items = new Item[30];
    //目前有的item種類
    public static Item powerUpItem = new Item(AssetManager.powerUp, "Power_up", 0);
    public static Item speedUpItem = new Item(AssetManager.speedUp, "Speed_up", 1);
    public static Item ammoUpItem = new Item(AssetManager.ammoUp, "Ammo_up", 2);
    public static Item pierceItem = new Item(AssetManager.pierce, "Pierce", 3);

    //道具的寬 高
    public static final int ITEM_WIDTH = 64;
    public static final int ITEM_HEIGHT = 64;
    //記錄剛生成的時間(避免剛生成就被炸彈炸掉)
    private long spawnTime;
    //是否被撿起來
    private boolean pickedUp;
    protected Handler handler;
    protected BufferedImage texture;
    protected String name;
    protected final int type;
    protected int x, y;
    //bounding box
    protected Rectangle boundingRect;

    public Item(BufferedImage texture, String name, int type) {
        this.texture = texture;
        this.name = name;
        this.type = type;
        spawnTime = System.currentTimeMillis();
        pickedUp = false;
        boundingRect = new Rectangle(x, y, ITEM_WIDTH, ITEM_HEIGHT);
        //將items[type]指定給呼叫method的item
        items[type] = this;
    }

    public void tick() {
        if (System.currentTimeMillis() - spawnTime > 500) {
            if (checkCollisionWithExplosion()) {
                return;
            }
        }
        if (handler.getEntityManager().getPlayer1().getCollisionRect(0.0f, 0.0f).intersects(boundingRect)) {
            itemEffect(handler.getEntityManager().getPlayer1());
            pickedUp = true;
        } else if (handler.getEntityManager().getPlayer2().getCollisionRect(0.0f, 0.0f).intersects(boundingRect)) {
            itemEffect(handler.getEntityManager().getPlayer2());
            pickedUp = true;
        }
    }

    //讓item manager處理的渲染
    public void render(Graphics graphics) {
        if (handler == null) {
            return;
        }
        render(graphics, x, y);
    }

    //在特定位置渲染
    private void render(Graphics graphics, int x, int y) {
        graphics.drawImage(texture, x, y, ITEM_WIDTH, ITEM_HEIGHT, null);
    }

    //新增item在指定地點
    public Item createItem(int x, int y) {
        Item item = new Item(texture, name, type);
        item.setPosition(x, y);
        return item;
    }

    //設定生成地點
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        boundingRect.x = x;
        boundingRect.y = y;
    }

    //是否被撿起來
    public boolean isPickedUp() {
        return pickedUp;
    }

    //對應的道具效果
    public void itemEffect(Player player) {
        if (type == 0) {
            player.powerUp(1);
        } else if (type == 1) {
            player.speedUp(0.05f);
        } else if (type == 2) {
            player.ammoUp(1);
        } else if (type == 3) {
            player.setPenetration(true);
        }
    }

    //檢查與爆炸的碰撞
    private boolean checkCollisionWithExplosion() {
        for (Explosion explosion : handler.getEntityManager().getExplosions()) {
            if (boundingRect.intersects(explosion.getBoundingRect())) {
                pickedUp = true;
                return true;
            }
        }
        return false;
    }

    //getters and setters
    public Handler getHandler() {
        return handler;
    }

    public BufferedImage getTexture() {
        return texture;
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public void setTexture(BufferedImage texture) {
        this.texture = texture;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setPickedUp(boolean pickedUp) {
        this.pickedUp = pickedUp;
    }
}
