package UI.gridElements;

import state.GolCell;
import state.Player;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.List;

public class RectangleWithColorFromOccupyingPlayer extends Rectangle{
    public RectangleWithColorFromOccupyingPlayer(GolCell golCell, List<Player> playersList) {
        super(40, 40, Color.WHITE);
        setRectangleColorFromGolCell(golCell, playersList);
    }

    private void setRectangleColorFromGolCell(GolCell golCell, List<Player> playersList) {
        if (golCell.isAlive()) {
            setRectangleColorFromPlayer(golCell, playersList);
        }
    }

    private void setRectangleColorFromPlayer(GolCell golCell, List<Player> playersList){
        String cellOccupant = golCell.getPlayer().playerName();
        if(cellOccupant.equals(playersList.get(0).playerName()))
            this.setFill(Color.BLUE);
        else if (cellOccupant.equals(playersList.get(1).playerName()))
            this.setFill(Color.RED);
        else
            throw new RuntimeException("Unknown player found in cell");
    }
}
