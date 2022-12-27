package Game;

import State.GolState;
import State.StateBuilder;
import UI.GolUI;

public class GameFactoryImpl implements GameFactory {
    @Override
    public Game createGOLGame() {
        Game golGame = new GolGame(new GolUI(), new StateBuilder<GolState>());
        golGame.start();
        return golGame;
    }
}
