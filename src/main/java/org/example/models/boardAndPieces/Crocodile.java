package org.example.models.boardAndPieces;

import org.example.models.player.Player;

public class Crocodile implements Piece {
    int start;

    Behaviour behaviour;

    public Crocodile(int start) {
        this.start = start;
        int temp = start - 5;
        behaviour = new GoToPos(Math.max(temp, 1));
    }

    @Override
    public int getStart() {
        return start;
    }

    @Override
    public int performAction(Player p) {
        int location = behaviour.execute(p);
        System.out.printf("and bitten by crocodile at %d ", start);
        return location;
    }
}
