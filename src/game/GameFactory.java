package game;

import javafx.stage.Stage;

public interface GameFactory {
    Game createGOLGame(Stage stage);
}
