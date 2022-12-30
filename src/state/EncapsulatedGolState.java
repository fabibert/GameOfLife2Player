package state;

import java.util.Map;

public record EncapsulatedGolState(Map<Player, Integer> playersToScore, GolBoard board, Integer numberOfEvolution, Player currentPlayer) { }
