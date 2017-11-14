package ex2.main;

import ex2.game.Game;

public class Main {
    public static void main(String[] Args) throws Exception
    {
        Game game = new Game("easy");
        game.go();
    }
}
