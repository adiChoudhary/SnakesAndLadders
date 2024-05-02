package org.example.models.boardAndPieces;

import org.example.models.player.Player;

public class Snake implements Piece {
    int start;
    int end;
    Behaviour behaviour;

    public Snake(int start, int end) {
        this.start = start;
        this.end = end;
        behaviour = new GoToPos(end);
    }

    @Override
    public int getStart() {
        return start;
    }

    @Override
    public int performAction(Player p) {
        int location = behaviour.execute(p);
        System.out.printf("and bitten by snake at %d ", start);
        return location;
    }
}
