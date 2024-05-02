package org.example.models.player;

import lombok.AllArgsConstructor;
import org.example.models.boardAndPieces.Board;
import org.example.service.PlayerManagerService;

@AllArgsConstructor
public class PlayerBehaviorFactory implements BehaviourFactory {
    private final Player player;
    private final PlayerManagerService playerManagerService;
    private final Board board;
    private int diceRoll;

    @Override
    public Behaviour createBehaviour() {
        if (player.getHoldTillTurn() > 0) {
            return new HoldForTurns(player);
        }
        return new GoToPos(diceRoll, player, board, playerManagerService);
    }
}
