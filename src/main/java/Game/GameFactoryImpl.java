package Game;

import State.GolState;
import State.GolStateBuilder;
import State.StateBuilder;
import UI.GolUI;
import UI.GolUIImpl;
import javafx.stage.Stage;

public class GameFactoryImpl implements GameFactory {
    @Override
    public Game createGOLGame(Stage stage) {
        return new GolGame(new GolUIImpl(stage), new GolStateBuilder());
    }
}
