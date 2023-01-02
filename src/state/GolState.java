package state;

import java.util.List;

public interface GolState extends State, Observable {
    List<Player> getPlayers();
    GolBoard getBoard();

    void setBoard(GolBoard board);

    Player getCurrentPlayer();
    int getNumberOfGenerations();
    void increaseNumberOfGenerations();
    public boolean checkForWinner();
    public Player getLeadingPlayer();

}
