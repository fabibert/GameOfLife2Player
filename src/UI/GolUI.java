package UI;

import State.EncapsulatedGolState;

public interface GolUI extends UI {

    Coordinates requestCellCreation();
    Coordinates requestCellDeletion();
    void displayState(EncapsulatedGolState board);

}
