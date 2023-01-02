package ui;

import javafx.scene.paint.Color;
import javafx.stage.Stage;
import state.data.Coordinates;
import state.data.EncapsulatedGolState;

import java.util.*;
import java.util.concurrent.CountDownLatch;

import static com.sun.javafx.application.PlatformImpl.runLater;

public class GolUIImpl implements GolUI {
    Stage stage;
    GridUI gridUi;
    Map<String, Color> playerToColor = new HashMap<>();
    List<String> allowedColors = new ArrayList<>();

    CountDownLatch countDownLatch;

    public GolUIImpl(Stage stage){
        this.stage = stage;
        this.countDownLatch = new CountDownLatch(1);
        allowedColors.add("BLUE");
        allowedColors.add("RED");
    }

    public String requestPlayerName(){
        TextFieldGetData field = new TextFieldGetData(allowedColors.toArray(new String[0]));
        runLater(() -> field.start(stage));
        UIPlayerInformation playerInformation = field.awaitReturnValue();
        String name = playerInformation.name();
        if(!playerToColor.containsKey(name)){
            playerToColor.put(name, playerInformation.color());
            allowedColors.remove(playerInformation.color().equals(Color.RED) ? "RED":"BLUE");
        }
        return name;
    }

    @Override
    public Coordinates requestPlayerCellCreation() {
        return getClickedField(true);
    }

    @Override
    public Coordinates requestPlayerCellDeletion() {
        return getClickedField(false);
    }


    @Override
    public void recieveGolStateEncapsulated(EncapsulatedGolState state) {
        if(gridUi == null){
            gridUi = new GridUI(countDownLatch, playerToColor);
            runLater(() -> gridUi.start(stage, state));
        }
        else{
            runLater(() -> gridUi.update(state));
        }
    }

    public Coordinates getClickedField(boolean creation) {
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        runLater(() -> gridUi.setInstructions(creation));
        gridUi.setListening(true);
        List<Integer> indices = gridUi.awaitReturnValue();
        System.out.println("Passed out: " + indices);
        return new Coordinates(indices.get(0), indices.get(1));
    }
}



