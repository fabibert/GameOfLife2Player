package State;

public interface GolState extends State {

    Player getCurrentPlayer();

    int getNumberOfGeneration();

    GolBoard getBoard();

    void increaseNumberOfGenerations();
}
