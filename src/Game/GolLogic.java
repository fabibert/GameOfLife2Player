package Game;

import UI.Coordinates;
import UI.GolUI;
import state.GolBoard;
import state.GolState;

public class GolLogic implements GameLogic {
    GolState state;
    GolUI ui;
    Verifier verifier;
    Regeneration reg;

    public GolLogic(GolUI ui, GolState state){
        this.ui = ui;
        this.state = state;
        this.verifier = new VerifierImpl(state);
    }

    public void run(){
        while(true){
            state.updateObservers();

            deletion();
            creation();
            regeneration();

        }
    }

    private void regeneration() {
        state.increaseNumberOfGenerations();
        //TODO: execute in the regeneration class
    }

    private void creation() {
        Coordinates creationCoordinates = ui.requestPlayerCellCreation();
        while(!verifier.verifyCellCreation(creationCoordinates)){
            creationCoordinates = ui.requestPlayerCellCreation();
        }
        GolBoard board = state.getBoard();
        board.setCellToPlayer(creationCoordinates, state.getCurrentPlayer());
        state.setBoard(board);
    }

    private void deletion() {
        Coordinates deletionCoordinates = ui.requestPlayerCellDeletion();
        while(!verifier.verifyCellDeletion(deletionCoordinates)){
            deletionCoordinates = ui.requestPlayerCellDeletion();
        }
        GolBoard board = state.getBoard();
        board.setCellEmpty(deletionCoordinates);
        state.setBoard(board);
    }
}
