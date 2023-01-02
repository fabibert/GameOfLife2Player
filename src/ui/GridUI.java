package ui;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import state.board.GolBoardImpl;
import state.data.Coordinates;
import state.data.EncapsulatedGolState;
import state.data.GolCell;
import state.data.Player;
import ui.gridElements.BorderedGrid;
import ui.gridElements.RectangleWithColorFromOccupyingPlayer;
import ui.gridElements.StackPaneCell;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;

public class GridUI {
    private final Map<String, Color> playerToColor;
    GridPane grid;
    private List<Integer> indices = List.of();
    private CountDownLatch countDownLatchAwaitClick;
    private final CountDownLatch countDownLatchAwaitCreation;
    private boolean listening;
    private Label playersToScore;
    private Label numberOfEvolutions;
    private Label currentPlayer;
    private Label instructions;
    private Label winner;


    public GridUI(CountDownLatch countDownLatchCreation, Map<String, Color> playerToColor) {
        countDownLatchAwaitClick = new CountDownLatch(1);
        countDownLatchAwaitCreation = countDownLatchCreation;
        this.playerToColor = playerToColor;

    }

    public void start(Stage stage, EncapsulatedGolState state) {
        stage.setTitle("GameOfLife2Players");
        this.grid = createGrid((GolBoardImpl) state.board(), state.playersToScore().keySet().stream().toList());
        Insets value = new Insets(30, 10, 30, 10);
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
        winner = new Label(getWinnerText(state));
        winner.setWrapText(true);
        winner.setPadding(value);

        VBox box = new VBox(2);
        box.setMaxWidth(200);
        box.setMinWidth(200);
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
                .sorted(Comparator.comparing(entry -> entry.getKey().playerName()))
                .map(e -> "Player: " + e.getKey().playerName() + " has " + e.getValue() + " " + "points.\n")
                .reduce("", (string1, string2) -> string1 + string2);
    }

    public void update( EncapsulatedGolState state){
        setCellsFromBoardToGrid((GolBoardImpl) state.board(), grid, state.playersToScore().keySet().stream().toList());
        playersToScore.setText(getPlayersToScoreText(state));
        numberOfEvolutions.setText(getEvolutionsText(state));
        currentPlayer.setText(getCurrentPlayerText(state));
        instructions.setText("");
        winner.setText(getWinnerText(state));
    }

    private String getCurrentPlayerText(EncapsulatedGolState state) {
        //if winner isPresent return "None"
        return "Current player is: " + state.currentPlayer().playerName();
    }

    private String getWinnerText(EncapsulatedGolState state) {
        return state.winner().map(player -> "Congratulations: player " + player.playerName() + " won!").orElse("");
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
                GolCell cell = board.getCell(new Coordinates(x, y));
                Optional<Color> color = cell.player().map(p -> playerToColor.get(p.playerName()));
                Rectangle rectangle = new RectangleWithColorFromOccupyingPlayer(color);
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


