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


