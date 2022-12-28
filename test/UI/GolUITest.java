package UI;


import State.GolBoardImpl;
import org.junit.jupiter.api.Test;

class GolUITest {

    GolBoardImpl board = new GolBoardImpl();
    //set cell
    //get cell


    @Test
    void displayState() {
        //get board and display
        board.setCell(1,1);
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