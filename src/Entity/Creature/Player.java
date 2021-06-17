package Entity.Creature;

import Entity.Static.Bomb;
import Graphics.Animation;
import Input.KeyboardManager;
import Texture.Tile;
import Utility.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Creature {
    //角色的keyboard manager
    private KeyboardManager keyboardManager;
    //上下左右動畫
    private Animation downAnimation;
    private Animation upAnimation;
    private Animation leftAnimation;
    private Animation rightAnimation;
    private Animation idleAnimation;
    //玩家id
    private int id;
    //炸彈數值
    private int power;
    private int ammo;
    private int maxAmmo;
    private boolean penetration;
    //避免重複偵測到空白鍵按下
    private long coolDownTimer;
    //紀路放下炸彈的bounding box(目的在於剛放下炸彈時不用做碰撞判定)
    private Rectangle bombRect;
    //是否剛丟下炸彈
    private boolean justDrop;

    public Player(Handler handler, float x, float y, KeyboardManager keyboardManager, BufferedImage[][] assets, int id) {
        super(handler, x, y, DEFAULT_CREATURE_WIDTH, DEFAULT_CREATURE_HEIGHT);
        this.keyboardManager = keyboardManager;
        this.id = id;

        //設定bounding box
        boundingRect.x = 19;
        boundingRect.y = 30;
        boundingRect.width = 25;
        boundingRect.height = 35;

        //設定動畫
        upAnimation = new Animation(150, assets[0]);
        downAnimation = new Animation(150, assets[1]);
        rightAnimation = new Animation(150, assets[2]);
        leftAnimation = new Animation(150, assets[3]);
        idleAnimation = new Animation(200, assets[4]);

        //初始化
        init(x, y);
    }

    @Override
    public void tick() {
        downAnimation.tick();
        upAnimation.tick();
        leftAnimation.tick();
        rightAnimation.tick();
        idleAnimation.tick();
        getInput();
        checkCollisionWithExplosion();
        move();
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(getCurrentAnimationFrame(), (int) position.x, (int) position.y, width, height, null);

        //畫出碰撞區域
        /*graphics.setColor(Color.RED);
        graphics.fillRect((int)(position.x + boundingRect.x), (int)(position.y + boundingRect.y)
                , boundingRect.width, boundingRect.height);*/
    }

    @Override
    public void onDestroy() {

    }

    public void init(float x, float y) {
        //設定初始位置
        position.x = x;
        position.y = y;

        //設定初始數值
        speed = DEFAULT_SPEED;
        power = 1;
        maxAmmo = 1;
        ammo = maxAmmo;
        penetration = false;

        //設定檢查用變數
        destroyed = false;
        coolDownTimer = 0;
        bombRect = new Rectangle(0, 0, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
        justDrop = false;
    }

    //根據輸入做出對應動作
    private void getInput() {
        velocity.x = 0.0f;
        velocity.y = 0.0f;

        if (keyboardManager.up) {
            velocity.y = -1.0f * speed;
        }
        if (keyboardManager.down) {
            velocity.y = 1.0f * speed;
        }
        if (keyboardManager.left) {
            velocity.x = -1.0f * speed;
        }
        if (keyboardManager.right) {
            velocity.x = 1.0f * speed;
        }
        if (keyboardManager.action) {
            if (ammo > 0 && (System.currentTimeMillis() - coolDownTimer) > 400) {
                coolDownTimer = System.currentTimeMillis();
                dropBomb(power);
            }
        }
    }

    //取得目前的動畫
    public BufferedImage getCurrentAnimationFrame() {
        if (velocity.x > 0.0f) {
            return rightAnimation.getCurrentFrame();
        } else if (velocity.x < 0.0f) {
            return leftAnimation.getCurrentFrame();
        } else if (velocity.y < 0.0f) {
            return upAnimation.getCurrentFrame();
        } else if (velocity.y > 0.0f) {
            return downAnimation.getCurrentFrame();
        } else {
            return idleAnimation.getCurrentFrame();
        }
    }

    //丟下炸彈
    public void dropBomb(int power) {
        Bomb bomb = new Bomb(handler, this, position.x, position.y, power, penetration);
        //如果在可以放置的位置才放
        if (!bomb.checkBombCollision()) {
            //System.out.println("Bomb pos:" + "(" + bomb.getPosition().x + "," + bomb.getPosition().y + ")");
            bombRect = bomb.getCollisionRect(0.0f, 0.0f);
            justDrop = true;
            handler.getEntityManager().addBomb(bomb);
            ammo--;
        } else {
            //System.out.println("Bomb collides with other entities");
        }
    }

    //重新補充彈藥
    public void restoreAmmo() {
        if (ammo < maxAmmo) {
            ammo++;
        }
    }

    //丟下炸彈後是否離開炸彈
    private boolean leaveBomb() {
        if (getCollisionRect(0.0f, 0.0f).intersects(bombRect) && justDrop) {
            return false;
        }
        justDrop = false;
        return true;
    }

    //檢查與炸彈的碰撞
    @Override
    protected boolean hasCollisionWithBomb(float xOffset, float yOffset) {
        //還沒離開炸彈
        if (!leaveBomb()) {
            return false;
        }

        for (Bomb bomb : handler.getEntityManager().getBombs()) {
            if (bomb.getCollisionRect(0.0f, 0.0f).intersects(getCollisionRect(xOffset, yOffset))) {
                //System.out.println("Player collides with bomb");
                return true;
            }
        }
        return false;
    }

    //道具加成效果
    public void powerUp(int amount) {
        power += amount;
    }

    public void speedUp(float amount) {
        speed += amount;
    }

    public void ammoUp(int amount) {
        maxAmmo += amount;
        ammo = maxAmmo;
    }

    public void setPenetration(boolean penetration) {
        this.penetration = penetration;
    }
}
