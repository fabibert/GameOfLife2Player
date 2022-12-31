package Game;

import UI.Coordinates;
import state.GolBoard;
import state.GolCell;
import state.GolState;
import state.Player;

public class VerifierImpl implements Verifier {

    GolState state;

    public VerifierImpl(GolState state){
        this.state = state;
    }

    @Override
    public boolean verifyCellDeletion(Coordinates coordinates) {
        Player currentPlayer = state.getCurrentPlayer();
        GolBoard board = state.getBoard();
        GolCell cell = board.getCell(coordinates);
        return cell.isAlive() && !cell.getPlayer().equals(currentPlayer);
    }

    @Override
    public boolean verifyCellCreation(Coordinates coordinates) {
        GolBoard board = state.getBoard();
        GolCell cell = board.getCell(coordinates);
        return !cell.isAlive();
    }
}
