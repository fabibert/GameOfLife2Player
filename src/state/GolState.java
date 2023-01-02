package state;

import state.board.GolBoard;
import state.data.Player;

import java.util.List;

public interface GolState extends State, Observable {
    List<Player> getPlayers();
    GolBoard getBoard();

    void setBoard(GolBoard board);

    Player getCurrentPlayer();
    int getNumberOfGenerations();
    void increaseNumberOfGenerations();
    boolean checkForWinner();
    Player getLeadingPlayer();

}
