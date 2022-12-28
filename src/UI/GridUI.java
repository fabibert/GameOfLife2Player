package UI;

import State.GolBoardImpl;
import State.GolCell;
import State.Player;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.List;

public class GridUI extends Application {

    //replace boolean property with red blue and white
    private StackPane createCell(GolCell cellSwitch, List playersList) {
        StackPane cell = new StackPane();

        Rectangle rectangle;
        //color depending on player //implemented only for 2 players
        if (cellSwitch.isAlive()) {
            if(cellSwitch.getPlayer().playerName() == playersList.get(0))
                rectangle = new Rectangle(40, 40, Color.BLUE);
            else
                rectangle = new Rectangle(40, 40, Color.RED);
        }
        else{
            rectangle = new Rectangle(40, 40, Color.WHITE);
        }

        cell.getChildren().add(rectangle);
        cell.getStyleClass().add("cell");
        return cell;
    }

    private GridPane createGrid(GolBoardImpl board, List<Player> playersList) {
        int numCols = board.getBoardHeight();
        int numRows = board.getBoardWidth();
        
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
                grid.add(createCell(board.getCell(x,y), playersList), x, y);
            }
        }

        grid.getStyleClass().add("grid");
        return grid;
    }

    @Override
    public void start(Stage primaryStage) {
        //later pass GolBoardImpl and playersList
        List<Player> playersList = List.of(new Player("Fabio"), new Player("Joe"));

        GolBoardImpl board1 = new GolBoardImpl(10,10);
        board1.setCellToPlayer(1,1, playersList.get(0).playerName());
        board1.setCellToPlayer(2,2, playersList.get(1).playerName());
        System.out.println(board1.getCell(1,1));

        GridPane grid = createGrid(board1, playersList);

        StackPane root = new StackPane(grid);
        Scene scene = new Scene(root, 600, 600);
        scene.getStylesheets().add("grid-with-borders.css");
        primaryStage.setScene(scene);
        primaryStage.show();

        LocateClick(grid);
    }

    public void LocateClick(GridPane grid){
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
        GridUI.main(args);
    }
}


