package state;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GolStateImpl implements GolState{

    private final List<Player> players;

    private List<Observer> observers;
    private int numberOfGenerations = 0;
    //TODO: here use interface
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
        //TODO: safe copy board and pass back copy
        return new GolBoardImpl(board.getArray());
    }

    @Override
    public void setBoard(GolBoard board) {
        this.board = board;
        updateObservers();
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
    public void updateObservers() {
        Map<Player, Integer> playerToScore = new HashMap<>(); // TODO: update
        EncapsulatedGolState encapsulatedGolState = new EncapsulatedGolState(playerToScore, getBoard(),getNumberOfGenerations(), getCurrentPlayer());
        for(Observer observer: observers){
            observer.recieveGolStateEncapsulated(encapsulatedGolState);
        }
    }

    // TODO: implement cell counting method in the cell by going over board copy
    //method: take playersList and go over current board counting cells


    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);

    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }
}
