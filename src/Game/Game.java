package Game;

import UI.UI;
import state.State;
import state.StateBuilder;

import java.util.Objects;

public abstract class Game<U extends UI, S extends State>{

    private U ui;
    private StateBuilder<S> stateBuilder;
    private S state;

    public Game(U ui, StateBuilder<S> stateBuilder){
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
        initObserverPattern(ui, state);
        GameLogic gameLogic = createGameLogic(ui, state);
        gameLogic.run();
    }

    protected abstract void initObserverPattern(U ui, S state);

    protected abstract GameLogic createGameLogic(U ui, S state);

}
