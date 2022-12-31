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
        GolBoard board = golState.getBoard();
        GolBoard golBoard = new GolBoardImpl(board.getBoardHeight(), board.getBoardWidth());
        Player player1 = golState.getPlayers().get(0);
        Player player2 = golState.getPlayers().get(1);
        for (int y = 0; y < board.getBoardHeight(); y++) {
            for (int x = 0; x < board.getBoardWidth(); x++) {
                Coordinates coordinates = new Coordinates(x, y);
                cellCreationRules(board, golBoard, player1, player2, coordinates);
            }
        }
        return golBoard;
    }

    //board is the old board and golBoard the new one
    private void cellCreationRules(GolBoard board, GolBoard golBoard, Player player1, Player player2, Coordinates coordinates) {
        GolCell golCell = board.getCell(coordinates);
        if (golCell.isAlive()) {
            Player currentOccupant = golCell.getPlayer();
            if (getNumberOfAliveNeighbours(coordinates, player1, player2) < 2)
                golBoard.setCellEmpty(coordinates);
            else if (getNumberOfAliveNeighbours(coordinates, player1, player2) == 2)
                golBoard.setCellToPlayer(coordinates, currentOccupant);
            else if(getNumberOfAliveNeighbours(coordinates, player1, player2) == 3)
                if(this.neighbours1 > neighbours2)
                    golBoard.setCellToPlayer(coordinates, player1);
                else
                    golBoard.setCellToPlayer(coordinates, player2);
            else if (getNumberOfAliveNeighbours(coordinates, player1, player2) > 3)
                golBoard.setCellEmpty(coordinates);
        }
        if (!golCell.isAlive()) {
            if (getNumberOfAliveNeighbours(coordinates, player1, player2) == 3)
                if(this.neighbours1 > neighbours2)
                    golBoard.setCellToPlayer(coordinates, player1);
                else
                    golBoard.setCellToPlayer(coordinates, player2);
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
