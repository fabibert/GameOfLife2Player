package State;

public interface StateBuilder<T extends State> {
    T createState(String player1, String player2);
}
