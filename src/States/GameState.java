package States;

import java.awt.*;

import Graphics.AssetManager;
import Graphics.Game;
import Maps.Map;
import Texture.Tile;
import Utility.Handler;

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
