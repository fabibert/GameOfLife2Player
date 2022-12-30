package state;

public class GolStateBuilder implements StateBuilder<GolState> {
    @Override
    public GolState createState(String player1, String player2) {
        GolBoardImpl golBoard = new GolBoardImpl(10, 10);
        //TODO use set intial pattern function on board
        golBoard.setCellToPlayer(1,1, player1);
        golBoard.setCellToPlayer(2,2, player1);
        golBoard.setCellToPlayer(4,4, player2);
        return new GolStateImpl(player1, player2, golBoard);
    }
}
