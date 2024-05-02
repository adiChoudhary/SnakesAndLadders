package org.example.models.player;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class HoldForTurns implements Behaviour {
    private final Player player;

    @Override
    public void execute() {
        System.out.printf("%s waiting for %d turns due to landing on mine\n", player.getName(), player.getHoldTillTurn());
        int temp = player.getHoldTillTurn();
        temp--;
        player.setHoldTillTurn(temp);
    }
}
