package Graphics;

import Input.KeyboardManager;
import Input.MouseManager;
import States.GameState;
import States.MenuState;
import States.State;
import States.StateManager;
import Utility.Handler;
import Utility.Timer;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class Game implements Runnable {
    //視窗
    private Window window;
    //視窗用的變數
    private int width, height;
    private String title;
    //執行續
    private Thread thread;
    private boolean running;
    //渲染用
    private BufferStrategy buffer;
    private Graphics graphics;
    //計時器
    private Timer timer;
    //狀態
    //public State gameState;
    //public State menuState;
    private StateManager stateManager;
    //鍵盤輸入
    private ArrayList<KeyboardManager> keyboardManagers;
    //滑鼠輸入
    private MouseManager mouseManager;
    //Handler
    private Handler handler;

    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        //keyboardManager = new KeyboardManager();
        mouseManager = new MouseManager();
    }

    private void init() {
        window = new Window(title, width, height);
        handler = new Handler(this);
        window.getFrame().addMouseListener(mouseManager);
        window.getFrame().addMouseMotionListener(mouseManager);
        window.getCanvas().addMouseListener(mouseManager);
        window.getCanvas().addMouseMotionListener(mouseManager);
        AssetManager.init();
        timer = new Timer();
        setKeyboardManagers();
        window.getFrame().addKeyListener(keyboardManagers.get(0));
        window.getFrame().addKeyListener(keyboardManagers.get(1));
        stateManager = new StateManager(handler);
        //gameState = new GameState(handler);
        //menuState = new MenuState(handler);
        //State.setState(menuState);
        stateManager.setCurrentState(stateManager.menuState);
    }

    private void tick() {
        if(stateManager.getCurrentState() != null) {
            stateManager.getCurrentState().tick();
        }
    }

    int x = 0;

    private void render() {
        buffer = window.getCanvas().getBufferStrategy();
        if(buffer == null) {
            window.getCanvas().createBufferStrategy(3);
            return;
        }

        //建立Graphics物件(用來畫東西到buffer裡面)
        graphics = buffer.getDrawGraphics();

        //清空畫面
        graphics.clearRect(0, 0, width, height);
        //繪圖開始
        if(stateManager.getCurrentState() != null) {
            stateManager.getCurrentState().render(graphics);
        }
        //繪圖結束
        //顯示到畫面上
        buffer.show();
        //清除graphics內容物
        graphics.dispose();
    }

    @Override
    public void run() {
        init();

        while(running) {
            if(timer.check()) {
                tick();
                render();
                timer.ticks++;
            }
        }

        stop();
    }

    public synchronized void start() {
        if(running)
        {
            return;
        }

        running = true;
        thread = new Thread(this);
        thread.start();//呼叫run()
    }

    public synchronized void stop() {
        if(!running)
        {
            return;
        }

        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //getters/setters
    public KeyboardManager getKeyboardManager(int index) {
        return keyboardManagers.get(index);
    }
    public MouseManager getMouseManager() {
        return mouseManager;
    }

    public StateManager getStateManager() {
        return stateManager;
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

    public void setKeyboardManagers() {
        keyboardManagers = new ArrayList<>();

        KeyboardManager manager1 = new KeyboardManager(handler.getKeys(0));
        KeyboardManager manager2 = new KeyboardManager(handler.getKeys(1));

        keyboardManagers.add(manager1);
        keyboardManagers.add(manager2);
    }
}
