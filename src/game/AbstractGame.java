package game;

import game.logic.GameLogic;
import ui.UI;
import state.State;
import state.stateBuilder.StateBuilder;

import java.util.Objects;

public abstract class AbstractGame<U extends UI, S extends State> implements Game{

    private final U ui;
    private final StateBuilder<S> stateBuilder;

    public AbstractGame(U ui, StateBuilder<S> stateBuilder){
        this.ui = ui;
        this.stateBuilder = stateBuilder;
    }

    @Override
    public void start(){
        String name1 = ui.requestPlayerName();
        String name2 = ui.requestPlayerName();
        while (Objects.equals(name1, name2)){
            name2 = ui.requestPlayerName();
        }
        S state = stateBuilder.createState(name1, name2);
        initObserverPattern(ui, state);
        GameLogic gameLogic = createGameLogic(ui, state);
        gameLogic.run();
    }

    protected abstract void initObserverPattern(U ui, S state);

    protected abstract GameLogic createGameLogic(U ui, S state);

}
