package Input;

import Utility.Handler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public class KeyboardManager implements KeyListener {
    //自訂的按鈕配置
    private HashMap<Integer, Key> keys;
    //對應上下左右
    public boolean up, down, left, right, action;
    private Handler handler;

    public KeyboardManager(Handler handler, HashMap<Integer, Key> keys) {
        this.handler = handler;
        this.keys = keys;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    //按下按鍵
    @Override
    public void keyPressed(KeyEvent e) {
        if (keys.get(e.getKeyCode()) == Key.up) {
            up = true;
        } else if (keys.get(e.getKeyCode()) == Key.down) {
            down = true;
        } else if (keys.get(e.getKeyCode()) == Key.left) {
            left = true;
        } else if (keys.get(e.getKeyCode()) == Key.right) {
            right = true;
        } else if (keys.get(e.getKeyCode()) == Key.action) {
            action = true;
        } else if (keys.get(e.getKeyCode()) == Key.toMenu) {
            if (handler.getGame().isGameOver()) {
                handler.getGame().restart();
            }
        }
    }

    //放開按鍵
    @Override
    public void keyReleased(KeyEvent e) {
        if (keys.get(e.getKeyCode()) == Key.up) {
            up = false;
        } else if (keys.get(e.getKeyCode()) == Key.down) {
            down = false;
        } else if (keys.get(e.getKeyCode()) == Key.left) {
            left = false;
        } else if (keys.get(e.getKeyCode()) == Key.right) {
            right = false;
        } else if (keys.get(e.getKeyCode()) == Key.action) {
            action = false;
        }
    }
}
