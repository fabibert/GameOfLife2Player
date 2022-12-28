package UI;

public abstract class GolUI extends UI {
    abstract void displayState();

    public String requestPlayerName(){
        return " ";
    }

    abstract void getPlayerCellCreation();

    abstract void getPlayerCellDeletion();

}
