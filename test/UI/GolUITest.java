package UI;


import State.GolBoardImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GolUITest {

    GolBoardImpl board = new GolBoardImpl(10,10);


    @Test
    void displayState() {
        GolUI ui = new GolUI();
        //pass board to be implemented
        ui.displayState();
        //board.setCellToPlayer(3,3, "Fabio");
    }

    @Test
    void requestPlayerName() {
        GolUI ui = new GolUI();
        String playerName = ui.requestPlayerName();
        assertEquals("Fabio", playerName);
    }

    @Test
    void getPlayerCellCreation() {
        //get Board and clicked on cell
    }

    @Test
    void getPlayerCellDeletion() {
    }

    @Test
    void testTextField(){
        TextFieldGetData.main();
    }

}