package org.example.models.boardAndPieces;

import org.example.models.player.Player;

public class Mine implements Piece {
    int start;
    int holdForTurns;
    Behaviour behaviour;

    public Mine(int start) {
        this.start = start;
        this.holdForTurns = 2;
        behaviour = new HoldForTurns(holdForTurns);
    }

    @Override
    public int getStart() {
        return start;
    }

    @Override
    public int performAction(Player p) {
        int location = behaviour.execute(p);
        System.out.printf("and landed on mine at %d ", start);
        return location;
    }
}
