package Game;

public class GameFactoryImpl implements GameFactory {
    @Override
    public Game createGOLGame() {
        //Game golGame = new GolGame(new GolUI(), new StateBuilder<GolState>());
        Game golGame = null;
        golGame.start();
        return golGame;
    }
}
