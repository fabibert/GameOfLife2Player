package ui;

import state.data.Coordinates;

public interface GolUI extends UI, Observer {

    abstract Coordinates requestPlayerCellCreation();

    abstract Coordinates requestPlayerCellDeletion();
}
