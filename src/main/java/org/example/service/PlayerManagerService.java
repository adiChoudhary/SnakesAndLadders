package org.example.service;

import org.example.configuration.ConfigurationFactory;
import org.example.models.boardAndPieces.BoardDimensions;
import org.example.models.player.Player;

import java.util.*;
import java.util.stream.Collectors;

public class PlayerManagerService {
    private final Map<Integer, List<Player>> playersMap;
    private final Deque<Player> playerQueue;
    private final BoardDimensions boardDimensions;

    public PlayerManagerService(List<Player> players) {
        playerQueue = new LinkedList<>();
        playersMap = new HashMap<>();
        players.forEach(e -> {
            addPlayerToMap(e);
            playerQueue.addLast(e);
        });
        boardDimensions = ConfigurationFactory.configuration.getBoardSize();
    }

    public void removePlayerFromMap(Player player) {
        List<Player> playerList = playersMap.getOrDefault(player.getLocation(), new ArrayList<>());
        playerList = playerList.stream().filter(e -> !e.getName().equals(player.getName())).collect(Collectors.toList());
        playersMap.put(player.getLocation(), playerList);
    }

    public void addPlayerToMap(Player player) {
        List<Player> temp = playersMap.getOrDefault(player.getLocation(), new ArrayList<>());
        temp.add(player);
        playersMap.put(player.getLocation(), temp);
    }

    public boolean arePlayersAtLocation(int location) {
        return !playersMap.getOrDefault(location, new ArrayList<>()).isEmpty();
    }

    public List<Player> getPlayersAtLocation(int location) {
        return playersMap.getOrDefault(location, new ArrayList<>());
    }

    public Player removeAndGetPlayerFromQueue() {
        return playerQueue.removeFirst();
    }

    public void addPlayerToQueue(Player p) {
        playerQueue.addLast(p);
    }

    public boolean isPlayerQueueEmpty() {
        return playerQueue.isEmpty();
    }

    public void performMovement(Player player, int finalLocation) {
        int curLocation = player.getLocation();
        if (curLocation != finalLocation) {
            this.removePlayerFromMap(player);
            if (finalLocation != 1 && finalLocation != (boardDimensions.getLength() * boardDimensions.getWidth()) && this.arePlayersAtLocation(finalLocation)) {
                // Move existing players on that location to 1
                List<Player> players = this.getPlayersAtLocation(finalLocation);
                String names = players.stream().map(Player::getName).collect(Collectors.joining(" "));
                System.out.println(names + " getting moved to one due to collision");
                players.forEach(this::removePlayerFromMap);
                players.forEach(e -> {
                    e.setLocation(1);
                    this.addPlayerToMap(e);
                });
            }
            // Move player to final location
            player.setLocation(finalLocation);
            this.addPlayerToMap(player);
        }
    }
}
