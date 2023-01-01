package UI;

import state.EncapsulatedGolState;
import javafx.stage.Stage;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import static com.sun.javafx.application.PlatformImpl.runLater;

public class GolUIImpl implements GolUI {
    Stage stage;
    GridUI gridUi;

    //should consistently display a Grid
    //update grid if we pass state
    CountDownLatch countDownLatch;

    public GolUIImpl(Stage stage){
        this.stage = stage;
        this.countDownLatch = new CountDownLatch(1);
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
        return getClickedField(true);
    }

    @Override
    public Coordinates requestPlayerCellDeletion() {
        //display deletion request
        return getClickedField(false);
    }


    @Override
    public void recieveGolStateEncapsulated(EncapsulatedGolState state) {
        if(gridUi == null){
            gridUi = new GridUI(countDownLatch);
            runLater(() -> gridUi.start(stage, state));
        }
        else{
            runLater(() -> gridUi.update(state));
        }
    }

    public Coordinates getClickedField(boolean creation) {
        //set boolean value on grid
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        gridUi.setListening(true);
        List<Integer> indices = gridUi.awaitReturnValue();
        System.out.println("Passed out: " + indices);
        return new Coordinates(indices.get(0), indices.get(1));
    }
}



