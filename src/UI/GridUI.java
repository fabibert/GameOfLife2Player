package UI;

import State.EncapsulatedGolState;
import State.GolBoardImpl;
import State.GolCell;
import State.Player;
import UI.gridElements.BorderedGrid;
import UI.gridElements.RectangleWithColorFromOccupyingPlayer;
import UI.gridElements.StackPaneCell;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class GridUI extends Application {
    EncapsulatedGolState state;
    GridPane grid;
    private List<Integer> indices = List.of();

    private final CountDownLatch countDownLatch;
    //public static String playerName = "default";

    public GridUI(EncapsulatedGolState state) {
        this.state = state;
        countDownLatch = new CountDownLatch(1);
    }

    @Override
    public void start(Stage primaryStage) {
        this.grid = createGrid((GolBoardImpl) state.board(), state.playersToScore().keySet().stream().toList());
        grid.addEventHandler(MouseEvent.MOUSE_CLICKED, e ->{locateClickOnGrid(e);});
        primaryStage.setScene(getScene(grid));
        primaryStage.show();
    }

    private GridPane createGrid(GolBoardImpl board, List<Player> playersList) {
        int numCols = board.getBoardHeight();
        int numRows = board.getBoardWidth();

        GridPane grid = new GridPane();
        grid = new BorderedGrid(grid, numCols, numRows).getGrid();
        grid = setCellsFromBoardToGrid(board, grid, playersList, numCols, numRows);
        grid.getStyleClass().add("grid");
        return grid;
    }

    private StackPane createCell(GolCell golCell, List<Player> playersList) {
        Rectangle rectangle = new RectangleWithColorFromOccupyingPlayer(golCell, playersList).getRectangle();
        return new StackPaneCell(rectangle);
    }

    //update displayed grid


    private GridPane setCellsFromBoardToGrid(GolBoardImpl board,GridPane grid, List<Player> playersList, int numCols, int numRows) {
        //add cells to grid
        for (int x = 0 ; x < numCols ; x++) {
            for (int y = 0 ; y < numRows ; y++) {
                grid.add(createCell(board.getCell(x,y), playersList), x, y);
            }
        }
        return grid;
    }

    void locateClick(){
        grid.addEventHandler(MouseEvent.MOUSE_CLICKED, e ->{locateClickOnGrid(e);});
    }

    void locateClickOnGrid(MouseEvent e){
        Node clickedNode = e.getPickResult().getIntersectedNode();
        Integer colIndex = GridPane.getColumnIndex(clickedNode);
        Integer rowIndex = GridPane.getRowIndex(clickedNode);
        System.out.println((colIndex)+ ":" + (rowIndex));
        //setReturnValue(List.of(colIndex,rowIndex));
    }

    public void setReturnValue(List<Integer> indices){
        this.indices = indices;
        countDownLatch.countDown();
    }

    public List<Integer> awaitReturnValue() {
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return indices;
    }

    Scene getScene(GridPane grid){
        StackPane root = new StackPane(grid);
        Scene scene = new Scene(root, 600, 600);
        scene.getStylesheets().add("grid-with-borders.css");
        return scene;
    }
}


