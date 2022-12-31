package Game;

import UI.Coordinates;
import UI.GolUI;
import state.GolBoard;
import state.GolState;

public class GolLogic implements GameLogic {
    private final Regeneration regeneration;
    GolState state;
    GolUI ui;
    Verifier verifier;

    public GolLogic(GolUI ui, GolState state){
        this.ui = ui;
        this.state = state;
        this.verifier = new VerifierImpl(state);
        this.regeneration = new RegenerationImpl(state);
    }

    public void run(){
        state.updateObservers();
        boolean winner =false;
        while(!winner) {
            deletion();
            creation();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            regeneration();
            //winner = checkForWinner();
        }
    }

    private void regeneration() {
        state.increaseNumberOfGenerations();
        this.state.setBoard(regeneration.regenerationBoard());
        //TODO: execute in the regeneration class
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

    private void creation() {
        Coordinates creationCoordinates = ui.requestPlayerCellCreation();
        while(!verifier.verifyCellCreation(creationCoordinates)){
            creationCoordinates = ui.requestPlayerCellCreation();
        }
        GolBoard board = state.getBoard();
        board.setCellToPlayer(creationCoordinates, state.getCurrentPlayer());
        state.setBoard(board);
    }


}
