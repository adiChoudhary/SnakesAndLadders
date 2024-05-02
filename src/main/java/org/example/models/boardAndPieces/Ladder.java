package org.example.models.boardAndPieces;

import org.example.models.player.Player;

public class Ladder implements Piece {
    int start;
    int end;
    Behaviour behaviour;

    public Ladder(int start, int end) {
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
        System.out.printf("and climbed the ladder at %d ", start);
        return location;
    }
}
