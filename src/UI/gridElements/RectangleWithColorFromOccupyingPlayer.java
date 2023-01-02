package UI.gridElements;

import javafx.scene.paint.Paint;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Optional;

public class RectangleWithColorFromOccupyingPlayer extends Rectangle{
    public RectangleWithColorFromOccupyingPlayer(Optional<Color> color) {
        super(40, 40, Color.WHITE);
        color.ifPresent(this::setFill);
    }
}
