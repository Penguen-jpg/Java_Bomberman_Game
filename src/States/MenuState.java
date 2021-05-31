package States;

import Graphics.AssetManager;
import UI.ClickListener;
import UI.UIImageButton;
import UI.UIManager;
import Utility.Handler;

import java.awt.*;

public class MenuState extends State {
    private UIManager uiManager;

    public MenuState(Handler handler) {
        super(handler);

        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);

        //開始按鈕
        uiManager.addObject(new UIImageButton(60, 450, 128, 64, AssetManager.startButton
                , new ClickListener() {
            @Override
            public void onClick() {
                handler.getMouseManager().setUIManager(null);
                handler.getStateManager().setCurrentState(handler.getStateManager().gameState);
            }
        }));

        //結束按鈕
        uiManager.addObject(new UIImageButton(230, 450, 128, 64, AssetManager.exitButton
                , new ClickListener() {
            @Override
            public void onClick() {
                handler.getMouseManager().setUIManager(null);
                System.exit(0);
            }
        }));
    }

    @Override
    public void tick() {
        uiManager.tick();
    }

    @Override
    public void render(Graphics graphics) {
        //先畫出封面圖片
        graphics.drawImage(AssetManager.menu, 0, 0, handler.getWidth(), handler.getHeight(), null);
        uiManager.render(graphics);
    }

    @Override
    public void init() {
        handler.getMouseManager().setUIManager(uiManager);
    }
}
