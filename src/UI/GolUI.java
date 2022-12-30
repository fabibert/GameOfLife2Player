package UI;

import state.Observer;

public interface GolUI extends UI, Observer {

    abstract Coordinates requestPlayerCellCreation();

    abstract Coordinates requestPlayerCellDeletion();



}
