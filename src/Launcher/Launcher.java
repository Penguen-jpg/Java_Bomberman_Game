package Launcher;

import Graphics.Game;

public class Launcher {
    public static void main(String[] args) {
        Game game = new Game("Test", 960, 640);
        game.start();
    }
}
