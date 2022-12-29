package UI;

import State.EncapsulatedGolState;
import javafx.stage.Stage;

import java.awt.*;

import static com.sun.javafx.application.PlatformImpl.runLater;

public class GolUIImpl implements GolUI {
    Stage stage;


    public GolUIImpl(Stage stage){
        this.stage = stage;
    }

    public void displayState(){
        GridUI grid = new GridUI();
        grid.main();
    };

    //Static way of getting input
//    public String requestPlayerName(){
//          TextFieldGetData.main();
//          return TextFieldGetData.playerName;
//    }

    public String requestPlayerName(){
        TextFieldGetData field = new TextFieldGetData();
        runLater(() -> field.start(stage));
        return field.awaitReturnValue();
    }

    @Override
    public Coordinates requestPlayerCellCreation() {
        return null;
    }

    @Override
    public Coordinates requestPlayerCellDeletion() {
        return null;
    }


    @Override
    public void recieveGolStateEncapsulated(EncapsulatedGolState state) {
        //displayState
    }
}
