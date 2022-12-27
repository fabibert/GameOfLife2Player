package UI;

public abstract class UI {
    //request info
    abstract String requestPlayerName(); //to validate in Game

    abstract Coordinates requestCellCreation();

    abstract Coordinates requestCellDeletion();

    //change display
    //void displayState(Board board);
}
