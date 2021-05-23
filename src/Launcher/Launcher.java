package Launcher;

import Graphics.Game;

public class Launcher {
    public static void main(String[] args) {
        Game game = new Game("Test", 832, 640);
        game.start();
    }
}
