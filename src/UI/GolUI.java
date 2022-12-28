package UI;

public class GolUI extends UI {

    public GolUI(){

    }
    void displayState(){};

    public String requestPlayerName(){
        TextFieldGetData field = new TextFieldGetData();
        TextFieldGetData.main();
        //here we need to wait on thread
        System.out.println(field.getReturnValue());
        return field.getReturnValue();
    }

    //abstract void getPlayerCellCreation();

    //abstract void getPlayerCellDeletion();

}
class MyLaunchey {
    public static void main(String[] args){
        GolUI ui = new GolUI();
        ui.requestPlayerName();
    }
}
