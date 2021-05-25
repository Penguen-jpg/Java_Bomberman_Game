package UI;

import Utility.Position;

import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class UIObject {
    protected Position position;
    protected int width, height;
    //游標是否在物件上
    protected boolean hovering = false;
    protected Rectangle boundingRect;

    public UIObject(float x, float y, int width, int height) {
        position = new Position(x, y);
        this.width = width;
        this.height = height;
        boundingRect = new Rectangle((int) x, (int) y, width, height);
    }

    public abstract void tick();

    public abstract void render(Graphics graphics);

    public abstract void onClick();

    //滑鼠移動
    public void onMouseMove(MouseEvent e) {
        if (boundingRect.contains(e.getX(), e.getY())) {
            hovering = true;
        }
        else {
            hovering = false;
        }
    }

    //放開滑鼠按鍵
    public void onMouseRelease(MouseEvent e) {
        if (hovering) {
            onClick();
        }
    }

    public Position getPosition() {
        return position;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}