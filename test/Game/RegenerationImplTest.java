package Game;

import UI.Coordinates;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import state.*;

import static org.mockito.MockitoAnnotations.openMocks;

class RegenerationImplTest {

    @Mock
    GolState state;
    @Mock
    GolBoard board;
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
        GolBoardImpl board = new GolBoardImpl(10,10);
        board.setCellToPlayer(new Coordinates(8,8), player1);
    }

    @Test
    void regenerationBoard() {

    }
}