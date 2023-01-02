package state;

import ui.Coordinates;

public interface GolBoard extends Board<GolCell>{
    void setCellToPlayer(Coordinates coordinates, Player player);
    
    void setCellEmpty(Coordinates coordinates);

    GolCell getCell(Coordinates coordinates);

    int getBoardHeight();

    int getBoardWidth();

}
