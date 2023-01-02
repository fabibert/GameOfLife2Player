package state;

import state.board.GolBoard;
import state.data.EncapsulatedGolState;
import state.data.GolCell;
import state.data.Player;
import state.data.Coordinates;
import ui.Observer;

import java.util.*;

public class GolStateImpl implements GolState{
    private final List<Player> players;
    private final List<ui.Observer> observers;
    private int numberOfGenerations = 0;
    private GolBoard board;



    public GolStateImpl(Player player1, Player player2, GolBoard board) {
        players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.sort(Comparator.comparing(Player::playerName));
        this.board = board;
        observers = new ArrayList<>();
    }

    @Override
    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public GolBoard getBoard() {
        return board.clone();
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
        EncapsulatedGolState encapsulatedGolState = createEncapsulatedGolState();
        for(ui.Observer observer: observers){
            observer.recieveGolStateEncapsulated(encapsulatedGolState);
        }
    }

    private EncapsulatedGolState createEncapsulatedGolState() {
        Map<Player, Integer> playerToScore = getPlayerToScore();
        Optional<Player> winner = Optional.empty();
        if(checkForWinner())
            winner = Optional.of(getLeadingPlayer());
        return new EncapsulatedGolState(playerToScore, getBoard(),getNumberOfGenerations(), getCurrentPlayer(), winner);
    }

    private Map<Player, Integer> getPlayerToScore() {
        Map<Player, Integer> playerToScore = new HashMap<>();
        players.forEach(p -> playerToScore.put(p, 0));
        for (int i = 0; i < board.getBoardWidth(); i++) {
            for (int j = 0; j < board.getBoardHeight(); j++) {
                GolCell cell = board.getCell(new Coordinates(i, j));
                cell.player().ifPresent(player -> playerToScore.put(player, playerToScore.get(player)+1));
            }
        }
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
        return lowestCount==0;
    }

    public Player getLeadingPlayer(){
        Map<Player, Integer> playersMap = getPlayerToScore();
        return playersMap
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public void removeObserver(ui.Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }
}
