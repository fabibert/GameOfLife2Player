package UI.gridElements;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

public class BorderedGrid extends GridPane {
    private GridPane grid;

    //extract
    public BorderedGrid(GridPane grid, int numCols, int numRows) {
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
        this.grid = grid;
    }

    public GridPane getGrid() {
        return grid;
    }
}
