package Graphics;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageLoader {
    //載入圖片
    public static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(ImageLoader.class.getResource(path));
        }catch(IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
}
