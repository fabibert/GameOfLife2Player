package Game;

import UI.Coordinates;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import state.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.MockitoAnnotations.openMocks;

class RegenerationImplTest {

    Coordinates coordinates;
    Player player1;
    Player player2;
    GolCell cell;
    Regeneration regeneration;

    GolBoardImpl testInitialBoard;

    GolBoardImpl testResultBoard;
    GolState state;



    @BeforeEach
    public void setUp(){
        openMocks(this);
        player1 = new Player("Red");
        player2 = new Player("Blue");

        this.testInitialBoard = new GolBoardImpl(10,10);

        setupTestInitialBoard(testInitialBoard);

        this.state = new GolStateImpl(player1, player2, testInitialBoard);
        regeneration = new RegenerationImpl(state);

        this.testResultBoard = new GolBoardImpl(10,10);

        setupTestResultBoard(testResultBoard);
    }

    @Test
    void testRegenerationBoard() {
        this.state.setBoard(regeneration.regenerationBoard());
        compareBoards();
    }


    void compareBoards(){
        for (int y = 0; y < state.getBoard().getBoardHeight(); y++) {
            for (int x = 0; x < state.getBoard().getBoardWidth(); x++) {
                Coordinates coordinates = new Coordinates(x, y);
                assertTrue(state.getBoard().getCell(coordinates).equals(this.testResultBoard.getCell(coordinates)));
            }
        }
    }

    private void setupTestInitialBoard(GolBoardImpl testInitialBoard) {
        testInitialBoard.setCellToPlayer(new Coordinates(1,1), player1);
        testInitialBoard.setCellToPlayer(new Coordinates(2,1), player2);
        testInitialBoard.setCellToPlayer(new Coordinates(3,1), player1);

        testInitialBoard.setCellToPlayer(new Coordinates(6,1), player2);
        testInitialBoard.setCellToPlayer(new Coordinates(8,1), player1);
        testInitialBoard.setCellToPlayer(new Coordinates(7,3), player2);

        testInitialBoard.setCellToPlayer(new Coordinates(7,7), player2);
        testInitialBoard.setCellToPlayer(new Coordinates(8,7), player2);
        testInitialBoard.setCellToPlayer(new Coordinates(7,8), player2);
        testInitialBoard.setCellToPlayer(new Coordinates(8,8), player2);


        testInitialBoard.setCellToPlayer(new Coordinates(0,7), player1);
        testInitialBoard.setCellToPlayer(new Coordinates(1,7), player1);
        testInitialBoard.setCellToPlayer(new Coordinates(2,7), player1);

        testInitialBoard.setCellToPlayer(new Coordinates(0,8), player2);
        testInitialBoard.setCellToPlayer(new Coordinates(1,8), player2);
        testInitialBoard.setCellToPlayer(new Coordinates(2,8), player2);

        testInitialBoard.setCellToPlayer(new Coordinates(0,9), player1);
        testInitialBoard.setCellToPlayer(new Coordinates(1,9), player1);
        testInitialBoard.setCellToPlayer(new Coordinates(2,9), player1);
    }

    private void setupTestResultBoard(GolBoardImpl testResultBoard) {
        testResultBoard.setCellToPlayer(new Coordinates(2,0), player1);
        testResultBoard.setCellToPlayer(new Coordinates(2,1), player2);
        testResultBoard.setCellToPlayer(new Coordinates(2,2), player1);

        testResultBoard.setCellToPlayer(new Coordinates(7,2), player2);

        testResultBoard.setCellToPlayer(new Coordinates(1,6), player1);
        testResultBoard.setCellToPlayer(new Coordinates(0,7), player1);
        testResultBoard.setCellToPlayer(new Coordinates(2,7), player1);
        testResultBoard.setCellToPlayer(new Coordinates(3,8), player1);
        testResultBoard.setCellToPlayer(new Coordinates(0,9), player1);
        testResultBoard.setCellToPlayer(new Coordinates(2,9), player1);

        testResultBoard.setCellToPlayer(new Coordinates(7,7), player2);
        testResultBoard.setCellToPlayer(new Coordinates(8,7), player2);
        testResultBoard.setCellToPlayer(new Coordinates(7,8), player2);
        testResultBoard.setCellToPlayer(new Coordinates(8,8), player2);
    }
}