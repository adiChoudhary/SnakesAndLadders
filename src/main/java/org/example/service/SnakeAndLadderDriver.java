package org.example.service;

import org.example.enums.MovementStrategy;
import org.example.models.Board;
import org.example.models.Players;

import java.util.*;
import java.util.stream.Collectors;

public class SnakeAndLadderDriver {
    private Board board;
    private Map<Integer, List<Players>> playersMap;
    private Deque<Players> playersQueue;
    private int turn = 0;
    private static final boolean PLAY_TILL_ONE_WINNER = true;
    private int dices = 2;
    private final MovementStrategy ms = MovementStrategy.SUM;

    public SnakeAndLadderDriver(Board board, List<Players> players){
        this.board = board;
        playersMap = new HashMap<>();
        playersQueue = new LinkedList<>();
        players.forEach(e -> {
            List<Players> temp = playersMap.getOrDefault(e.getLocation(), new ArrayList<>());
            temp.add(e);
            playersMap.put(e.getLocation(), temp);
            playersQueue.addLast(e);
        });
    }

    public void startGame(){
        do {
            turn++;
            Players player = playersQueue.removeFirst();
            if(player.getHoldTillTurn() > 0){
                System.out.printf("%s waiting for %d turns due to landing on mine\n", player.getName(), player.getHoldTillTurn());
                int temp = player.getHoldTillTurn();
                temp--;
                player.setHoldTillTurn(temp);
                playersQueue.addLast(player);
                continue;
            }
            int diceRoll = ms.diceRollResult(dices);
            System.out.printf("%s rolled a %d ", player.getName(), diceRoll);
            int finalLocation = getPlayerFinalLocation(player.getLocation() + diceRoll, player);
            System.out.printf("and moves from %d -> %d\n", player.getLocation(), finalLocation);
            if(finalLocation == board.getSize() && PLAY_TILL_ONE_WINNER){
                System.out.printf("%s is the winner\n", player.getName());
                break;
            }
            performMovement(player, finalLocation);
            if(finalLocation != board.getSize()){
                playersQueue.addLast(player);
            }
        } while (!playersQueue.isEmpty());
    }

    public boolean testGame(Scanner sc){
        turn++;
        if(playersQueue.isEmpty()){
            System.out.println("INVALID turn performed");
            return false;
        }
        Players player = playersQueue.removeFirst();
        System.out.println(player.getName() + ":");
        int diceRoll = sc.nextInt();
        System.out.printf("\nTURN %d\n", turn);
        if(player.getHoldTillTurn() > 0){
            System.out.printf("%s waiting for %d turns due to landing on mine\n", player.getName(), player.getHoldTillTurn());
            int temp = player.getHoldTillTurn();
            temp--;
            player.setHoldTillTurn(temp);
            playersQueue.addLast(player);
            return true;
        }
        System.out.printf("%s rolled a %d ", player.getName(), diceRoll);
        int finalLocation = getPlayerFinalLocation(player.getLocation() + diceRoll, player);
        System.out.printf("and moves from %d -> %d\n", player.getLocation(), finalLocation);
        if(finalLocation == board.getSize() && PLAY_TILL_ONE_WINNER){
            System.out.printf("%s is the winner\n", player.getName());
            return false;
        }
        performMovement(player, finalLocation);
        if(finalLocation != board.getSize()){
            playersQueue.addLast(player);
        }
        return true;
    }

    private void removePlayerFromMap(Players player) {
        List<Players> playersList = playersMap.getOrDefault(player.getLocation(), new ArrayList<>());
        playersList = playersList.stream().filter(e -> !e.getName().equals(player.getName())).collect(Collectors.toList());
        playersMap.put(player.getLocation(), playersList);
    }

    public void performMovement(Players player, int finalLocation){
        int curLocation = player.getLocation();
        if(curLocation != finalLocation) {
            removePlayerFromMap(player);
            if(finalLocation != 1 && !playersMap.getOrDefault(finalLocation, new ArrayList<>()).isEmpty()){
                // Move existing players on that location to 1
                List<Players> players = playersMap.getOrDefault(finalLocation, new ArrayList<>());
                String names = players.stream().map(Players::getName).collect(Collectors.joining(" "));;
                System.out.println(names + " getting moved to one due to collision");
                players.forEach(this::removePlayerFromMap);
                players.forEach(e -> {
                    e.setLocation(1);
                    List<Players> temp = playersMap.getOrDefault(1, new ArrayList<>());
                    temp.add(e);
                    playersMap.put(e.getLocation(), temp);
                });
            }
            // Move player to final location
            player.setLocation(finalLocation);
            List<Players> temp = playersMap.getOrDefault(player.getLocation(), new ArrayList<>());
            temp.add(player);
            playersMap.put(player.getLocation(), temp);
        }
    }

    public int getPlayerFinalLocation(int location, Players player){
        boolean onAnEntity;
        do {
            if(location >= board.getSize()){
                return board.getSize();
            }
            boolean isOnSnake = board.getSnakesMap().containsKey(location);
            boolean isOnLadder = board.getLadderMap().containsKey(location);
            boolean isOnCrocodile = board.getCrocodileMap().containsKey(location);
            boolean isOnMineMap = board.getMineMap().containsKey(location);
            onAnEntity = isOnSnake || isOnLadder || isOnCrocodile || isOnMineMap;
            if(isOnSnake){
                System.out.printf("and bitten by snake at %d ", location);
                location = board.getSnakesMap().get(location);
                continue;
            }
            if(isOnLadder){
                System.out.printf("and climbed the ladder at %d ", location);
                location = board.getLadderMap().get(location);
                continue;
            }
            if(isOnCrocodile){
                System.out.printf("and bitten by crocodile at %d ", location);
                location = board.getCrocodileMap().get(location);
                continue;
            }
            if(isOnMineMap){
                System.out.printf("and landed on mine at %d ", location);
                int turnsToHold = board.getMineMap().get(location);
                if(player.getHoldTillTurn() == 0){
                    player.setHoldTillTurn(turnsToHold);
                }
                break;
            }
        } while (onAnEntity);
        return location;
    }
}
