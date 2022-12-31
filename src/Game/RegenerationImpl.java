package Game;

import UI.Coordinates;
import state.*;

public class RegenerationImpl implements Regeneration {
    private GolState golState;

    public RegenerationImpl(GolState golState) {
        this.golState = golState;
    }

    @Override
    public GolBoard regenerationBoard() {
        return evolve();
    }

    GolBoard evolve() {
        GolBoard board = golState.getBoard();
        GolBoard golBoard = new GolBoardImpl(board.getBoardHeight(), board.getBoardWidth());
        Player player1 = golState.getPlayers().get(0);
        Player player2 = golState.getPlayers().get(1);
        for (int y = 0; y < board.getBoardHeight(); y++) {
            for (int x = 0; x < board.getBoardWidth(); x++) {
                Coordinates coordinates = new Coordinates(x, y);
                cellCreationRules(board, golBoard, player1, coordinates);
                cellCreationRules(board, golBoard, player2, coordinates);
            }
        }
        return golBoard;
    }

    private void cellCreationRules(GolBoard board, GolBoard golBoard, Player player, Coordinates coordinates) {
        GolCell golCell = board.getCell(coordinates);
        if (golCell.isAlive()) {
            if (golCell.getPlayer().equals(player)) {
                if (getNumberOfAliveNeighbours(coordinates, player) < 2)
                    golBoard.setCellEmpty(coordinates);
                else if (getNumberOfAliveNeighbours(coordinates, player) == 2 || getNumberOfAliveNeighbours(coordinates, player) == 3)
                    golBoard.setCellToPlayer(coordinates, player);
                else if (getNumberOfAliveNeighbours(coordinates, player) > 3)
                    golBoard.setCellEmpty(coordinates);
            }
        }
        if (!golCell.isAlive()) {
            if (getNumberOfAliveNeighbours(coordinates, player) == 3)
                golBoard.setCellToPlayer(coordinates, player);
        }
    }


    public int getNumberOfAliveNeighbours(Coordinates coordinates, Player player) {
        int neighbours = 0;

        GolBoard board = golState.getBoard();

        int x = coordinates.x();
        int y = coordinates.y();

        neighbours += checkAlive(x - 1, y + 1, player);
        neighbours += checkAlive(x, y + 1, player);
        neighbours += checkAlive(x + 1, y + 1, player);

        neighbours += checkAlive(x - 1, y, player);
        neighbours += checkAlive(x + 1, y, player);

        neighbours += checkAlive(x - 1, y - 1, player);
        neighbours += checkAlive(x, y - 1, player);
        neighbours += checkAlive(x + 1, y - 1, player);

        return neighbours;
    }

    int checkAlive(int x, int y, Player player) {
        if (x < 0 || golState.getBoard().getBoardWidth() <= x)
            return 0;
        if (y < 0 || golState.getBoard().getBoardWidth() <= y)
            return 0;
        if (golState.getBoard().getCell(new Coordinates(x, y)).isAlive())
            return golState.getBoard().getCell(new Coordinates(x, y)).getPlayer().equals(player)? 1 : 0;
        return 0;
    }
}
