package Graphics;

import java.awt.image.BufferedImage;

public class Animation {
    //每張圖片要播多久
    private int speed;
    //目前圖片編號
    private int index;
    //上次更新時間
    private long lastTime;
    //播放下一張圖片前的時間
    private long timer;
    //圖片
    private BufferedImage[] frames;

    public Animation(int speed, BufferedImage[] frames) {
        this.speed = speed;
        this.frames = frames;
        index = 0;
        timer = 0;
        lastTime = System.currentTimeMillis();
    }

    public void tick() {
        //更新時間
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        //檢查是否要播放下一張圖片
        if (timer > speed) {
            index++;
            timer = 0;
            if (index >= frames.length) {
                index = 0;
            }
        }
    }

    public BufferedImage getCurrentFrame() {
        return frames[index];
    }
}
