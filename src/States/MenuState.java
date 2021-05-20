package States;

import java.awt.*;
import Utility.Handler;

public class MenuState extends State {
    public MenuState(Handler handler) {
        super(handler);
    }

    @Override
    public void tick() {
        if(handler.getMouseManager().isLeftPressed() && handler.getMouseManager().isRightPressed()) {
            handler.getGame().getStateManager().setCurrentState(handler.getGame().getStateManager().gameState);
        }
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.BLUE);
        graphics.fillRect(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY(), 8, 8);
    }
}
