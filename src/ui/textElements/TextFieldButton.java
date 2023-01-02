package ui.textElements;

import ui.TextFieldGetData;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class TextFieldButton extends Button {
    public String playerName;

    public TextFieldButton(TextField textField, Text text, TextFieldGetData parent) {
        super("Submit");
        this.setTranslateX(100);
        this.setTranslateY(10);
        this.setOnAction(e -> playerInteraction(textField, text, parent));
    }

    private void playerInteraction(TextField textField, Text text, TextFieldGetData parent) {
        String playerName = textField.getText();
        text.setText("Hello " + playerName + ". Thank your for playing with us!");
        parent.setReturnValuePlayerName(playerName);
    }


}
