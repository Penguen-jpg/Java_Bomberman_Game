package UI;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UIImageButton extends UIObject {
    private BufferedImage images;
    private ClickListener listener;

    //BufferedImage should be BufferImage[]
    public UIImageButton(float x, float y, int width, int height, BufferedImage images, ClickListener clicker) {
        super(x, y, width, height);
        this.images = images;
        this.listener = clicker;
    }

    public void tick() {

    }

    public void render(Graphics graphics) {
        if (hovering) {
            graphics.drawImage(images, (int) position.x, (int) position.y, width, height, null);
        }
        else {
            graphics.drawImage(images, (int) position.x, (int) position.y, width, height, null);
        }
    }

    public void onClick() {
        listener.onClick();
    }
}