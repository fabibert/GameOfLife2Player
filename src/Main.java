import UI.GolUI;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        GolUI ui = new GolUI(stage);
    }
}