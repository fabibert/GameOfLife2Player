package game;

import game.logic.GameLogic;
import game.logic.GolLogic;
import state.GolState;
import state.stateBuilder.StateBuilder;
import ui.GolUI;

public class GolGame extends AbstractGame<GolUI, GolState>{

    public GolGame(GolUI ui, StateBuilder<GolState> golStateBuilder) {
        super(ui, golStateBuilder);
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
