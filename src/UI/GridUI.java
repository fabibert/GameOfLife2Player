package UI;

import UI.gridElements.BorderedGrid;
import UI.gridElements.RectangleWithColorFromOccupyingPlayer;
import UI.gridElements.StackPaneCell;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import state.EncapsulatedGolState;
import state.GolBoardImpl;
import state.Player;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import static javafx.scene.paint.Color.BLUE;
import static javafx.scene.paint.Color.RED;

public class GridUI {
    //EncapsulatedGolState state;
    GridPane grid;
    private List<Integer> indices = List.of();

    private CountDownLatch countDownLatchAwaitClick;
    private CountDownLatch countDownLatchAwaitCreation;
    private boolean listening;
    private Label playersToScore;
    private Label numberOfEvolutions;
    private Label currentPlayer;
    private Label instructions;

    Color color1 = BLUE;
    Color color2 = RED;

    TextField text;
    private Label winner;
    //public static String playerName = "default";

    public GridUI(CountDownLatch countDownLatchCreation) {
        countDownLatchAwaitClick = new CountDownLatch(1);
        countDownLatchAwaitCreation = countDownLatchCreation;

    }

    public void start(Stage stage, EncapsulatedGolState state) {
        stage.setTitle("GameOfLife2Players");
        this.grid = createGrid((GolBoardImpl) state.board(), state.playersToScore().keySet().stream().toList());
        Insets value = new Insets(50, 10, 50, 10);
        playersToScore = new Label(getPlayersToScoreText(state));
        playersToScore.setWrapText(true);
        playersToScore.setPadding(value);
        numberOfEvolutions = new Label(getEvolutionsText(state));
        numberOfEvolutions.setWrapText(true);
        numberOfEvolutions.setPadding(value);
        instructions = new Label("");
        instructions.setWrapText(true);
        instructions.setPadding(value);
        currentPlayer = new Label(getCurrentPlayerText(state));
        currentPlayer.setWrapText(true);
        currentPlayer.setPadding(value);

        //winner if empty
        //Winner is not decided yet
        String winnerText;
//        if(!state.winner().isPresent())
//            winnerText = "There has no winner been decided yet.";
//        else
//            winnerText = state.winner() + "has won the game!!!";
        winner = new Label(getWinnerText(state));
        winner.setWrapText(true);
        winner.setPadding(value);
        //TODO: problem this is not dynamically displayed

        VBox box = new VBox(2);
        box.getChildren().addAll(playersToScore, numberOfEvolutions, currentPlayer, instructions, winner);

        BorderPane border = new BorderPane();
        border.setCenter(grid);
        border.setRight(box);

        Scene scene = new Scene(border, 900, 600);

        scene.getStylesheets().add("grid-with-borders.css");
        stage.setScene(scene);
        stage.show();
        countDownLatchAwaitCreation.countDown();
    }

    private String getEvolutionsText(EncapsulatedGolState state) {
        return "Number of evolutions: " + state.numberOfEvolution();
    }

    private String getPlayersToScoreText(EncapsulatedGolState state) {
        return state.playersToScore()
                .entrySet()
                .stream()
                .map(e -> "Player: " + e.getKey().playerName() + " has " + e.getValue() + " " + "points.\n")
                .reduce("", (string1, string2) -> string2 + string1);
    }

    public void update( EncapsulatedGolState state){
        setCellsFromBoardToGrid((GolBoardImpl) state.board(), grid, state.playersToScore().keySet().stream().toList());
        playersToScore.setText(getPlayersToScoreText(state));
        numberOfEvolutions.setText(getEvolutionsText(state));
        currentPlayer.setText(getCurrentPlayerText(state));
        instructions.setText("");
    }

    private String getCurrentPlayerText(EncapsulatedGolState state) {
        //if winner isPresent return "None"
        return "Current player is: " + state.currentPlayer().playerName();
    }

    private String getWinnerText(EncapsulatedGolState state) {
        //if winner isPresent return "None"
        return "Winner is: " + state.winner().toString();
    }

    private GridPane createGrid(GolBoardImpl board, List<Player> playersList) {
        GridPane grid = new BorderedGrid(board.getBoardWidth(), board.getBoardHeight());
        setCellsFromBoardToGrid(board, grid, playersList);
        grid.getStyleClass().add("grid");
        return grid;
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

    public void setInstructions(boolean isCreation){
        if(isCreation){
            instructions.setText("Please click a location to create a new cell");
        } else {
            instructions.setText("Please click on an opponents cell to delete it");
        }
    }

    public void setListening(boolean listening) {
        this.listening = listening;
        countDownLatchAwaitClick = new CountDownLatch(1);
    }
}


