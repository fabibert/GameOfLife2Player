package UI;

import State.EncapsulatedGolState;
import javafx.stage.Stage;

import java.util.List;

import static com.sun.javafx.application.PlatformImpl.runLater;

public class GolUIImpl implements GolUI {
    Stage stage;
    GridUI gridUi;

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
        //display creation request
        return getClickedField();
    }

    @Override
    public Coordinates requestPlayerCellDeletion() {
        //display deletion request
        return getClickedField();
    }


    @Override
    public void recieveGolStateEncapsulated(EncapsulatedGolState state) {
        if(gridUi == null){
            gridUi = new GridUI(state);
            runLater(() -> gridUi.start(stage));
        }
        else{
            runLater(() -> gridUi.update(state));
        }
    }

    public Coordinates getClickedField() {
        //set boolean value on grid
        gridUi.setListening(true);
        List<Integer> indices = gridUi.awaitReturnValue();
        System.out.println("Passed out: " + indices);
        return new Coordinates(indices.get(0), indices.get(1));
    }
}



