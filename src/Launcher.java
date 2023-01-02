import game.Game;
import game.factory.GameFactory;
import game.factory.GameFactoryImpl;
import javafx.application.Application;
import javafx.stage.Stage;

public class Launcher extends Application {
    public static void main(String[] args) {
        Launcher.launch();
    }

    @Override
    public void start(Stage stage) {
        GameFactory gameFactory = new GameFactoryImpl();
        Game game = gameFactory.createGOLGame(stage);
        new Thread(game::start).start();
    }
}

