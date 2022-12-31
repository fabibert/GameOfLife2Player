package Game;

import UI.Coordinates;
import state.*;

public class RegenerationImpl implements Regeneration {
    private GolState golState;
    int neighbours1 = 0;
    int neighbours2 = 0;

    public RegenerationImpl(GolState golState) {
        this.golState = golState;
    }

    @Override
    public GolBoard regenerationBoard() {
        GolBoard oldBoard = golState.getBoard();
        GolBoard newBoard = new GolBoardImpl(oldBoard.getBoardHeight(), oldBoard.getBoardWidth());
        Player player1 = golState.getPlayers().get(0);
        Player player2 = golState.getPlayers().get(1);
        for (int y = 0; y < oldBoard.getBoardHeight(); y++) {
            for (int x = 0; x < oldBoard.getBoardWidth(); x++) {
                Coordinates coordinates = new Coordinates(x, y);
                cellCreationRules(oldBoard, newBoard, player1, player2, coordinates);
            }
        }
        return newBoard;
    }

    private void cellCreationRules(GolBoard oldBoard, GolBoard newBoard, Player player1, Player player2, Coordinates coordinates) {
        GolCell golCell = oldBoard.getCell(coordinates);
        if (golCell.isAlive()) {
            Player currentOccupant = golCell.getPlayer();
            if (getNumberOfAliveNeighbours(coordinates, player1, player2) < 2)
                newBoard.setCellEmpty(coordinates);
            else if (getNumberOfAliveNeighbours(coordinates, player1, player2) == 2)
                newBoard.setCellToPlayer(coordinates, currentOccupant);
            else if(getNumberOfAliveNeighbours(coordinates, player1, player2) == 3)
                if(this.neighbours1 > neighbours2)
                    newBoard.setCellToPlayer(coordinates, player1);
                else
                    newBoard.setCellToPlayer(coordinates, player2);
            else if (getNumberOfAliveNeighbours(coordinates, player1, player2) > 3)
                newBoard.setCellEmpty(coordinates);
        }
        if (!golCell.isAlive()) {
            if (getNumberOfAliveNeighbours(coordinates, player1, player2) == 3)
                if(this.neighbours1 > neighbours2)
                    newBoard.setCellToPlayer(coordinates, player1);
                else
                    newBoard.setCellToPlayer(coordinates, player2);
        }
    }


    private int getNumberOfAliveNeighbours(Coordinates coordinates, Player player1, Player player2) {
        this.neighbours1 = 0;
        this.neighbours2 = 0;

        int x = coordinates.x();
        int y = coordinates.y();

        for(int j = y-1; j<=y+1; j++){
            for(int i= x-1; i<= x+1; i++){
                if(!(j==y && i==x)){
                    this.neighbours1 += checkAlive(i,j,player1);
                    this.neighbours2 += checkAlive(i,j,player2);
                }
            }
        }
        return neighbours1 + neighbours2;
    }

    private int checkAlive(int x, int y, Player player) {
        if (x < 0 || golState.getBoard().getBoardWidth() <= x)
            return 0;
        if (y < 0 || golState.getBoard().getBoardWidth() <= y)
            return 0;
        if (golState.getBoard().getCell(new Coordinates(x, y)).isAlive())
            return golState.getBoard().getCell(new Coordinates(x, y)).getPlayer().equals(player)? 1 : 0;
        return 0;
    }
}
