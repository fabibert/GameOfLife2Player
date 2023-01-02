package game.verifier;

import state.board.GolBoard;
import state.data.GolCell;
import state.GolState;
import state.data.Player;
import state.data.Coordinates;

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
        return cell.isAlive() && !cell.player().get().equals(currentPlayer);
    }

    @Override
    public boolean verifyCellCreation(Coordinates coordinates) {
        GolBoard board = state.getBoard();
        GolCell cell = board.getCell(coordinates);
        return !cell.isAlive();
    }
}
