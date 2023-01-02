package ui.textElements;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class TextFieldText extends Text {
    public TextFieldText() {
        super("");
        Font font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10);
        this.setFont(font);
        this.setTranslateX(15);
        this.setTranslateY(125);
        this.setFill(Color.BLACK);
        this.maxWidth(580);
        this.setWrappingWidth(580);
    }
}
