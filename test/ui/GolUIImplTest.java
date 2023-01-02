package ui;

import game.GolGame;
import javafx.application.Application;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import state.data.EncapsulatedGolState;
import state.board.GolBoardImpl;

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

    }

    @Test
    void testRecieveGolStateEncapsulated() {
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