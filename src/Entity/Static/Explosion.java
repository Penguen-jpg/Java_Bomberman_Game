package Entity.Static;

import Entity.Entity;
import Texture.Tile;
import Utility.Handler;
import Graphics.AssetManager;

import java.awt.*;


public abstract class Explosion extends StaticEntity {
    //引爆的時間
    protected long detonateTime;
    //炸彈
    protected Bomb bomb;
    //最大爆炸範圍
    protected int maxRange;

    public Explosion(Handler handler, Bomb bomb) {
        super(handler, bomb.getPosition().x, bomb.getPosition().y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
        this.bomb = bomb;

        //設定爆炸數值
        maxRange = bomb.getPower() * 2 + 1;

        //紀錄引爆的時間
        detonateTime = System.currentTimeMillis();
    }

    //水平的爆炸
    public static class HorizontalExplosion extends Explosion {
        public HorizontalExplosion(Handler handler, Bomb bomb) {
            super(handler, bomb);

            //設定bounding box
            boundingRect.x = (Tile.TILE_WIDTH * bomb.getPower()) * -1;
            boundingRect.y = 0;
            boundingRect.width = 0;
            boundingRect.height = Tile.TILE_HEIGHT;
        }

        @Override
        public void tick() {
            //爆炸持續0.5秒再結束
            if(System.currentTimeMillis() - detonateTime > 500) {
                destroyed = true;
            }
        }

        @Override
        public void render(Graphics graphics) {
            //爆炸的起始x座標
            int startX = (int)(position.x + boundingRect.x);
            //爆炸的頂端y座標
            int upperY = (int)((position.y + boundingRect.y) / Tile.TILE_HEIGHT);
            //爆炸的底端y座標
            int lowerY = (int)((position.y + boundingRect.y + boundingRect.height) / Tile.TILE_HEIGHT);
            //暫存原本boundingRect.x
            int temp = boundingRect.x;
            //最終爆炸範圍
            int waveRange = 0;

            //算出左半邊爆炸範圍
            for(int n=0;n<maxRange/2;n++) {
                //檢查是否碰撞到solid tile和unbreakable entity
                if(hasCollisionWithEntity() || hasCollisionWithTile(startX / Tile.TILE_WIDTH, upperY)
                        || hasCollisionWithTile(startX / Tile.TILE_WIDTH, lowerY)) {
                    startX += Tile.TILE_WIDTH;
                    boundingRect.x += Tile.TILE_WIDTH;
                    //boundingRect.width -= Tile.TILE_WIDTH;
                    //waveRange--;
                }else {
                    waveRange++;
                }
            }

            //爆炸的底部x座標
            int endX = startX;
            //算出右半邊爆炸範圍
            for(int n=maxRange/2;n<maxRange;n++) {
                if(hasCollisionWithTile(endX / Tile.TILE_WIDTH, upperY)
                        || hasCollisionWithTile(endX / Tile.TILE_WIDTH, lowerY)) {
                    break;
                }else {
                    //boundingRect.width += Tile.TILE_WIDTH;
                    waveRange++;
                    endX += Tile.TILE_WIDTH;
                }
            }

            boundingRect.width = Tile.TILE_WIDTH * waveRange;
            graphics.drawImage(AssetManager.unbreakableBox1, startX,
                    (int)position.y, boundingRect.width,
                    Tile.TILE_HEIGHT, null);
            graphics.setColor(Color.BLUE);
            graphics.fillRect(startX, (int)(position.y + boundingRect.y),
                    boundingRect.width, boundingRect.height);
            boundingRect.x = temp;
        }

        //檢查與tile的碰撞
        private boolean hasCollisionWithTile(int x, int y) {
            return handler.getMap().getTile(x, y).isSolid();
        }

        //檢查與entity的碰撞
        private boolean hasCollisionWithEntity() {
            for(Entity entity : handler.getMap().getEntityManager().getEntities()) {
                if(!entity.isDestroyed()
                        && entity.getCollisionRect(0.0f, 0.0f)
                        .intersects(getCollisionRect(0.0f, 0.0f))) {
                    //debug用
                    /*if(entity.equals(bomb.getPlayer())) {
                        continue;
                    }*/
                    //如果該entity可破壞，則不須削減爆炸範圍
                    if(entity.isBreakable()) {
                        System.out.println(entity.getClass());
                        entity.setDestroyed(true);
                        return false;
                    }else
                    {
                        System.out.println("Collide with unbreakable entity");
                        return true;
                    }
                }
            }
            return false;
        }
    }

    //垂直的爆炸
    public static class VerticalExplosion extends Explosion {
        public VerticalExplosion(Handler handler, Bomb bomb) {
            super(handler, bomb);

            //設定bounding box
            boundingRect.x = 0;
            boundingRect.y = (Tile.TILE_HEIGHT * bomb.getPower()) * -1;
            boundingRect.width = Tile.TILE_WIDTH;
            boundingRect.height = 0;
        }


        @Override
        public void tick() {
            //爆炸持續0.5秒再結束
            if(System.currentTimeMillis() - detonateTime > 500) {
                destroyed = true;
            }
        }

        @Override
        public void render(Graphics graphics) {
            //爆炸的起始y座標
            int startY = (int)(position.y + boundingRect.y);
            //爆炸的左邊x座標
            int leftX = (int)((position.x + boundingRect.x) / Tile.TILE_WIDTH);
            //爆炸的右邊x座標
            int rightX = (int)((position.x + boundingRect.x + boundingRect.width) / Tile.TILE_WIDTH);
            //暫存原本boundingRect.y
            int temp = boundingRect.y;
            //最終爆炸範圍
            int waveRange = 0;

            //算出上半邊爆炸範圍
            for(int n=0;n<maxRange/2;n++) {
                //檢查是否碰撞到solid tile和unbreakable entity
                if(hasCollisionWithTile(leftX, startY / Tile.TILE_HEIGHT)
                        || hasCollisionWithTile(rightX, startY / Tile.TILE_HEIGHT)) {
                    startY += Tile.TILE_HEIGHT;
                    boundingRect.y += Tile.TILE_HEIGHT;
                    //boundingRect.width -= Tile.TILE_WIDTH;
                    //waveRange--;
                }else {
                    waveRange++;
                }
            }

            //爆炸的底部y座標
            //startY += Tile.TILE_HEIGHT * 2;
            int endY = startY;
            //算出下半邊爆炸範圍
            for(int n=maxRange/2;n<maxRange;n++) {
                if(hasCollisionWithEntity() || hasCollisionWithTile(leftX, endY / Tile.TILE_HEIGHT)
                        || hasCollisionWithTile(rightX, endY / Tile.TILE_HEIGHT)) {
                    break;
                }else {
                    //boundingRect.width += Tile.TILE_WIDTH;
                    waveRange++;
                    endY += Tile.TILE_HEIGHT;
                }
            }

            boundingRect.height = Tile.TILE_HEIGHT * waveRange;
            graphics.drawImage(AssetManager.unbreakableBox2, (int)position.x,
                    startY, boundingRect.width,
                    boundingRect.height, null);
            graphics.setColor(Color.BLUE);
            graphics.fillRect((int)(position.x + boundingRect.x), startY,
                    boundingRect.width, boundingRect.height);
            boundingRect.y = temp;
        }

        //檢查與tile的碰撞
        private boolean hasCollisionWithTile(int x, int y) {
            return handler.getMap().getTile(x, y).isSolid();
        }

        //檢查與entity的碰撞
        private boolean hasCollisionWithEntity() {
            for(Entity entity : handler.getMap().getEntityManager().getEntities()) {
                if(!entity.isDestroyed()
                        && entity.getCollisionRect(0.0f, 0.0f)
                        .intersects(getCollisionRect(0.0f, 0.0f))) {
                    //debug用
                    /*if(entity.equals(bomb.getPlayer())) {
                        continue;
                    }*/
                    //如果該entity可破壞，則不須削減爆炸範圍
                    if(entity.isBreakable()) {
                        System.out.println(entity.getClass());
                        entity.setDestroyed(true);
                        return false;
                    }else
                    {
                        System.out.println("Collide with unbreakable entity");
                        return true;
                    }
                }
            }
            return false;
        }
    }
}
