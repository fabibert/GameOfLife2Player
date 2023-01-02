package state.stateBuilder;

import state.GolState;
import state.GolStateImpl;
import state.data.Player;
import state.board.GolBoardImpl;
import state.data.Coordinates;

public class GolStateBuilder implements StateBuilder<GolState> {
    @Override
    public GolState createState(String playerName1, String playerName2) {
        Player player1 = new Player(playerName1);
        Player player2 = new Player(playerName2);
        GolBoardImpl golBoard = new GolBoardImpl(10, 10);
        setInitialPattern(player1, player2, golBoard);
        return new GolStateImpl(player1, player2, golBoard);
    }

    private void setInitialPattern(Player player1, Player player2, GolBoardImpl golBoard) {
        golBoard.setCellToPlayer(new Coordinates(2,3), player1);
        golBoard.setCellToPlayer(new Coordinates(4,2), player1);
        golBoard.setCellToPlayer(new Coordinates(3,3), player1);
        golBoard.setCellToPlayer(new Coordinates(4,4), player1);
        golBoard.setCellToPlayer(new Coordinates(5,5), player2);
        golBoard.setCellToPlayer(new Coordinates(6,6), player2);
        golBoard.setCellToPlayer(new Coordinates(5,7), player2);
        golBoard.setCellToPlayer(new Coordinates(7,6), player2);
    }
}

