package ui;

import ui.textElements.TextFieldButton;
import ui.textElements.TextFieldHBox;
import ui.textElements.TextFieldText;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.concurrent.CountDownLatch;

public class TextFieldGetData {

    public static final int TEXT_FIELD_WIDTH = 595;
    public static final int TEXT_FIELD_HEIGHT = 150;
    private String playerName;
    private Color color;
    private final CountDownLatch countDownLatch;
    private final String[] allowedColors;

    public TextFieldGetData(String[] allowedColors){
        countDownLatch = new CountDownLatch(2);
        this.allowedColors = allowedColors;
    }

    public void start(Stage stage) {
        TextField textField = new TextField();
        Text text = new TextFieldText();
        Button button = new TextFieldButton(textField, text, this);
        ComboBox<String> comboBox = new ComboBox<>(FXCollections.observableArrayList(allowedColors));
        comboBox.setOnAction(e -> {
            String colorName = comboBox.getValue();
            setReturnValuePlayerColor(colorName.equals("RED") ? Color.RED : Color.BLUE);
        });
        comboBox.setTranslateX(100);
        comboBox.setTranslateY(5);

        HBox box = new TextFieldHBox(textField, new Label("Player Name: "));
        initializeStage(stage, new VBox(box, comboBox, button, text));
        stage.show();
    }

    private void initializeStage(Stage stage, VBox root) {
        Scene scene = new Scene(root, TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT, Color.WHITE);
        stage.setTitle("GameOfLife2Player");
        stage.setScene(scene);
    }

    public void setReturnValuePlayerName(String playerName){
        this.playerName = playerName;
        countDownLatch.countDown();
    }

    public void setReturnValuePlayerColor(Color color){
        this.color = color;
        countDownLatch.countDown();
    }

    public UIPlayerInformation awaitReturnValue(){
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new UIPlayerInformation(playerName, color);
    }
}
