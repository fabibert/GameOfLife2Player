package Game;

import UI.UI;

public abstract class Game<T extends UI>{

    T ui;

    //GameInitializer<T> initializer;

    public Game(T ui){
        this.ui = ui;
    }

    abstract void initGame();

    abstract void start();
}
