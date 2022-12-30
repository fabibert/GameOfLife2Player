package Game;

import UI.Coordinates;

public interface Verifier {
    boolean verifyCellDeletion(Coordinates coordinates);

    boolean verifyCellCreation(Coordinates coordinates);
}
