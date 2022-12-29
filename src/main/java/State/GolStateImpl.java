package State;

import java.util.List;

public class GolStateImpl implements GolState{

    private final List<Player> players;
    private int numberOfGenerations = 0;
    private GolBoard board;


    public GolStateImpl(String player1Name, String player2Name, GolBoard board) {
        Player player1 = new Player(player1Name);
        Player player2 = new Player(player2Name);
        players = List.of(player1, player2);
        this.board = board;
    }

    @Override
    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public GolBoard getBoard() {
        return board;
    }

    @Override
    public Player getCurrentPlayer() {
        return players.get(numberOfGenerations % players.size());
    }

    @Override
    public int getNumberOfGenerations() {
        return numberOfGenerations;
    }

    @Override
    public void increaseNumberOfGenerations() {
        numberOfGenerations++;
    }
}
