package UI;

import javafx.stage.Stage;

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
        field.start(stage);
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


}
