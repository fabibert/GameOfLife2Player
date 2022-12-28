package State;

import java.util.List;

public interface GolState extends State {
    List<Player> getPlayers();
    GolBoard getBoard();
    Player getCurrentPlayer();
    int getNumberOfGenerations();
    void increaseNumberOfGenerations();
}
