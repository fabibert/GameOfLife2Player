package State;

import java.util.Arrays;
import java.util.Optional;

public class GolBoardImpl extends GolBoard {

    GolCell[][] board;

    public GolBoardImpl(){
        board = new GolCell[10][10];
        for (GolCell[] row: board)
            Arrays.fill(row, new GolCell(Optional.empty()));
    }

    public void setCell(int x,int y, String name) {
        board[x][y] = new GolCell(Optional.of(new Player(name)));
    }
    public GolCell getCell(int x, int y) {
        return board[x][y];
    }

    @Override
    public GolCell[][] getArray(){
        return new GolCell[0][];
    }

    @Override
    public void setArray(GolCell[][] array){
    }
}

class MyLaunch {
    public static void main(String[] args){
        GolBoardImpl board = new GolBoardImpl();
        board.setCell(1,1, "Fabio");
        System.out.println(board.getCell(1,1));
    }
}

