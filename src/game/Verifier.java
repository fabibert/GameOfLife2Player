package game;

import ui.Coordinates;

public interface Verifier {
    boolean verifyCellDeletion(Coordinates coordinates);

    boolean verifyCellCreation(Coordinates coordinates);
}
