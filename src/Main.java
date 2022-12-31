import Game.Game;
import Game.GameFactory;
import Game.GameFactoryImpl;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        Main.launch();
    }

    @Override
    public void start(Stage stage) {
        GameFactory gameFactory = new GameFactoryImpl();
        Game game = gameFactory.createGOLGame(stage);
        new Thread(game::start).start();
    }
}

class MyLauncher {
    public static void main(String[] args) {
        Main.main(args);
    }
}