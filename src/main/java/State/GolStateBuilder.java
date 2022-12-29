package State;

public class GolStateBuilder implements StateBuilder<GolState> {
    @Override
    public GolState createState(String player1, String player2) {
        return new GolStateImpl(player1, player2, new GolBoardImpl(10, 10));
    }
}
