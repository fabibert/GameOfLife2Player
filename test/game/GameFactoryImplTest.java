package game;

import game.factory.GameFactory;
import game.factory.GameFactoryImpl;
import ui.GolUI;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import state.stateBuilder.GolStateBuilder;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.MockitoAnnotations.openMocks;

class GameFactoryImplTest {
    private @Mock Stage stage;
    //private @Mock GameFactoryImpl factory;
    private @Mock GolUI ui;
    private @Mock GolStateBuilder builder;


    private GameFactory factory;


    @BeforeEach
    public void setUp(){
        openMocks(this);
        factory = new GameFactoryImpl();
    }


    @Test
    public void testFactoryCreatesGOLGame(){
        assertTrue(factory.createGOLGame(stage) instanceof GolGame);
    }
}