package UI;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UIImageButton extends UIObject {
    //按鈕圖片
    private BufferedImage[] images;
    //listener
    private ClickListener listener;

    public UIImageButton(float x, float y, int width, int height, BufferedImage[] images, ClickListener clicker) {
        super(x, y, width, height);
        this.images = images;
        this.listener = clicker;
    }

    public void tick() {

    }

    public void render(Graphics graphics) {
        //游標停在按鈕上
        if (hovering) {
            graphics.drawImage(images[1], (int) position.x, (int) position.y, width, height, null);
        } else {
            graphics.drawImage(images[0], (int) position.x, (int) position.y, width, height, null);
        }
    }

    public void onClick() {
        listener.onClick();
    }
}