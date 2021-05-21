package Entity.Creature;

import java.awt.*;
import java.awt.image.BufferedImage;

import Entity.Static.Bomb;
import Graphics.*;
import Input.KeyboardManager;
import Texture.Tile;
import Utility.Handler;

public class Player extends Creature {
    //角色的keyboard manager
    private KeyboardManager keyboardManager;
    //上下左右動畫
    private Animation downAnimation;
    private Animation upAnimation;
    private Animation leftAnimation;
    private Animation rightAnimation;
    //炸彈數值
    private int power;
    private int ammo;
    private int maxAmmo;
    //避免重複偵測到空白鍵按下
    private long coolDownTimer;
    //紀路放下炸彈的bounding box(目的在於剛放下炸彈時不用做碰撞判定)
    private Rectangle bombRect;
    //是否剛丟下炸彈
    private boolean justDrop;

    public Player(Handler handler , float x, float y, KeyboardManager keyboardManager, BufferedImage[][] assets) {
        super(handler, x, y, DEFAULT_CREATURE_WIDTH, DEFAULT_CREATURE_HEIGHT);
        this.keyboardManager = keyboardManager;

        //設定bounding box
        boundingRect.x = 22;
        boundingRect.y = 30;
        boundingRect.width = 19;
        boundingRect.height = 33;

        //設定動畫
        upAnimation = new Animation(500, assets[0]);
        downAnimation = new Animation(500, assets[1]);
        leftAnimation = new Animation(500, assets[2]);
        rightAnimation = new Animation(500, assets[3]);

        //設定炸彈數值
        power = 1;
        maxAmmo = 1;
        ammo = maxAmmo;

        //設定檢查用變數
        coolDownTimer = 0;
        bombRect = new Rectangle(0, 0, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
        justDrop = false;
    }

    @Override
    public void tick() {
        downAnimation.tick();
        upAnimation.tick();
        leftAnimation.tick();
        rightAnimation.tick();
        getInput();
        move();
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(getCurrentAnimationFrame(), (int)position.x, (int)position.y, width, height, null);
        /*graphics.setColor(Color.RED);
        graphics.fillRect((int)(position.x + boundingRect.x), (int)(position.y + boundingRect.y)
                , boundingRect.width, boundingRect.height);*/
    }

    //根據輸入做出對應動作
    private void getInput() {
        direction.x = 0.0f;
        direction.y = 0.0f;

        if(keyboardManager.up) {
            direction.y = -1.0f;
        }
        if(keyboardManager.down) {
            direction.y = 1.0f;
        }
        if(keyboardManager.left) {
            direction.x = -1.0f;
        }
        if(keyboardManager.right) {
            direction.x = 1.0f;
        }
        if(keyboardManager.action) {
            if(ammo > 0 && (System.currentTimeMillis() - coolDownTimer) > 400) {
                coolDownTimer = System.currentTimeMillis();
                dropBomb(power);
            }
        }
    }

    //取得目前的動畫
    public BufferedImage getCurrentAnimationFrame() {
        if(direction.x > 0.0f) {
            return rightAnimation.getCurrentFrame();
        }else if(direction.x < 0.0f) {
            return leftAnimation.getCurrentFrame();
        }else if(direction.y < 0.0f) {
            return upAnimation.getCurrentFrame();
        }else {
            return downAnimation.getCurrentFrame();
        }
    }

    public void dropBomb(int power) {
        Bomb bomb = new Bomb(handler, this, power);
        //如果在可以放置的位置才放
        if(!bomb.checkBombCollision()) {
            bombRect = bomb.getCollisionRect(0.0f, 0.0f);
            justDrop = true;
            handler.getMap().getEntityManager().addBomb(bomb);
            ammo--;
        }else
        {
            System.out.println("Bomb collides with other entities");
        }
    }

    public void restoreAmmo() {
        if(ammo < maxAmmo) {
            ammo++;
        }
    }

    //丟下炸彈後是否離開炸彈
    private boolean leaveBomb() {
        if(getCollisionRect(0.0f, 0.0f).intersects(bombRect) && justDrop) {
            return false;
        }
        justDrop = false;
        return true;
    }

    //檢查與炸彈的碰撞
    @Override
    protected boolean hasCollisionWithBomb(float xOffset, float yOffset) {
        //還沒離開炸彈
        if(!leaveBomb()) {
            return false;
        }

        for(Bomb bomb : handler.getMap().getEntityManager().getBombs()) {
            if(!bomb.isDestroyed() &&
                    bomb.getCollisionRect(0.0f, 0.0f).intersects(getCollisionRect(xOffset, yOffset)))
            {
                System.out.println("Player collides with bomb");
                return true;
            }
        }

        return false;
    }
}
