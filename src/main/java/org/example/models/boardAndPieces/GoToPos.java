package org.example.models.boardAndPieces;

import lombok.AllArgsConstructor;
import org.example.models.player.Player;

@AllArgsConstructor
public class GoToPos implements Behaviour {
    int pos;

    @Override
    public int execute(Player e) {
        return pos;
    }
}
