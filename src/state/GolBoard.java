package state;

import java.util.Optional;

public abstract class GolBoard implements Board<GolCell>{
    GolCell[][] board;

    //TODO: accept coordinates
    //TODO: pass player instead of PlayerName
    public void setCellToPlayer(int x, int y, String name) {
        board[x][y] = new GolCell(Optional.of(new Player(name)));
    }

    //TODO: accept coordinates
    public void setCellEmpty(int x, int y) {
        board[x][y] = new GolCell(Optional.empty());
    }

    //TODO: accept coordinates
    public GolCell getCell(int x, int y) {
        return board[x][y];
    }

    public abstract GolCell[][] getArray();

    public abstract void setArray(GolCell[][] array);
}
