package State;

import java.util.Optional;

public record GolCell(Optional<Player> player) implements Cell {

    boolean isAlive(){
        return player.isPresent();
    }

    Player getPlayer(){
        return player.get();
    }
}
