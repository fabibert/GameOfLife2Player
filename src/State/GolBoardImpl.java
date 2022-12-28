package State;

import java.util.Arrays;
import java.util.Optional;

public class GolBoardImpl extends GolBoard {

    GolCell[][] board;
    int boardHeight;
    int boardWidth;

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

    public void setCellToPlayer(int x,int y, String name) {
        board[x][y] = new GolCell(Optional.of(new Player(name)));
    }

    public void setCellEmpty(int x,int y) {
        board[x][y] = new GolCell(Optional.empty());
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
        GolBoardImpl board = new GolBoardImpl(10,10);
        board.setCellToPlayer(1,1, "Fabio");
        System.out.println(board.getCell(1,1));
    }
}

