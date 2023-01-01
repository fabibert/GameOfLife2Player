package state;

import java.util.*;

public class GolStateImpl implements GolState{

    private final List<Player> players;

    private List<Observer> observers;
    private int numberOfGenerations = 0;

    //TODO: here use interface
    private GolBoard board;



    public GolStateImpl(Player player1, Player player2, GolBoard board) {
        players = new ArrayList<Player>();
        players.add(player1);
        players.add(player2);
        players.sort(Comparator.comparing(Player::playerName));
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
        Map<Player, Integer> playerToScore = getPlayerToScore();
        EncapsulatedGolState encapsulatedGolState = new EncapsulatedGolState(playerToScore, getBoard(),getNumberOfGenerations(), getCurrentPlayer());
        for(Observer observer: observers){
            observer.recieveGolStateEncapsulated(encapsulatedGolState);
        }
    }

    private Map<Player, Integer> getPlayerToScore() {
        Map<Player, Integer> playerToScore = new HashMap<>();
        players.forEach(p -> playerToScore.put(p, 0));
        Arrays.stream(board.getArray())
                .flatMap(Arrays::stream)
                .filter(GolCell::isAlive)
                .forEach(c -> playerToScore.put(c.getPlayer(), playerToScore.get(c.getPlayer())+1));
        return playerToScore;
    }

    public boolean checkForWinner(){
        Map<Player, Integer> playersMap = getPlayerToScore();
        Integer lowestCount = playersMap
                .entrySet()
                .stream()
                .min(Map.Entry.comparingByValue())
                .map(Map.Entry::getValue)
                .orElseThrow(RuntimeException::new);
        if(lowestCount==0){
            return true;
        }
        else
            return false;
    }

    public Player getLeadingPlayer(){
        Map<Player, Integer> playersMap = getPlayerToScore();
        Player playerWithHighestCount = playersMap
                .entrySet()
                .stream()
                .min(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElseThrow(RuntimeException::new);
        return playerWithHighestCount;
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
