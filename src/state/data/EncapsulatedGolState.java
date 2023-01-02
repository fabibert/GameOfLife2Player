package state.data;

import state.board.GolBoard;
import state.data.Player;

import java.util.Map;
import java.util.Optional;

public record EncapsulatedGolState(Map<Player, Integer> playersToScore, GolBoard board, Integer numberOfEvolution, Player currentPlayer, Optional<Player> winner) { }
