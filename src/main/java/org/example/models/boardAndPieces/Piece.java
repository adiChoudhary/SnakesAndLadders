package org.example.models.boardAndPieces;

import org.example.models.player.Player;

public interface Piece {
    int getStart();

    int performAction(Player p);
}
