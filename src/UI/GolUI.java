package UI;

public interface GolUI extends UI {
    abstract void displayState();

    abstract Coordinates requestPlayerCellCreation();

    abstract Coordinates requestPlayerCellDeletion();

}
