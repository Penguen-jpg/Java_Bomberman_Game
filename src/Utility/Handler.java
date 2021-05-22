package Utility;

import Entity.EntityManager;
import Graphics.Game;
import Input.Key;
import Input.KeyboardManager;
import Input.MouseManager;
import Maps.Map;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

public class Handler {
    private Game game;
    private Map map;
    private ArrayList<HashMap<Integer, Key>> keys;

    public Handler(Game game) {
        this.game = game;
        setKeys();
    }

    //getters/setters
    public KeyboardManager getKeyboardManager(int index) {
        return game.getKeyboardManager(index);
    }

    public MouseManager getMouseManager() {
        return game.getMouseManager();
    }

    public EntityManager getEntityManager() {
        return map.getEntityManager();
    }

    public int getWidth() {
        return game.getWidth();
    }

    public int getHeight() {
        return game.getHeight();
    }

    public int getMapWidth() {
        return map.getWidth();
    }

    public int getMapHeight() {
        return map.getHeight();
    }

    public Game getGame() {
        return game;
    }

    public Map getMap() {
        return map;
    }

    public HashMap<Integer, Key> getKeys(int index) {
        return keys.get(index);
    }

    public void setGame() {
        this.game = game;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public void setKeys() {
        keys = new ArrayList<>();

        HashMap<Integer, Key> keys1, keys2;
        keys1 = new HashMap<>();
        keys2 = new HashMap<>();
        //玩家1的操作方法
        keys1.put(KeyEvent.VK_UP, Key.up);
        keys1.put(KeyEvent.VK_DOWN, Key.down);
        keys1.put(KeyEvent.VK_LEFT, Key.left);
        keys1.put(KeyEvent.VK_RIGHT, Key.right);
        keys1.put(KeyEvent.VK_SPACE, Key.action);

        //玩家2的操作方法
        keys2.put(KeyEvent.VK_W, Key.up);
        keys2.put(KeyEvent.VK_S, Key.down);
        keys2.put(KeyEvent.VK_A, Key.left);
        keys2.put(KeyEvent.VK_D, Key.right);
        keys2.put(KeyEvent.VK_ENTER, Key.action);

        keys.add(keys1);
        keys.add(keys2);
    }
}
