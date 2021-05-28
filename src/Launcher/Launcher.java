package Launcher;

import Graphics.Game;

//遊戲啟動器
public class Launcher {
    public static void main(String[] args) {
        Game game = new Game("Bomberman", 960, 640);
        game.start();
    }
}
