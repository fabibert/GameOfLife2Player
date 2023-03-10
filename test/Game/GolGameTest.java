package Game;


import State.*;
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
        state = new GolStateImpl("", "", new GolBoardImpl(10, 10));
        game = new GolGame(gameOfLifeUI, builder);
    }

    @Test
    void testThatUiIsAddedAsAnObserver() {
        game.initObserverPattern(gameOfLifeUI, state);
        EncapsulatedGolState encapsulatedState = new EncapsulatedGolState(null, null, 0, null);
        state.updateObservers(encapsulatedState);
        Mockito.verify(gameOfLifeUI).recieveGolStateEncapsulated(encapsulatedState);
    }
}