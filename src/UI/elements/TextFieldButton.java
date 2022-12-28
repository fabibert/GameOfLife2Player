package UI.elements;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class TextFieldButton extends Button {

    public TextFieldButton(TextField textField, Text text) {
        super("Submit");
        this.setTranslateX(250);
        this.setTranslateY(75);
        this.setOnAction(e -> playerInteraction(textField, text));
    }

    private void playerInteraction(TextField textField1, Text text) {
        String playerName = textField1.getText();
        text.setText("Hello "+playerName+". Thank your for playing with us!");
        //setReturnValue(playerName);
    }


}
