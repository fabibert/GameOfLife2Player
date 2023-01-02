package game;

import ui.GolUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import state.*;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class GolLogicTest {
    private @Mock GolUI gameOfLifeUI;
    private @Mock GolStateBuilder builder;
    private GolState state;
    private GolLogic logic;
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
        logic = Mockito.spy(new GolLogic(gameOfLifeUI,state));

        when(gameOfLifeUI.requestPlayerName()).thenReturn(player1Name, player2Name);

        when(builder.createState(anyString(), anyString())).thenReturn(state);

        //when(game.createGameLogic(gameOfLifeUI, state)).thenReturn(logic);
    }
    @Test
    public void testChecksForWinner(){
        logic.run();
        verify(state).checkForWinner();
    }


    //@Test
//    public void testNameVerificationDoesNotAllowBothPlayersToHaveTheSameName(){
//        when(gameOfLifeUI.requestPlayerName()).thenReturn(player1Name, player1Name, player2Name);
//        game.start();
//        verify(builder).createState(player1Name, player2Name);
//    }
//
//    @Test
//    void testThatUiIsAddedAsAnObserver() {
//        game.initObserverPattern(gameOfLifeUI, state);
//        state.updateObservers();
//        Mockito.verify(gameOfLifeUI).recieveGolStateEncapsulated(ArgumentMatchers.any(EncapsulatedGolState.class));
//    }
//
//    @Test
//    void testGolGameLogicIsReturned(){
//        game = new GolGame(gameOfLifeUI, builder);
//        assertNotNull(game.createGameLogic(gameOfLifeUI, state));
//    }
//
//    @Test
//    void testRunsGameLogic(){
//        game.start();
//        verify(logic).run();
//    }

}