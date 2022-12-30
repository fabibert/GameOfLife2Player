package state;

public interface StateBuilder<S extends State> {
    S createState(String player1, String player2);
}
