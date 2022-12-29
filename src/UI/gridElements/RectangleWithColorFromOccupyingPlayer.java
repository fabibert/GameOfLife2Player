package UI.gridElements;

import State.GolCell;
import State.Player;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.List;

public class RectangleWithColorFromOccupyingPlayer{
    private Rectangle rectangle;
    public RectangleWithColorFromOccupyingPlayer(GolCell golCell, List<Player> playersList) {
        setRectangleColorFromGolCell(golCell, playersList);
    }

    private void setRectangleColorFromGolCell(GolCell golCell, List<Player> playersList) {
        if (golCell.isAlive()) {
            setRectangleColorFromPlayer(golCell, playersList);
        }
        else{
            this.rectangle = new Rectangle(40, 40, Color.WHITE);
        }
    }

    private void setRectangleColorFromPlayer(GolCell golCell, List<Player> playersList){
        String cellOccupant = golCell.getPlayer().playerName();
        if(cellOccupant == playersList.get(0).playerName())
            this.rectangle = new Rectangle(40, 40, Color.BLUE);
        else if (cellOccupant == playersList.get(1).playerName())
            this.rectangle = new Rectangle(40, 40, Color.RED);
        else//implement exception
            this.rectangle = new Rectangle(40, 40, Color.WHITE);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
