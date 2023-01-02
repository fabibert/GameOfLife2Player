package ui.gridElements;

import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

public class StackPaneCell extends StackPane {
    public StackPaneCell(Rectangle rectangle){
        super();
        this.getChildren().add(rectangle);
        this.getStyleClass().add("cell");
    }
}
