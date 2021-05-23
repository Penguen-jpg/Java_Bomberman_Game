package Entity.Static;

import Entity.Creature.Player;
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
    //渲染用的bounding box
    protected Rectangle drawRect;

    public Explosion(Handler handler, Bomb bomb) {
        super(handler, bomb.getGridX() * Tile.TILE_WIDTH
                , bomb.getGridY() * Tile.TILE_HEIGHT, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
        this.bomb = bomb;

        //設定爆炸數值
        maxRange = bomb.getPower() * 2 + 1;

        //紀錄引爆的時間
        detonateTime = System.currentTimeMillis();

        //初始化drawRect
        drawRect = new Rectangle(0, 0, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
    }

    //檢查與tile的碰撞
    protected boolean hasCollisionWithTile(int x, int y) {
        return handler.getMap().getTile(x, y).isSolid();
    }

    @Override
    public Rectangle getCollisionRect(float xOffset, float yOffset) {
        //System.out.println((int)(position.x + boundingRect.x + xOffset) + "  " + (int)(position.y + boundingRect.y + yOffset));

        return new Rectangle((int)(position.x + boundingRect.x + xOffset),
                (int)(position.y + boundingRect.y + yOffset), boundingRect.width, boundingRect.height);
    }

    //水平的爆炸
    public static class HorizontalExplosion extends Explosion {
        public HorizontalExplosion(Handler handler, Bomb bomb) {
            super(handler, bomb);

            //設定bounding box
            drawRect.x = 0;
            drawRect.y = 0;
            drawRect.width = Tile.TILE_WIDTH;
            drawRect.height = Tile.TILE_HEIGHT;
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
            //最終爆炸範圍
            int waveRange = 1;

            //算出左半邊爆炸範圍
            for(int n=1;n<=maxRange/2;n++) {
                if(hasCollisionWithEntity((gridX - n) * Tile.TILE_WIDTH)
                        || hasCollisionWithTile(bomb.gridX - n, bomb.gridY)) {
                    break;
                }else {
                    //往左延伸
                    waveRange++;
                    drawRect.x -= Tile.TILE_WIDTH;
                }
            }

            //算出右半邊爆炸範圍
            for(int n=1;n<=maxRange/2;n++) {
                if(hasCollisionWithEntity((gridX + n) * Tile.TILE_WIDTH)
                        || hasCollisionWithTile(bomb.getGridX() + n, bomb.getGridY())) {
                    break;
                }else {
                    //往右延伸
                    waveRange++;
                }
            }

            drawRect.width = Tile.TILE_WIDTH * waveRange;
            graphics.setColor(Color.BLUE);
            graphics.fillRect((int)(position.x + drawRect.x), (int)(position.y + drawRect.y)
                    , drawRect.width, drawRect.height);

            //設定判斷用的bounding box
            boundingRect.x = drawRect.x;
            boundingRect.y = drawRect.y;
            boundingRect.width = drawRect.width;
            boundingRect.height = drawRect.height;

            //歸0
            drawRect.x = 0;
        }

        @Override
        public void onDestroy() {

        }

        //檢查與entity的碰撞
        private boolean hasCollisionWithEntity(int x){
            for(Entity entity : handler.getMap().getEntityManager().getEntities()) {
                if (!entity.isDestroyed()
                        && (entity.getCollisionRect(0.0f, 0.0f).contains(x, position.y))) {
                    /*Rectangle temp = getCollisionRect(xOffset, 0.0f);
                    System.out.println("Rect now:"+"x:"+temp.x+" y:"+temp.y
                            +" w:"+temp.width+" h:"+temp.height);*/
                    //如果該entity可破壞，則不須削減爆炸範圍
                    if (entity.isBreakable()) {
                        //System.out.println("nowX:"+(boundingRect.x+Tile.TILE_WIDTH));
                        entity.setDestroyed(true);
                        return true;
                    } else {
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
            drawRect.x = 0;
            drawRect.y = 0;
            drawRect.width = Tile.TILE_WIDTH;
            drawRect.height = Tile.TILE_HEIGHT;
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
            //最終爆炸範圍
            int waveRange = 1;

            //算出上半邊爆炸範圍
            for(int n=1;n<=maxRange/2;n++) {
                if(hasCollisionWithEntity((gridY - n) * Tile.TILE_HEIGHT)
                        || hasCollisionWithTile(bomb.gridX, bomb.gridY - n)) {
                    break;
                }else {
                    drawRect.y -= Tile.TILE_HEIGHT;
                    waveRange++;
                }
            }

            //爆炸的底部y座標
            for(int n=1;n<=maxRange/2;n++) {
                if(hasCollisionWithEntity((gridY + n) * Tile.TILE_HEIGHT)
                        || hasCollisionWithTile(bomb.getGridX(), bomb.getGridY() + n)) {
                    break;
                }else {
                    waveRange++;
                }
            }

            drawRect.height = Tile.TILE_HEIGHT * waveRange;
            graphics.setColor(Color.BLUE);
            graphics.fillRect((int)(position.x + boundingRect.x), (int)(position.y + boundingRect.y)
                    , boundingRect.width, boundingRect.height);

            //設定判斷用的bounding box
            boundingRect.x = drawRect.x;
            boundingRect.y = drawRect.y;
            boundingRect.width = drawRect.width;
            boundingRect.height = drawRect.height;

            //歸0
            drawRect.y = 0;
        }

        @Override
        public void onDestroy() {

        }

        //檢查與entity的碰撞
        private boolean hasCollisionWithEntity(int y) {
            for(Entity entity : handler.getMap().getEntityManager().getEntities()) {
                if(!entity.isDestroyed()
                        && entity.getCollisionRect(0.0f, 0.0f).contains(position.x, y)) {
                    //如果該entity可破壞，則不須削減爆炸範圍
                    if(entity.isBreakable()) {
                        //System.out.println(entity.getClass());
                        entity.setDestroyed(true);
                        return true;
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
