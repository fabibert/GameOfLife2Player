package game;

import ui.Coordinates;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import state.GolBoard;
import state.GolCell;
import state.GolState;
import state.Player;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class VerifierImplTest {

    @Mock GolState state;
    @Mock GolBoard board;
    Coordinates coordinates;
    Player player1;
    Player player2;
    GolCell cell;
    Verifier verifier;

    @BeforeEach
    public void setUp(){
        openMocks(this);
        player1 = new Player("NAME_1");
        player2 = new Player("NAME_2");
        coordinates = new Coordinates(1, 4);
        when(state.getCurrentPlayer()).thenReturn(player1);
        when(state.getBoard()).thenReturn(board);
        verifier = new VerifierImpl(state);
    }

    @Test
    public void testCellDeletionWillReturnFalseIfTheCellIsAlreadyDead(){
        cell = new GolCell(Optional.empty());
        when(board.getCell(coordinates)).thenReturn(cell);
        assertFalse(verifier.verifyCellDeletion(coordinates));
    }

    @Test
    public void testCellDeletionWillReturnFalseIfTheCellIsOwnedByThisPlayer(){
        cell = new GolCell(Optional.of(player1));
        when(board.getCell(coordinates)).thenReturn(cell);
        assertFalse(verifier.verifyCellDeletion(coordinates));
    }

    @Test
    public void testCellDeletionWillReturnTrueIfTheCellIsOwnedByOtherPlayer(){
        cell = new GolCell(Optional.of(player2));
        when(board.getCell(coordinates)).thenReturn(cell);
        assertTrue(verifier.verifyCellDeletion(coordinates));
    }

    @Test
    public void testCellCreationWillReturnFalseIfTheCellIsAliveAndPlayer1(){
        cell = new GolCell(Optional.of(player1));
        when(board.getCell(coordinates)).thenReturn(cell);
        assertFalse(verifier.verifyCellCreation(coordinates));
    }

    @Test
    public void testCellCreationWillReturnFalseIfTheCellIsAliveAndPlayer2(){
        cell = new GolCell(Optional.of(player2));
        when(board.getCell(coordinates)).thenReturn(cell);
        assertFalse(verifier.verifyCellCreation(coordinates));
    }

    @Test
    public void testCellCreationWillReturnTrueIfTheCellIsDead(){
        cell = new GolCell(Optional.empty());
        when(board.getCell(coordinates)).thenReturn(cell);
        assertTrue(verifier.verifyCellCreation(coordinates));
    }

}