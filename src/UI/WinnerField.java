package UI;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import state.Player;

public class WinnerField {

    public static final int TEXT_FIELD_WIDTH = 595;
    public static final int TEXT_FIELD_HEIGHT = 150;


    public WinnerField(){

    }

    public void start(Stage stage, Player player) {
        stage.setTitle("GameOfLife2Players");
        Insets value = new Insets(50, 10, 50, 10);
        Label winnerText = new Label("Congratulations to the game of life winner:");
        winnerText.setWrapText(true);
        winnerText.setPadding(value);
        Label playerDisplay = new Label(player.playerName());
        playerDisplay.setWrapText(true);
        playerDisplay.setPadding(value);

        VBox box = new VBox(2);
        box.getChildren().addAll(winnerText, playerDisplay);

        BorderPane border = new BorderPane();
        border.setCenter(box);

        Scene scene = new Scene(border, 300, 100);
        stage.setScene(scene);
        stage.show();
    }
}

