package UI;


import State.GolBoardImpl;
import javafx.application.Application;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GolUITest extends Application {
    GolUI ui;
    GolBoardImpl board = new GolBoardImpl(10,10);
    public static void main(String[] args) {
        System.out.println("Hello world!");
        launch();
    }
    public void start(Stage stage) throws Exception {
        ui = new GolUI(stage);
        String playerName = ui.requestPlayerName();
        assertEquals("Fabio", playerName);
    }


    @Test
    void displayState() {
        //pass board to be implemented
        ui.displayState();
        //board.setCellToPlayer(3,3, "Fabio");
    }

    @Test
    void requestPlayerName() {
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