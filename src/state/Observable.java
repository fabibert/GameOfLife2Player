package state;


import ui.Observer;

public interface Observable {

    void updateObservers();

    void addObserver(Observer observer);

    void removeObserver(Observer observer);
}
