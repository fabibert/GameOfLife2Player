package UI;

public interface UI {
    //request info
    String requestPlayerName(); //to validate in Game

    Coordinates requestCellCreation();

    Coordinates requestCellDeletion();

    //change display
    //void displayState(Board board);
}
