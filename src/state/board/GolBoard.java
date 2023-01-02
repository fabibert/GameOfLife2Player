package state.board;

import state.data.GolCell;
import state.data.Player;
import state.data.Coordinates;

public interface GolBoard extends Board {
    void setCellToPlayer(Coordinates coordinates, Player player);
    
    void setCellEmpty(Coordinates coordinates);

    GolCell getCell(Coordinates coordinates);

    GolBoard clone();
}
