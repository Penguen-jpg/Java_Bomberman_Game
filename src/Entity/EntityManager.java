package Entity;

import Entity.Creature.Player;
import Entity.Static.Bomb;
import Entity.Static.Explosion;
import Graphics.AssetManager;
import Utility.Handler;
import Utility.Text;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class EntityManager {
    private Handler handler;
    //玩家1,2
    private Player player1;
    private Player player2;
    //儲存所有entity
    private ArrayList<Entity> entities;
    //儲存所有炸彈(使用CopyOnWriteArrayList才能同時讀取同時加入或刪除)
    private CopyOnWriteArrayList<Bomb> bombs;
    //儲存所有爆炸(使用CopyOnWriteArrayList才能同時讀取同時加入或刪除)
    private CopyOnWriteArrayList<Explosion> explosions;
    //處理渲染順序
    private Comparator<Entity> renderSorter = new Comparator<Entity>() {
        @Override
        public int compare(Entity a, Entity b) {
            //如果a的底部y座標比b的底部y座標小，優先渲染
            if (a.getPosition().y + a.getHeight() < b.getPosition().y + b.getHeight()) {
                return -1;
            } else {
                return 1;
            }
        }
    };

    public EntityManager(Handler handler, Player player1, Player player2) {
        this.handler = handler;
        this.player1 = player1;
        this.player2 = player2;
        entities = new ArrayList<>();
        bombs = new CopyOnWriteArrayList<>();
        explosions = new CopyOnWriteArrayList<>();
        addEntity(player1);
        addEntity(player2);
    }

    public void tick() {
        //走訪器(用iterator才能安全的移除元素)
        Iterator<Entity> it = entities.iterator();

        while (it.hasNext()) {
            Entity entity = it.next();
            entity.tick();
            if (entity.destroyed) {
                entity.onDestroy();
                it.remove();
            }
        }

        //排序(決定渲染順序)
        entities.sort(renderSorter);

        for (Bomb bomb : bombs) {
            bomb.tick();
            if (bomb.destroyed) {
                //CopyOnWriteArrayList不能透過走訪器移除
                bombs.remove(bomb);
            }
        }

        for (Explosion explosion : explosions) {
            explosion.tick();
            if (explosion.destroyed) {
                explosions.remove(explosion);
            }
        }
    }

    public void render(Graphics graphics) {
        try {
            for (Entity entity : entities) {
                entity.render(graphics);
            }

            for (Bomb bomb : bombs) {
                bomb.render(graphics);
            }

            for (Explosion explosion : explosions) {
                explosion.render(graphics);
            }

            if (player1.destroyed) {
                Text.drawText(graphics, "PLAYER 2 WINS!!!", handler.getGame().getWidth() / 2
                        , handler.getGame().getHeight() / 4, true, Color.WHITE, AssetManager.font120);
                Text.drawText(graphics, "PRESS ESC TO BACK TO MENU", handler.getGame().getWidth() / 2
                        , handler.getGame().getHeight() / 2, true, Color.WHITE, AssetManager.font120);
                handler.getGame().setGameOver(true);
            } else if (handler.getEntityManager().getPlayer2().isDestroyed()) {
                Text.drawText(graphics, "PLAYER 1 WINS!!!", handler.getGame().getWidth() / 2
                        , handler.getGame().getHeight() / 4, true, Color.WHITE, AssetManager.font120);
                Text.drawText(graphics, "PRESS ESC TO BACK TO MENU", handler.getGame().getWidth() / 2
                        , handler.getGame().getHeight() / 2, true, Color.WHITE, AssetManager.font120);
                handler.getGame().setGameOver(true);
            }
        } catch (ConcurrentModificationException e) {
            return;
        }
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void addBomb(Bomb bomb) {
        bombs.add(bomb);
    }

    public void addExplosion(Explosion explosion) {
        explosions.add(explosion);
    }

    //摧毀所有entity
    public void destroyAll() {
        entities.clear();
        bombs.clear();
        explosions.clear();
    }

    //getters and setters
    public Handler getHandler() {
        return handler;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public CopyOnWriteArrayList<Bomb> getBombs() {
        return bombs;
    }

    public CopyOnWriteArrayList<Explosion> getExplosions() {
        return explosions;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public void setPlayer1(Player player) {
        player1 = player;
        addEntity(player1);
    }

    public void setPlayer2(Player player) {
        player2 = player;
        addEntity(player2);
    }
}
