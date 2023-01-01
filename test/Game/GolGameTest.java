package Game;


import state.*;
import UI.GolUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class GolGameTest {

    private @Mock GolUI gameOfLifeUI;
    private @Mock GolStateBuilder builder;
    private @Mock GolLogic logic;
    private GolState state;
    private GolGame game;
    private String player1Name;
    private String player2Name;
    private Player player1;
    private Player player2;
    private GolBoardImpl golBoard;

    @BeforeEach
    public void setUp(){
        openMocks(this);
        player1Name = "player1";
        player2Name = "player2";
        player1 = new Player(player1Name);
        player2 = new Player(player2Name);
        golBoard = new GolBoardImpl(10, 10);
        state = new GolStateImpl(player1, player2, golBoard);
        game = Mockito.spy(new GolGame(gameOfLifeUI, builder));

        when(gameOfLifeUI.requestPlayerName()).thenReturn(player1Name, player2Name);

        when(builder.createState(anyString(), anyString())).thenReturn(state);

        when(game.createGameLogic(gameOfLifeUI, state)).thenReturn(logic);
    }
    @Test
    public void testCorrectNamesAreTakenFromTheUi(){
        game.start();
        verify(builder).createState(player1Name, player2Name);
    }


    @Test
    public void testNameVerificationDoesNotAllowBothPlayersToHaveTheSameName(){
        when(gameOfLifeUI.requestPlayerName()).thenReturn(player1Name, player1Name, player2Name);
        game.start();
        verify(builder).createState(player1Name, player2Name);
    }

    @Test
    void testThatUiIsAddedAsAnObserver() {
        game.initObserverPattern(gameOfLifeUI, state);
        state.updateObservers();
        Mockito.verify(gameOfLifeUI).recieveGolStateEncapsulated(ArgumentMatchers.any(EncapsulatedGolState.class));
    }

    @Test
    void testGolGameLogicIsReturned(){
        game = new GolGame(gameOfLifeUI, builder);
        assertNotNull(game.createGameLogic(gameOfLifeUI, state));
    }

    @Test
    void testRunsGameLogic(){
        game.start();
        verify(logic).run();
    }
}