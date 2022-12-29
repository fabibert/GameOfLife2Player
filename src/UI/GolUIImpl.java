package UI;

import State.EncapsulatedGolState;
import javafx.stage.Stage;

import static com.sun.javafx.application.PlatformImpl.runLater;

public class GolUIImpl implements GolUI {
    Stage stage;

    //should consistently display a Grid
    //update grid if we pass state

    public GolUIImpl(Stage stage){
        this.stage = stage;
    }

    public String requestPlayerName(){
        TextFieldGetData field = new TextFieldGetData();
        runLater(() -> field.start(stage));
        String name = field.awaitReturnValue();
        System.out.println(name);
        return name;
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
        GridUI grid = new GridUI(state);
        grid.start(stage);
        //runLater(() -> grid.start(stage));
        //List<Integer> indices = grid.awaitReturnValue();
        //System.out.println(indices);
    }

    public void getClickedField(EncapsulatedGolState state) {
        //displayState
        GridUI grid = new GridUI(state);
        grid.start(stage);
        //runLater(() -> grid.start(stage));
        //List<Integer> indices = grid.awaitReturnValue();
        //System.out.println(indices);
    }
}



