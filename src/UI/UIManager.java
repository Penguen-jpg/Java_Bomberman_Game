package UI;

import Utility.Handler;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class UIManager {
    private Handler handler;
    private ArrayList<UIObject> uiObjects;

    public UIManager(Handler handler) {
        this.handler = handler;
        uiObjects = new ArrayList<>();
    }

    public void tick() {
        for (UIObject object : uiObjects)
            object.tick();
    }

    public void render(Graphics graphics) {
        for (UIObject object : uiObjects)
            object.render(graphics);
    }
    
    public void onMouseMove(MouseEvent e) {
        for (UIObject object : uiObjects)
            object.onMouseMove(e);
    }

    public void onMouseRelease(MouseEvent e) {
        for (UIObject object : uiObjects)
            object.onMouseRelease(e);
    }

    public void addObject(UIObject object) {
        uiObjects.add(object);
    }

    public void removeObject(UIObject object) {
        uiObjects.remove(object);
    }
}