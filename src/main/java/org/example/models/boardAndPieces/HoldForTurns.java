package org.example.models.boardAndPieces;

import lombok.AllArgsConstructor;
import org.example.models.player.Player;

@AllArgsConstructor
public class HoldForTurns implements Behaviour {
    int holdForTurns;

    @Override
    public int execute(Player e) {
        e.setHoldTillTurn(holdForTurns);
        return e.getLocation();
    }
}
