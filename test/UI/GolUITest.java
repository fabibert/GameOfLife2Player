package UI;

import State.EncapsulatedGolState;
import State.GolBoard;
import State.GolBoardImpl;
import State.Player;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GolUITest {

    GolUI golUI = null;

    @Test
    public void testRequestPlayerWillReturnPlayerNameAfterUserInteraction(){
        assertEquals("Tom", golUI.requestPlayerName());
    }

    @Test
    public void testRequestKillCoordinatesWillReturnTheClickedLocationAfterUserInteraction(){
        GolBoard board = new GolBoardImpl();
        golUI.displayState(new EncapsulatedGolState(Map.of(new Player("player1"), 1, new Player("player2"), 3), board));
        assertEquals("Tom", golUI.requestPlayerName());
    }
}