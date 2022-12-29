package State;


public interface Observable {

    void updateObservers(EncapsulatedGolState state);

    void addObserver(Observer observer);

    void removeObserver(Observer observer);


}
