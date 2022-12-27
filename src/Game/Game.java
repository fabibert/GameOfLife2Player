package Game;

import UI.UI;
import State.State;
import State.StateBuilder;

import java.util.Objects;

public abstract class Game<T extends UI, I extends State>{

    private T ui;
    private StateBuilder<I> stateBuilder;
    private I state;

    //GameInitializer<T> initializer;

    public Game(T ui, StateBuilder<I> stateBuilder){
        this.ui = ui;
        this.stateBuilder = stateBuilder;
    }

    public void start(){
        String name1 = ui.requestPlayerName();
        String name2 = ui.requestPlayerName();
        while (Objects.equals(name1, name2)){
            name2 = ui.requestPlayerName();
        }
        state = stateBuilder.createState(name1, name2);
        GameLogic gameLogic = createGameLogic(ui, state);
        gameLogic.run();
    }

    protected abstract GameLogic createGameLogic(T ui, I state);

}
