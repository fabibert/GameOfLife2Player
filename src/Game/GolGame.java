package Game;

import State.GolState;
import State.StateBuilder;
import UI.GolUI;

public class GolGame extends Game<GolUI, GolState>{

    public GolGame(GolUI ui, StateBuilder<GolState> golStateBuilder) {
        super(ui, golStateBuilder);
    }
    //StateBuilder<T> builder;

    @Override
    public void start() {

        super.start();
    }

    @Override
    protected void initObserverPattern(GolUI ui, GolState state){
        state.addObserver(ui);
    }

    @Override
    protected GameLogic createGameLogic(GolUI ui, GolState state) {
        return new GolLogic(ui, state);
    }
}
