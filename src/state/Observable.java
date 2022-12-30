package state;


public interface Observable {

    void updateObservers();

    void addObserver(Observer observer);

    void removeObserver(Observer observer);


}
