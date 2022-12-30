package state;

import java.util.Arrays;
import java.util.Optional;

public class GolBoardImpl extends GolBoard {

    //TODO: merge class with GolBoard or GolBoard interface only
    int boardHeight;
    int boardWidth;

    public GolBoardImpl(GolCell[][] board){
        super.board = board;
    }

    public GolBoardImpl(int boardHeight,int boardWidth){
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;
        board = new GolCell[this.boardHeight][this.boardWidth];
        for (GolCell[] row: board)
            Arrays.fill(row, new GolCell(Optional.empty()));
    }

    public int getBoardHeight(){
        return this.boardHeight;
    }

    public int getBoardWidth(){
        return this.boardWidth;
    }


    @Override
    public GolCell[][] getArray(){
        return Arrays.stream(board).map(arr -> Arrays.copyOf(arr, arr.length)).toArray(GolCell[][]::new);
    }

    @Override
    public void setArray(GolCell[][] array){
    }
}

class MyLaunch {
    public static void main(String[] args){
        GolBoardImpl board = new GolBoardImpl(10,10);
        board.setCellToPlayer(1,1, "Fabio");
        System.out.println(board.getCell(1,1));
    }
}

