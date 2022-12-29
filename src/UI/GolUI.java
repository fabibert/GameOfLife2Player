package UI;

import javafx.stage.Stage;

public class GolUI extends UI {
    Stage stage;

    public GolUI(Stage stage){
        this.stage = stage;
    }

    void displayState(){
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
        return field.playerName;
    }

    //abstract void getPlayerCellCreation();

    //abstract void getPlayerCellDeletion();

}
