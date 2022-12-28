package UI;

public class GolUI extends UI {

    public GolUI(){

    }
    void displayState(){
        GridUI grid = new GridUI();
        grid.main();
    };

    public String requestPlayerName(){
//        CountDownLatch latch = new CountDownLatch(1);
//        //TextFieldGetData field = new TextFieldGetData(latch);
//        Stage stage = new Stage();
//        TextFieldGetData.main();
//        String playerName = null;
//        try {
//            playerName = field.awaitReturnValue();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        return playerName;
        return " ";
    }

    //abstract void getPlayerCellCreation();

    //abstract void getPlayerCellDeletion();

}
class MyLaunchey {
    public static void main(String[] args){
        GolUI ui = new GolUI();
        //ui.requestPlayerName();
        ui.displayState();
    }
}
