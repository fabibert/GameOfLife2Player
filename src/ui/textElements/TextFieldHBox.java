package ui.textElements;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class TextFieldHBox extends HBox {
    public TextFieldHBox(TextField textField, Label label) {
        super(5);
        this.setPadding(new Insets(25, 5 , 5, 50));
        this.getChildren().addAll(label, textField);
    }
}
