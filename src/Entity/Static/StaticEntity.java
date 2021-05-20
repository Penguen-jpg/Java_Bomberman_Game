package Entity.Static;

import Entity.Entity;
import Utility.Handler;

public abstract class StaticEntity extends Entity {

    public StaticEntity(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        destroyed = false;
    }
}
