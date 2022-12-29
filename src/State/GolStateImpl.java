package State;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GolStateImpl implements GolState{

    private final List<Player> players;

    private List<Observer> observers;
    private int numberOfGenerations = 0;
    private GolBoard board;



    public GolStateImpl(String player1Name, String player2Name, GolBoard board) {
        Player player1 = new Player(player1Name);
        Player player2 = new Player(player2Name);
        players = List.of(player1, player2);
        this.board = board;
        observers = new ArrayList<Observer>();
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
    public void setBoard(GolBoard board) {
        this.board = board;
        updateObservers(new EncapsulatedGolState(Map.of(), board,1, getCurrentPlayer()));
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



    @Override
    public void updateObservers(EncapsulatedGolState state) {
        for(Observer observer: observers){
            observer.recieveGolStateEncapsulated(state);
        }
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);

    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }
}
