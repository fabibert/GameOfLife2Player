package game.factory;

import game.Game;
import javafx.stage.Stage;

public interface GameFactory {
    Game createGOLGame(Stage stage);
}
