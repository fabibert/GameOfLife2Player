package UI;

import UI.elements.TextFieldButton;
import UI.elements.TextFieldHBox;
import UI.elements.TextFieldText;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TextFieldGetData extends Application {

    public static final int TEXT_FIELD_WIDTH = 595;
    public static final int TEXT_FIELD_HEIGHT = 150;
    String playerName = null;
    //public static String playerName = "default";

    public void start(Stage stage) {
        TextField textField = new TextField();
        Text text = new TextFieldText();
        TextFieldButton button = new TextFieldButton(textField, text);
        this.playerName = button.playerName;
        HBox box = new TextFieldHBox(textField, new Label("Player Name: "));
        initializeStage(stage, new Group(box, button, text));
        stage.show();
    }

    private void initializeStage(Stage stage, Group root) {
        Scene scene = new Scene(root, TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT, Color.WHITE);
        stage.setTitle("GameOfLife2Player");
        stage.setScene(scene);
    }

//    public void setReturnValue(String playerName){
//        this.playerName = playerName;
//    }

//    public String awaitReturnValue() throws InterruptedException {
//        latch.await();
//
//        return playerName;
//    }

    public static void main(){
        launch();
    }
}
