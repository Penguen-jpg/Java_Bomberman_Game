package Item;

import Entity.Creature.Player;
import Utility.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;
import Graphics.AssetManager;

public class Item {
    //儲存所有種類的item
    public static Item[] items = new Item[30];
    //目前有的item種類
    public static Item powerUpItem = new Item(AssetManager.unbreakableBox2, "Power_up", 0);
    public static Item speedUpItem = new Item(AssetManager.unbreakableBox1, "Speed_up", 1);
    public static Item ammoUpItem = new Item(AssetManager.bottomFloor, "Ammo_up", 2);

    //道具的寬 高
    public static final int ITEM_WIDTH = 32;
    public static final int ITEM_HEIGHT = 32;
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
        pickedUp = false;

        boundingRect = new Rectangle(x, y, ITEM_WIDTH, ITEM_HEIGHT);

        items[type] = this;
    }

    public void tick() {
        if(handler.getEntityManager().getPlayer1().getCollisionRect(0.0f, 0.0f).intersects(boundingRect)) {
            itemEffect(handler.getEntityManager().getPlayer1());
            System.out.println("Player1 got item");
            pickedUp = true;
        }else if(handler.getEntityManager().getPlayer2().getCollisionRect(0.0f, 0.0f).intersects(boundingRect)) {
            itemEffect(handler.getEntityManager().getPlayer2());
            System.out.println("Player2 got item");
            pickedUp = true;
        }
    }

    public void render(Graphics graphics) {
        if(handler == null) {
            return;
        }
        render(graphics, x, y);
    }

    private void render(Graphics graphics, int x, int y) {
        graphics.drawImage(texture, x, y, ITEM_WIDTH, ITEM_HEIGHT, null);
    }

    public Item createItem(int x, int y) {
        Item item = new Item(texture, name, type);
        item.setPosition(x, y);
        return item;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        boundingRect.x = x;
        boundingRect.y = y;
    }

    public boolean isPickedUp() {
        return pickedUp;
    }

    public void itemEffect(Player player) {
        if(type == 0) {
            player.powerUp(1);
        }else if(type == 1)
        {
            player.speedUp(0.5f);
        }else if(type == 2) {
            player.ammoUp(1);
        }
    }

    //getters/setters
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
