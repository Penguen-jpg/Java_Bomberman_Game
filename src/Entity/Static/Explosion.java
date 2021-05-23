package Entity.Static;

import Entity.Entity;
import Texture.Tile;
import Utility.Handler;

import java.awt.*;


public abstract class Explosion extends StaticEntity {
    //引爆的時間
    protected long detonateTime;
    //炸彈
    protected Bomb bomb;

    public Explosion(Handler handler, Bomb bomb) {
        super(handler, bomb.getGridX() * Tile.TILE_WIDTH
                , bomb.getGridY() * Tile.TILE_HEIGHT, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
        this.bomb = bomb;

        //紀錄引爆的時間
        detonateTime = System.currentTimeMillis();
    }

    //水平的爆炸
    public static class HorizontalExplosion extends Explosion {
        public HorizontalExplosion(Handler handler, Bomb bomb) {
            super(handler, bomb);

            //爆炸的上界和下界
            float leftX = checkHorizontalCollision(Tile.TILE_WIDTH * -1);
            float rightX = checkHorizontalCollision(Tile.TILE_WIDTH);

            //設定bounding box
            boundingRect.x = (int) leftX;
            boundingRect.y = (int) position.y;
            boundingRect.width = (int) (rightX - leftX) + Tile.TILE_WIDTH;//多加的是用來補上中間那格
            boundingRect.height = Tile.TILE_HEIGHT;
        }

        @Override
        public void tick() {
            //爆炸持續0.5秒再結束
            if (System.currentTimeMillis() - detonateTime > 500) {
                destroyed = true;
            }
        }

        @Override
        public void render(Graphics graphics) {
            graphics.setColor(Color.BLUE);
            graphics.fillRect(boundingRect.x, boundingRect.y
                    , boundingRect.width, boundingRect.height);
        }

        @Override
        public void onDestroy() {

        }

        //檢查水平方向的爆炸碰撞
        private float checkHorizontalCollision(int xOffset) {
            //爆炸的邊界(從起始點開始算)，用來檢查碰撞
            float bound = position.x;

            outer:
            for (int n = 1; n <= bomb.getPower(); n++) {
                bound += xOffset;

                //檢查與tile的碰撞
                if (handler.getMap().isSolidTile((int) (bound / Tile.TILE_WIDTH), gridY)) {
                    bound -= xOffset;
                    break;
                }

                //檢查與entity的碰撞
                for (Entity entity : handler.getEntityManager().getEntities()) {
                    //如果碰到的entity的bounding box包含爆炸會觸碰到的位置
                    if (entity.getCollisionRect(0.0f, 0.0f).contains(bound, position.y)) {
                        //只檢查unbreakableEntity，否則會有bug
                        if (!entity.isBreakable()) {
                            bound -= xOffset;
                        }

                        //如果沒有穿透效果，在碰到第一個箱子時爆炸就會中斷
                        if (!bomb.canPierce()) {
                            break outer;
                        }
                    }
                }
            }

            return bound;
        }
    }


    //垂直的爆炸
    public static class VerticalExplosion extends Explosion {
        public VerticalExplosion(Handler handler, Bomb bomb) {
            super(handler, bomb);

            //爆炸的上界和下界
            float upperY = checkVerticalCollision(Tile.TILE_HEIGHT * -1);
            float lowerY = checkVerticalCollision(Tile.TILE_HEIGHT);

            //設定bounding box
            boundingRect.x = (int) position.x;
            boundingRect.y = (int) upperY;
            boundingRect.width = Tile.TILE_WIDTH;
            boundingRect.height = (int) (lowerY - upperY) + Tile.TILE_HEIGHT;//多加一個是為了補上中間的那格

            System.out.println(boundingRect);
        }


        @Override
        public void tick() {
            //爆炸持續0.5秒再結束
            if (System.currentTimeMillis() - detonateTime > 500) {
                destroyed = true;
            }
        }

        @Override
        public void render(Graphics graphics) {
            graphics.setColor(Color.BLUE);
            graphics.fillRect(boundingRect.x, boundingRect.y
                    , boundingRect.width, boundingRect.height);
        }

        @Override
        public void onDestroy() {

        }

        private float checkVerticalCollision(int yOffset) {
            //爆炸的邊界(從起始點開始算)，用來檢查碰撞
            float bound = position.y;

            outer:
            for (int n = 1; n <= bomb.getPower(); n++) {
                bound += yOffset;

                //檢查與tile的碰撞
                if (handler.getMap().isSolidTile(gridX, (int) (bound / Tile.TILE_HEIGHT))) {
                    bound -= yOffset;
                    break;
                }

                //檢查與entity的碰撞
                for (Entity entity : handler.getEntityManager().getEntities()) {
                    //如果碰到的entity的bounding box包含爆炸會觸碰到的位置
                    if (entity.getCollisionRect(0.0f, 0.0f).contains(position.x, bound)) {
                        if (!entity.isBreakable()) {
                            bound -= yOffset;
                        }

                        if (!bomb.canPierce()) {
                            break outer;
                        }
                    }
                }
            }
            return bound;
        }
    }
}
