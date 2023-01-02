package state;

import ui.Coordinates;

import java.util.Arrays;
import java.util.Optional;

public class GolBoardImpl implements GolBoard {

    GolCell[][] board;

    //TODO: merge class with GolBoard or GolBoard interface only
    int boardHeight;
    int boardWidth;


    //init board with other board
    public GolBoardImpl(GolCell[][] board){
        //TODO: ensure no reference leakage
        //ensure that a copy of the argument board is used
        this.board = board;
        this.boardHeight = board.length;
        this.boardWidth = board[0].length;
    }

    //init fresh board
    public GolBoardImpl(int boardHeight,int boardWidth){
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;
        board = new GolCell[this.boardHeight][this.boardWidth];
        for (GolCell[] row: board)
            Arrays.fill(row, new GolCell(Optional.empty()));
    }

    @Override
    public void setCellToPlayer(Coordinates coordinates, Player player) {
        board[coordinates.x()][coordinates.y()] = new GolCell(Optional.of(player));
    }

    @Override
    public void setCellEmpty(Coordinates coordinates) {
        board[coordinates.x()][coordinates.y()] = new GolCell(Optional.empty());
    }

    @Override
    public GolCell[][] getArray(){
        return Arrays.stream(board).map(arr -> Arrays.copyOf(arr, arr.length)).toArray(GolCell[][]::new);
    }

    @Override
    public void setArray(GolCell[][] array){
        //TODO: implement set array
    }

    @Override
    public GolCell getCell(Coordinates coordinates) {
        return board[coordinates.x()][coordinates.y()];
    }

    @Override
    public int getBoardHeight(){
        return boardHeight;
    }

    @Override
    public int getBoardWidth(){
        return boardWidth;
    }
}


