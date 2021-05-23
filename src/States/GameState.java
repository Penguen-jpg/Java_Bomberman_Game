package States;

import Maps.Map;
import Utility.Handler;

import java.awt.*;

public class GameState extends State {
    private Map map;

    public GameState(Handler handler) {
        super(handler);
        map = new Map(handler, "src/res/maps/map1.txt");
        handler.setMap(map);
    }

    @Override
    public void tick() {
        map.tick();
    }

    @Override
    public void render(Graphics graphics) {
        map.render(graphics);
    }
}
