package UI;

import UI.gridElements.BorderedGrid;
import UI.gridElements.RectangleWithColorFromOccupyingPlayer;
import UI.gridElements.StackPaneCell;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import state.EncapsulatedGolState;
import state.GolBoardImpl;
import state.GolCell;
import state.Player;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class GridUI {
    EncapsulatedGolState state;
    GridPane grid;
    private List<Integer> indices = List.of();

    private CountDownLatch countDownLatchAwaitClick;
    private CountDownLatch countDownLatchAwaitCreation;
    private boolean listening;
    TextField text;
    //public static String playerName = "default";

    public GridUI(EncapsulatedGolState state, CountDownLatch countDownLatchCreation) {
        this.state = state;
        countDownLatchAwaitClick = new CountDownLatch(1);
        countDownLatchAwaitCreation = countDownLatchCreation;

    }

    public void start(Stage stage) {
        this.grid = createGrid((GolBoardImpl) state.board(), state.playersToScore().keySet().stream().toList());
        //Create new stack Pane?
        //Add Grid to pane
        //Add info to pane
        //SetScene to pane
        Label label = new Label(state.playersToScore().keySet().toString());
        //Label label2 = new Label(state.playersToScore().values().toString()); //TODO: implement counting alive cells per player
        Label label3 = new Label("Number of evolutions: " + state.numberOfEvolution().toString());
        Label label4 = new Label("Current Player: " + state.currentPlayer().playerName());
        label4.setAlignment(Pos.BOTTOM_CENTER);

        HBox box1 = new HBox(2);
        box1.getChildren().addAll(label);
        box1.setAlignment(Pos.TOP_CENTER);
        //HBox box2 = new HBox(2);
        //box2.getChildren().addAll(label, label3, label4);
        HBox box3 = new HBox(2);
        box3.getChildren().addAll(label3);
        HBox box4 = new HBox(2);
        box4.getChildren().addAll(label4);


        stage.setTitle("GameOfLife2Players");

//        TableView table = new TableView();
//        final Label labelTitle = new Label("Address Book");
//        label.setFont(new Font("Arial", 20));
//
//        table.setEditable(true);
//
//        TableColumn category = new TableColumn("Category");
//        TableColumn stats = new TableColumn("Statistics");
//
//        table.getColumns().addAll(category, stats);
//
//        final VBox vbox = new VBox();
//        vbox.setSpacing(5);
//        vbox.setPadding(new Insets(10, 0, 0, 10));
//        vbox.getChildren().addAll(label, table);


        HBox box = new HBox(5);
        box.setPadding(new Insets(25, 5 , 5, 50));
        box.getChildren().addAll(box1, box3, box4);

        BorderPane border = new BorderPane();
        border.setCenter(grid);
        border.setRight(box);

        Scene scene = new Scene(border, 1200, 600);

        scene.getStylesheets().add("grid-with-borders.css");
        stage.setScene(scene);
        stage.show();
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

    void updateCreateContent(){

    }

    private GridPane setCellsFromBoardToGrid(GolBoardImpl board,GridPane grid, List<Player> playersList) {
        //add cells to grid
        for (int x = 0 ; x < board.getBoardWidth() ; x++) {
            for (int y = 0 ; y < board.getBoardHeight() ; y++) {
                Rectangle rectangle = new RectangleWithColorFromOccupyingPlayer(board.getCell(new Coordinates(x,y)), playersList);
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
        StackPane root = new StackPane(grid, text);
        Scene scene = new Scene(root, 600, 600);
        scene.getStylesheets().add("grid-with-borders.css");
        return scene;
    }

    public void setListening(boolean listening) {
        this.listening = listening;
        countDownLatchAwaitClick = new CountDownLatch(1);
    }
}


