package Game;


import UI.GolUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import state.*;

import static org.mockito.MockitoAnnotations.openMocks;

class GolGameTest {

    @Mock
    GolUI gameOfLifeUI;
    @Mock
    GolStateBuilder builder;
    GolState state;
    GolGame game;
    private Player player1;
    private Player player2;
    private GolBoardImpl golBoard;

    @BeforeEach
    public void setUp(){
        openMocks(this);
        player1 = new Player("Fabio");
        player2 = new Player("Joe");
        golBoard = new GolBoardImpl(10, 10);
        state = new GolStateImpl(player1, player2, golBoard);
        game = new GolGame(gameOfLifeUI, builder);
    }

    @Test
    void testThatUiIsAddedAsAnObserver() {
        game.initObserverPattern(gameOfLifeUI, state);
        state.updateObservers();
        Mockito.verify(gameOfLifeUI).recieveGolStateEncapsulated(ArgumentMatchers.any(EncapsulatedGolState.class));
    }
}