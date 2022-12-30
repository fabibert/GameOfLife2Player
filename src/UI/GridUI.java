package UI;

import state.EncapsulatedGolState;
import state.GolBoardImpl;
import state.GolCell;
import state.Player;
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

    private CountDownLatch countDownLatchAwaitClick;
    private CountDownLatch countDownLatchAwaitCreation;
    private boolean listening;
    //public static String playerName = "default";

    public GridUI(EncapsulatedGolState state, CountDownLatch countDownLatchCreation) {
        this.state = state;
        countDownLatchAwaitClick = new CountDownLatch(1);
        countDownLatchAwaitCreation = countDownLatchCreation;
    }

    public void start(Stage primaryStage) {
        this.grid = createGrid((GolBoardImpl) state.board(), state.playersToScore().keySet().stream().toList());
        primaryStage.setScene(getScene(grid));
        primaryStage.show();
        countDownLatchAwaitCreation.countDown();
    }

    public void update( EncapsulatedGolState state){
        setCellsFromBoardToGrid((GolBoardImpl) state.board(), grid, state.playersToScore().keySet().stream().toList());
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
        if(listening) {
            Integer colIndex = GridPane.getColumnIndex(node);
            Integer rowIndex = GridPane.getRowIndex(node);
            setReturnValue(List.of(colIndex, rowIndex));
        }
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
        countDownLatchAwaitClick.countDown();
        this.listening = false;
    }

    public List<Integer> awaitReturnValue() {
        try {
            countDownLatchAwaitClick.await();
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

    public void setListening(boolean listening) {
        this.listening = listening;
        countDownLatchAwaitClick = new CountDownLatch(1);
    }
}


