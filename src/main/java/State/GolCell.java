package State;

import java.util.Optional;

public class GolCell {

    private final Optional<Player> owner;

    public GolCell(){
        owner = Optional.empty();
    }

    public GolCell(Player player){
        owner = Optional.of(player);
    }

    public boolean isAlive(){
        return owner.isPresent();
    }

    public Optional<Player> getOwner(){
        return owner;
    }

}
