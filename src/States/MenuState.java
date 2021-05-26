package States;

import UI.ClickListener;
import UI.UIImageButton;
import UI.UIManager;
import Utility.Handler;
import Graphics.AssetManager;

import java.awt.*;

public class MenuState extends State {
    private UIManager uiManager;

    public MenuState(Handler handler) {
        super(handler);

        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);
        //新增按鈕
        uiManager.addObject(new UIImageButton(200, 200, 128, 64, AssetManager.wall
                , new ClickListener() {
            @Override
            public void onClick() {
                handler.getMouseManager().setUIManager(null);
                handler.getStateManager().setCurrentState(handler.getStateManager().gameState);
            }
        }));
    }

    @Override
    public void tick() {
        /*if (handler.getMouseManager().isLeftPressed() && handler.getMouseManager().isRightPressed()) {
            handler.getGame().getStateManager().setCurrentState(handler.getStateManager().gameState);
        }*/
        uiManager.tick();
    }

    @Override
    public void render(Graphics graphics) {
        /*graphics.setColor(Color.BLUE);
        graphics.fillRect(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY(), 8, 8);*/
        uiManager.render(graphics);
    }

    @Override
    public void init() {
        handler.getMouseManager().setUIManager(uiManager);
    }
}
