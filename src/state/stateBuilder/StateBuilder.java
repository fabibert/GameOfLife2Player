package state.stateBuilder;

import state.State;

public interface StateBuilder<S extends State> {
    S createState(String player1, String player2);
}
