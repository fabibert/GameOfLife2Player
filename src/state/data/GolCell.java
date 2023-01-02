package state.data;

import java.util.Optional;

public record GolCell(Optional<Player> player) {

    public boolean isAlive(){
        return player.isPresent();
    }
}
