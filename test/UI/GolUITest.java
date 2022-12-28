package UI;


import State.GolBoardImpl;
import org.junit.jupiter.api.Test;

class GolUITest {

    GolBoardImpl board = new GolBoardImpl(10,10);
    //set cell
    //get cell


    @Test
    void displayState() {
        //get board and display
        board.setCellToPlayer(1,1, "Fabio");
        System.out.println(board.getCell(1,1));

    }

    @Test
    void requestPlayerName() {
    }

    @Test
    void getPlayerCellCreation() {
        //get Board and clicked on cell
    }

    @Test
    void getPlayerCellDeletion() {
    }
}