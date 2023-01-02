package ui.gridElements;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

public class BorderedGrid extends GridPane {
    public BorderedGrid(int numCols, int numRows) {
        super();
        for (int x = 0 ; x < numCols ; x++) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setFillWidth(true);
            cc.setHgrow(Priority.ALWAYS);
            this.getColumnConstraints().add(cc); //put constraints to grid Columns
        }

        for (int y = 0 ; y < numRows ; y++) {
            RowConstraints rc = new RowConstraints();
            rc.setFillHeight(true);
            rc.setVgrow(Priority.ALWAYS);
            this.getRowConstraints().add(rc); //put constraints to grid Rows
        }
    }
}
