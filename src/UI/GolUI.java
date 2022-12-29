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

//    public String requestPlayerName(){
//          TextFieldGetData.main();
//          return TextFieldGetData.playerName;
//    }

    public String requestPlayerName(){
//        TextFieldGetData.main();
//        TextFieldGetData.playerName;
        TextFieldGetData field = new TextFieldGetData();
        field.start(stage);
        return field.playerName;
    }

    //abstract void getPlayerCellCreation();

    //abstract void getPlayerCellDeletion();

}
