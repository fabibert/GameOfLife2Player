package game.factory;

import game.Game;
import game.GolGame;
import state.stateBuilder.GolStateBuilder;
import ui.GolUIImpl;
import javafx.stage.Stage;

public class GameFactoryImpl implements GameFactory {
    @Override
    public Game createGOLGame(Stage stage) {
        return new GolGame(new GolUIImpl(stage), new GolStateBuilder());
    }
}
