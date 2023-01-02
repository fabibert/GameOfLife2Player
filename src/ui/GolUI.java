package ui;

import state.Observer;
import state.Player;

public interface GolUI extends UI, Observer {

    abstract Coordinates requestPlayerCellCreation();

    abstract Coordinates requestPlayerCellDeletion();

    public void displayWinnerMsg(Player player);

}
