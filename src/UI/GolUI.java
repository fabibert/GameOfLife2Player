package UI;

import State.Observer;

public interface GolUI extends UI, Observer {

    abstract Coordinates requestPlayerCellCreation();

    abstract Coordinates requestPlayerCellDeletion();



}
