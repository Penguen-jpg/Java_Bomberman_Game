package Entity;

import Entity.Creature.Player;
import Entity.Static.Bomb;
import Entity.Static.Explosion;
import Utility.Handler;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class EntityManager {
    private Handler handler;
    //玩家1,2
    private Player player1;
    private Player player2;
    //儲存所有entity
    private ArrayList<Entity> entities;
    //儲存所有炸彈
    private ArrayList<Bomb> bombs;
    //儲存所有爆炸
    private ArrayList<Explosion> explosions;
    //處理渲染順序
    private Comparator<Entity> renderSorter = new Comparator<Entity>() {
        @Override
        public int compare(Entity a, Entity b) {
            //如果a的底部y座標比b的底部y座標小，優先渲染
            if(a.getPosition().y + a.getHeight() < b.getPosition().y + b.getHeight()) {
                return -1;
            }else {
                return 1;
            }
        }
    };

    public EntityManager(Handler handler, Player player1, Player player2) {
        this.handler = handler;
        this.player1 = player1;
        this.player2 = player2;
        entities = new ArrayList<>();
        bombs = new ArrayList<>();
        explosions = new ArrayList<>();
        addEntity(player1);
        addEntity(player2);
    }

    public void tick() {
        for(Entity entity : entities) {
            if(!entity.destroyed) {
                entity.tick();
            }
        }
        //排序
        entities.sort(renderSorter);

        for(Bomb bomb : bombs) {
            if(!bomb.destroyed) {
                bomb.tick();
            }
        }

        for(Explosion explosion : explosions) {
            if(!explosion.destroyed) {
                explosion.tick();
            }
        }
    }

    public void render(Graphics graphics) {
        for(Entity entity : entities) {
            if(!entity.destroyed) {
                entity.render(graphics);
            }
        }

        for(Bomb bomb : bombs) {
            if(!bomb.destroyed) {
                bomb.render(graphics);
            }
        }

        for(Explosion explosion : explosions) {
            if(!explosion.destroyed) {
                explosion.render(graphics);
            }
        }
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void addBomb(Bomb bomb) { bombs.add(bomb); }

    public void addExplosion(Explosion explosion) { explosions.add(explosion); }

    public Handler getHandler() {
        return handler;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() { return player2; }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public ArrayList<Bomb> getBombs() {
        return bombs;
    }

    public ArrayList<Explosion> getExplosions() { return explosions; }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public void setPlayer1(Player player) {
        this.player1 = player;
    }

    public void setPlayer2(Player player) {
        this.player2 = player;
    }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }
}
