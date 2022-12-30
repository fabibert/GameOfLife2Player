package UI;

import State.EncapsulatedGolState;
import State.GolBoardImpl;
import State.GolCell;
import State.Player;
import UI.gridElements.BorderedGrid;
import UI.gridElements.RectangleWithColorFromOccupyingPlayer;
import UI.gridElements.StackPaneCell;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class GridUI {
    EncapsulatedGolState state;
    GridPane grid;
    private List<Integer> indices = List.of();

    private CountDownLatch countDownLatch;
    //public static String playerName = "default";

    public GridUI(EncapsulatedGolState state) {
        this.state = state;
        countDownLatch = new CountDownLatch(1);
    }

    public void start(Stage primaryStage) {
        this.grid = createGrid((GolBoardImpl) state.board(), state.playersToScore().keySet().stream().toList());
        //grid.addEventHandler(MouseEvent.MOUSE_CLICKED, this::locateClickOnGrid);
        primaryStage.setScene(getScene(grid));
        primaryStage.show();
    }

    private GridPane createGrid(GolBoardImpl board, List<Player> playersList) {
        GridPane grid = new BorderedGrid(board.getBoardWidth(), board.getBoardHeight());
        setCellsFromBoardToGrid(board, grid, playersList);
        grid.getStyleClass().add("grid");
        return grid;
    }

    private Rectangle createCell(GolCell golCell, List<Player> playersList) {
        Rectangle rectangle = new RectangleWithColorFromOccupyingPlayer(golCell, playersList);
        return rectangle;
    }

    //update displayed grid


    private GridPane setCellsFromBoardToGrid(GolBoardImpl board,GridPane grid, List<Player> playersList) {
        //add cells to grid
        for (int x = 0 ; x < board.getBoardWidth() ; x++) {
            for (int y = 0 ; y < board.getBoardHeight() ; y++) {
                Rectangle rectangle = new RectangleWithColorFromOccupyingPlayer(board.getCell(x,y), playersList);
                StackPane node = new StackPaneCell(rectangle);
                rectangle.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> locateClickOnGrid(node));
                node.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> locateClickOnGrid(node));
                grid.add(node, x, y);
                //grid.add(createCell(board.getCell(x,y), playersList), x, y);
            }
        }
        return grid;
    }

    void locateClickOnGrid(Node node){
        Integer colIndex = GridPane.getColumnIndex(node);
        Integer rowIndex = GridPane.getRowIndex(node);
        //System.out.println((colIndex)+ ":" + (rowIndex));
        setReturnValue(List.of(colIndex,rowIndex));
    }

//    void locateClickOnGrid(MouseEvent e){
//        Node clickedNode = e.getPickResult().getIntersectedNode();
//        Integer colIndex = GridPane.getColumnIndex(clickedNode);
//        Integer rowIndex = GridPane.getRowIndex(clickedNode);
//        System.out.println((colIndex)+ ":" + (rowIndex));
//        setReturnValue(List.of(colIndex,rowIndex));
//    }

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
        countDownLatch = new CountDownLatch(1);
        return indices;
    }

    Scene getScene(GridPane grid){
        StackPane root = new StackPane(grid);
        Scene scene = new Scene(root, 600, 600);
        scene.getStylesheets().add("grid-with-borders.css");
        return scene;
    }
}


