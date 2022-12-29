package Game;

import State.GolStateBuilder;
import UI.GolUIImpl;
import javafx.stage.Stage;

public class GameFactoryImpl implements GameFactory {
    @Override
    public Game createGOLGame(Stage stage) {
        return new GolGame(new GolUIImpl(stage), new GolStateBuilder());
    }
}
