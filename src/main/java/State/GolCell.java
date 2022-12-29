package State;

import java.util.Optional;

public record GolCell(Optional<Player> player) implements Cell {

    public boolean isAlive(){
        return player.isPresent();
    }

    public Player getPlayer(){
        return player.get();
    }


}
