package Item;

import Utility.Handler;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class ItemManager {
    private Handler handler;
    private ArrayList<Item> items;

    public ItemManager(Handler handler) {
        this.handler = handler;
        items = new ArrayList<>();
    }

    public void tick() {
        //走訪器
        Iterator<Item> it = items.iterator();

        while (it.hasNext()) {
            Item item = it.next();
            item.tick();
            //被撿起後就移除
            if (item.isPickedUp()) {
                it.remove();
            }
        }
    }

    public void render(Graphics graphics) {
        for (Item item : items) {
            item.render(graphics);
        }
    }

    //加入新的item
    public void addItem(Item item) {
        item.setHandler(handler);
        items.add(item);
    }

    //getters
    public Handler getHandler() {
        return handler;
    }
}
