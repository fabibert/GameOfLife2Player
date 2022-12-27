package UI;

import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class GridPaneWithLines extends Application {
    
    private StackPane createCell(BooleanProperty cellSwitch) {

        StackPane cell = new StackPane();

        cell.setOnMouseClicked(e -> cellSwitch.set(! cellSwitch.get() ));

        Rectangle rectangle = new Rectangle(40,40, Color.RED);

        rectangle.visibleProperty().bind(cellSwitch);

        cell.getChildren().add(rectangle);
        cell.getStyleClass().add("cell");
        return cell;
    }

    private GridPane createGrid(BooleanProperty[][] board) {

        int numCols = board.length ;
        int numRows = board[0].length;
        
        GridPane grid = new GridPane();

        for (int x = 0 ; x < numCols ; x++) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setFillWidth(true);
            cc.setHgrow(Priority.ALWAYS);
            grid.getColumnConstraints().add(cc);
        }

        for (int y = 0 ; y < numRows ; y++) {
            RowConstraints rc = new RowConstraints();
            rc.setFillHeight(true);
            rc.setVgrow(Priority.ALWAYS);
            grid.getRowConstraints().add(rc);
        }

        for (int x = 0 ; x < numCols ; x++) {
            for (int y = 0 ; y < numRows ; y++) {
                grid.add(createCell(board[x][y]), x, y);
            }
        }

        grid.getStyleClass().add("grid");
        return grid;
    }

    @Override
    public void start(Stage primaryStage) {
        int numCols = 10;
        int numRows = 10 ;

        BooleanProperty[][] board = new BooleanProperty[numCols][numRows];
        for (int x = 0 ; x < numCols ; x++) {
            for (int y = 0 ; y < numRows ; y++) {
                board[x][y] = new SimpleBooleanProperty();
            }
        }

        GridPane grid = createGrid(board);

        StackPane root = new StackPane(grid);
        Scene scene = new Scene(root, 600, 600);
        scene.getStylesheets().add("./grid-with-borders.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }




    public static void main(String[] args) {
        launch(args);
    }
}

class MyLaunch {
    public static void main(String[] args){
        GridPaneWithLines.main(args);
    }
}






/*

import javafx.application.Application;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Board {

    private int[][] board_matrix;
    private int board_size;
    private int win_length;

    public Board(int board_size, int win_length) {
        this.board_matrix = new int[board_size][board_size];
        this.board_size = board_size;
        this.win_length = win_length;

        for (int i = 0; i < board_size; i++) {
            for (int j = 0; j < board_size; j++) {
                this.board_matrix[i][j] = 0;
            }
        }
    }


    public void make_move(int player, int x_pos, int y_pos) {
        if (player == 1) board_matrix[x_pos][y_pos] = 1;
        else board_matrix[x_pos][y_pos] = 2;
    }

    public class BoardGUI_ extends Application {

        private final int BOARD_SIZE = 15;

        public void start(Stage stage) {

            GridPane gameBoard = new GridPane();
            gameBoard.setPrefSize(755, 755);

            for (int i = 0; i < BOARD_SIZE; i++) {
                for (int j = 0; j < BOARD_SIZE; j++) {

                    Rectangle tile = new Rectangle(50, 50);
                    tile.setFill(Color.BURLYWOOD);
                    tile.setStroke(Color.BLACK);

                    Text text = new Text();
                    text.setFont(Font.font(40));
                    gameBoard.add(new StackPane(tile, text), j, i);

                    //GridPane.setRowIndex(tile, i);
                    //GridPane.setColumnIndex(tile, j);
                    //gameBoard.getChildren().addAll(tile, text);
                    tile.setOnMouseClicked(event -> drawMove(text));
                }
            }
        }

        public void drawMove(Text text) {
            text.setText("O");
            text.setFill(Color.BLACK);
        }


        public static void main(String args[]) {
            launch(args);
        }


    }
}

class MyLaunch {
    public static void main(String[] args){
        UI.Board.BoardGUI_.main(args);
    }
}


*/
