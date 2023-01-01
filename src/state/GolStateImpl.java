package state;

import java.util.*;

public class GolStateImpl implements GolState{

    private final List<Player> players;

    private List<Observer> observers;
    private int numberOfGenerations = 0;

    //TODO: here use interface
    private GolBoard board;



    public GolStateImpl(Player player1, Player player2, GolBoard board) {
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
        //TODO: avoid reference leaking
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
        Map<Player, Integer> playerToScore = new HashMap<>();
        players.forEach(p -> playerToScore.put(p, 0));
        Arrays.stream(board.getArray())
                .flatMap(Arrays::stream)
                .filter(GolCell::isAlive)
                .forEach(c -> playerToScore.put(c.getPlayer(), playerToScore.get(c.getPlayer())+1));
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
