package state;

import ui.Coordinates;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.MockitoAnnotations.openMocks;

class GolStateBuilderTest {
    private GolStateBuilder builder;
    private GolState state;
    private String player1Name;
    private String player2Name;
    private Player player1;
    private Player player2;
    private GolBoardImpl golBoard;

    @BeforeEach
    public void setUp(){
        openMocks(this);
        player1Name = "Joe";
        player2Name = "Fabio";
        player1 = new Player(player1Name);
        player2 = new Player(player2Name);
        golBoard = new GolBoardImpl(10, 10);
        state = new GolStateImpl(player1, player2, golBoard);
        builder = new GolStateBuilder();
    }

    @Test
    public void testPlayerStoringAndAlphabeticalOrder(){
        GolState state = builder.createState(player1Name,player2Name);
        assertTrue(state.getPlayers().get(0).playerName().equals(player2Name));
    }

    @Test
    public void testStateInitWinner(){
        GolState state = builder.createState(player1Name,player2Name);
        assertFalse(state.checkForWinner());
    }

    @Test
    public void testStateInitLeadingPlayerAlphabeticallyFirst(){
        GolState state = builder.createState(player1Name,player2Name);
        assertFalse(state.getLeadingPlayer().equals(player2));
    }

    @Test
    public void testStateInitBoardPattern(){
        GolState state = builder.createState(player1Name,player2Name);
        assertTrue(state.getBoard().getCell(new Coordinates(2,3)).getPlayer().equals(player1));
    }
}