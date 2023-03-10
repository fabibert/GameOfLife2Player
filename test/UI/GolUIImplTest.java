package UI;

import Game.GolGame;
import State.EncapsulatedGolState;
import State.GolBoardImpl;
import State.Player;
import javafx.application.Application;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class GolUIImplTest extends Application {

    GolUI ui;
    EncapsulatedGolState state;
    EncapsulatedGolState state2;
    GolGame game;
    private GolBoardImpl board1;

    public static void main(String[] args) {
        GolUIImplTest.launch();
    }

    @Override
    public void start(Stage stage) {
        ui = new GolUIImpl(stage);
        Player player1 = new Player("Fabio");
        Player player2 = new Player("Joe");
        GolBoardImpl board = new GolBoardImpl(10, 10);
        board.setCellToPlayer(1,1, player1.playerName());

        board1 = new GolBoardImpl(10, 10);
        board1.setCellToPlayer(1,1, player1.playerName());

        state = new EncapsulatedGolState(Map.of(player1, 4, player2, 3), board, 3, player1);

        board1.setCellToPlayer(3,3, player1.playerName());
        board1.setCellToPlayer(5,4, player2.playerName());

        state2 = new EncapsulatedGolState(Map.of(player1, 4, player2, 3), board1, 3, player1);
        testRecieveGolStateEncapsulated();
    }

    @Test
    void testRecieveGolStateEncapsulated() {

        new Thread(() -> {
            ui.recieveGolStateEncapsulated(state);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            ui.recieveGolStateEncapsulated(state2);
            Coordinates coord = ui.requestPlayerCellCreation();
            board1.setCellToPlayer(coord.x(), coord.y(), "Fabio");
            ui.recieveGolStateEncapsulated(state2);
            System.out.println(coord.x() + " " + coord.y());
        }).start();

    }

    @Test
    void testRequestClickLocation(){

    }
}


class MyLauncher {
    public static void main(String[] args) {
        GolUIImplTest.main(args);
    }
}