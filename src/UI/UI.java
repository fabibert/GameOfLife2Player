package UI;

public abstract class UI {
    //request info
    public abstract String requestPlayerName(); //to validate in Game

    void requestCellCreation(){};

    void requestCellDeletion(){};

    //change display
    //void displayState(Board board);
}
