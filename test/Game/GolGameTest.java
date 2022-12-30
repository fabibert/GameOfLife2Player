package Game;


import state.*;
import UI.GolUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.MockitoAnnotations.openMocks;

class GolGameTest {

    @Mock
    GolUI gameOfLifeUI;
    @Mock
    GolStateBuilder builder;
    GolState state;
    GolGame game;

    @BeforeEach
    public void setUp(){
        openMocks(this);
        state = new GolStateImpl(new Player("Fabio"), new Player("Joe"), new GolBoardImpl(10, 10));
        game = new GolGame(gameOfLifeUI, builder);
    }

    @Test
    void testThatUiIsAddedAsAnObserver() {
        game.initObserverPattern(gameOfLifeUI, state);
        EncapsulatedGolState encapsulatedState = new EncapsulatedGolState(null, null, 0, null);
        state.updateObservers();
        Mockito.verify(gameOfLifeUI).recieveGolStateEncapsulated(encapsulatedState);
    }
}