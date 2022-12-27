package UI;

public abstract class UI {
    //request info
    public abstract String requestPlayerName(); //to validate in Game

    abstract Coordinates requestCellCreation();

    abstract Coordinates requestCellDeletion();

    //change display
    //void displayState(Board board);
}
