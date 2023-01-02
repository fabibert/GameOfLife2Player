package game.verifier;

import state.data.Coordinates;

public interface Verifier {
    boolean verifyCellDeletion(Coordinates coordinates);

    boolean verifyCellCreation(Coordinates coordinates);
}
