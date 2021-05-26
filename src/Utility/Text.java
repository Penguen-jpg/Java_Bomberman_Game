package Utility;

import java.awt.*;

public class Text {
    public static void drawText(Graphics graphics, String text, int x, int y
            , boolean center, Color color, Font font) {
        graphics.setColor(color);
        graphics.setFont(font);

        //如果要置中
        if(center) {
            //取得關於font的資訊
            FontMetrics metrics = graphics.getFontMetrics(font);
            x = x - metrics.stringWidth(text) / 2;
            y = (y - metrics.getHeight() / 2) + metrics.getAscent();
        }

        graphics.drawString(text, x, y);
    }
}
