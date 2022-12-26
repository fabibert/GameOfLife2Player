package Game;

import State.GolState;
import UI.GolUI;

public abstract class GolLogic {
    GolState state;
    GolUI ui;
    Verifier veri;
    Regeneration reg;

    abstract void run();

}
