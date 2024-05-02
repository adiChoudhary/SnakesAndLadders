package org.example.service;


import org.example.configuration.Configuration;
import org.example.configuration.ConfigurationFactory;
import org.example.enums.MovementStrategy;
import org.example.models.player.Behaviour;
import org.example.models.player.Player;
import org.example.models.boardAndPieces.Board;
import org.example.models.player.PlayerBehaviorFactory;

import java.util.*;

public class SnakeAndLadderDriver {
    private final Board board;
    private final PlayerManagerService playerManagerService;
    private int turn;
    private final boolean PLAY_TILL_ONE_WINNER;
    private final int dices;
    private final MovementStrategy ms;

    public SnakeAndLadderDriver(Board board, List<Player> players) {
        this.board = board;
        playerManagerService = new PlayerManagerService(players);
        turn = 0;
        Configuration configuration = ConfigurationFactory.configuration;
        PLAY_TILL_ONE_WINNER = configuration.isPlayTillOneWinner();
        dices = configuration.getDies();
        ms = configuration.getMs();
    }

    public void startGame() {
        boolean flag;
        do {
            int diceRoll = ms.diceRollResult(dices);
            flag = helper(diceRoll);
        } while (flag);
    }

    public boolean testGame(Scanner sc) {
        return helper(sc.nextInt());
    }

    private boolean helper(int diceRoll) {
        turn++;
        if (playerManagerService.isPlayerQueueEmpty()) {
            System.out.println("Game Ended");
            return false;
        }
        Player player = playerManagerService.removeAndGetPlayerFromQueue();
        System.out.printf("\nTURN %d\n", turn);
        System.out.println(player.getName() + ":");
        PlayerBehaviorFactory playerBehaviorFactory = new PlayerBehaviorFactory(player, playerManagerService, board, diceRoll);
        Behaviour playerBehaviour = playerBehaviorFactory.createBehaviour();
        playerBehaviour.execute();
        if (player.getLocation() == board.getSize() && PLAY_TILL_ONE_WINNER) {
            System.out.printf("%s is the winner\n", player.getName());
            return false;
        }
        if (player.getLocation() != board.getSize()) {
            playerManagerService.addPlayerToQueue(player);
        } else {
            System.out.printf("%s completes game at %d turn\n", player.getName(), turn);
        }
        return true;
    }
}
