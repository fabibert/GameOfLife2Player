package game;

import ui.Coordinates;
import state.*;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RegenerationImpl implements Regeneration {

    private final GolState golState;


    Player player1;
    Player player2;


    public RegenerationImpl(GolState golState) {
        this.golState = golState;
    }

    @Override
    public GolBoard regenerationBoard() {
        GolBoard oldBoard = golState.getBoard();
        GolBoard newBoard = new GolBoardImpl(oldBoard.getBoardHeight(), oldBoard.getBoardWidth());
        this.player1 = golState.getPlayers().get(0);
        this.player2 = golState.getPlayers().get(1);
        for (int y = 0; y < oldBoard.getBoardHeight(); y++) {
            for (int x = 0; x < oldBoard.getBoardWidth(); x++) {
                Coordinates coordinates = new Coordinates(x, y);
                cellCreationRules(oldBoard, newBoard, coordinates);
            }
        }
        return newBoard;
    }

    private void cellCreationRules(GolBoard oldBoard, GolBoard newBoard, Coordinates coordinates) {
        GolCell golCell = oldBoard.getCell(coordinates);
        List<Player> aliveNeighbours = getAliveNeighbourOwners(coordinates);
        int numberOfAliveNeighbours = aliveNeighbours.size();
        if (golCell.isAlive()) {
            Player currentOccupant = golCell.getPlayer();
            if(numberOfAliveNeighbours == 2 || numberOfAliveNeighbours == 3){
                newBoard.setCellToPlayer(coordinates, currentOccupant);
            } else {
                newBoard.setCellEmpty(coordinates);
            }
        } else {
            if (numberOfAliveNeighbours == 3) {
                Map<Player, Long> mapOfPlayerToCount = aliveNeighbours
                        .stream()
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
                Player playerWithHighestCount = mapOfPlayerToCount
                        .entrySet()
                        .stream()
                        .max(Map.Entry.comparingByValue())
                        .map(Map.Entry::getKey)
                        .orElseThrow(RuntimeException::new);
                newBoard.setCellToPlayer(coordinates, playerWithHighestCount);
            }
        }
    }


    private List<Player> getAliveNeighbourOwners(Coordinates coordinates) {
        List<Player> neighbours = new ArrayList<>();

        int x = coordinates.x();
        int y = coordinates.y();

        for(int j = y-1; j<=y+1; j++){
            for(int i= x-1; i<= x+1; i++){
                if(!(j==y && i==x)){
                    checkAlive(i,j).ifPresent(neighbours::add);
                }
            }
        }
        return neighbours;
    }

    private Optional<Player> checkAlive(int x, int y) {
        if (x < 0 || golState.getBoard().getBoardWidth() <= x)
            return Optional.empty();
        if (y < 0 || golState.getBoard().getBoardWidth() <= y)
            return Optional.empty();
        else{
            return golState.getBoard().getCell(new Coordinates(x, y)).player();
        }
    }
}
