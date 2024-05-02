package org.example.models.player;

import lombok.AllArgsConstructor;
import org.example.models.boardAndPieces.Board;
import org.example.service.PlayerManagerService;

@AllArgsConstructor
public class GoToPos implements Behaviour {
    private final int diceRoll;
    private final Player player;
    private final Board board;
    private final PlayerManagerService playerManagerService;

    @Override
    public void execute() {
        System.out.printf("%s rolled a %d ", player.getName(), diceRoll);
        int finalLocation = getPlayerFinalLocation(player.getLocation() + diceRoll, player);
        System.out.printf("and moves from %d -> %d\n", player.getLocation(), finalLocation);
        playerManagerService.performMovement(player, finalLocation);
    }

    private int getPlayerFinalLocation(int location, Player player) {
        if (location >= board.getSize()) {
            return board.getSize();
        }
        while (board.getPieceMap().containsKey(location)) {
            location = board.getPieceMap().get(location).performAction(player);
        }
        return location;
    }
}
