package state.board;

import state.data.GolCell;
import state.data.Player;
import state.data.Coordinates;

import java.util.Arrays;
import java.util.Optional;

public class GolBoardImpl implements GolBoard {
    GolCell[][] board;
    int boardHeight;
    int boardWidth;

    public GolBoardImpl(GolCell[][] board){
        this.board = board;
        this.boardHeight = board.length;
        this.boardWidth = board[0].length;
    }

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

    private GolCell[][] getArray(){
        return Arrays.stream(board).map(arr -> Arrays.copyOf(arr, arr.length)).toArray(GolCell[][]::new);
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

    @Override
    public GolBoard clone() {
        return new GolBoardImpl(getArray());
    }
}


