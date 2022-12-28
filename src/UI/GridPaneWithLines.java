package UI;

import State.GolBoardImpl;
import State.GolCell;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class GridPaneWithLines extends Application {

    //replace boolean property with red blue and white
    private StackPane createCell(GolCell cellSwitch, int player) {

        StackPane cell = new StackPane();

        //if not yet used creation clicks
        //cell.setOnMouseClicked(e -> cellSwitch.set(!cellSwitch.get()));

        //if no yet used deletion clicks

        Rectangle rectangle;
        //color depending on player
        if (cellSwitch.isAlive()) {
            if(cellSwitch.getPlayer().playerName() == "Fabio")
                rectangle = new Rectangle(40, 40, Color.BLUE);
            else
                rectangle = new Rectangle(40, 40, Color.RED);
        }
        else{
            rectangle = new Rectangle(40, 40, Color.WHITE);
        }

        //rectangle.visibleProperty().bind(cellSwitch); //with cell switch set visible or not

        cell.getChildren().add(rectangle);
        cell.getStyleClass().add("cell");

        //also display cells which are alive

        //register which cell is clicked


        return cell;
    }

    //GridPane gird which hold StackPane cells
    private GridPane createGrid(BooleanProperty[][] board, int player) {

        GolBoardImpl board1 = new GolBoardImpl();
        //set cell
        //get cell
        board1.setCell(1,1, "Fabio");
        board1.setCell(2,2, "Joe");
        System.out.println(board1.getCell(1,1));
        //board length and size will be given by board
        //int numCols = board1.length ; //use length getter
        //int numRows = board1[0].length;

        int numCols = 10;
        int numRows = 10;
        
        GridPane grid = new GridPane();

        //adjust scale to zooming
        for (int x = 0 ; x < numCols ; x++) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setFillWidth(true);
            cc.setHgrow(Priority.ALWAYS);
            grid.getColumnConstraints().add(cc); //put constraints to grid Columns
        }

        for (int y = 0 ; y < numRows ; y++) {
            RowConstraints rc = new RowConstraints();
            rc.setFillHeight(true);
            rc.setVgrow(Priority.ALWAYS);
            grid.getRowConstraints().add(rc); //put constraints to grid Rows
        }

        //add cells to grid
        for (int x = 0 ; x < numCols ; x++) {
            for (int y = 0 ; y < numRows ; y++) {
                grid.add(createCell(board1.getCell(x,y), player), x, y);
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

        GridPane grid = createGrid(board, 1);

        //set cell;
        //board[x][y]

        StackPane root = new StackPane(grid);
        Scene scene = new Scene(root, 600, 600);
        scene.getStylesheets().add("grid-with-borders.css");
        primaryStage.setScene(scene);
        primaryStage.show();

        grid.addEventHandler(MouseEvent.MOUSE_CLICKED, e ->{
            double x = e.getX();
            double y = e.getY();
            //And if applicable
            System.out.println("x: " + x + " y: " + y);
        });

        grid.addEventHandler(MouseEvent.MOUSE_CLICKED, e ->{
            Node clickedNode = e.getPickResult().getIntersectedNode();
            Integer colIndex = GridPane.getColumnIndex(clickedNode);
            Integer rowIndex = GridPane.getRowIndex(clickedNode);
            System.out.println((colIndex)+ ":" + (rowIndex));
        });
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


