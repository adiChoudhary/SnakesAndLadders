package org.example.service;

import org.example.configuration.Configuration;
import org.example.configuration.ConfigurationFactory;
import org.example.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameStarterService {
    private static final Configuration configuration = ConfigurationFactory.configuration;
    public static void startGame(){
        Scanner sc = new Scanner(System.in);
        int noOfSnakes = sc.nextInt();

        // Initialize board elements
        List<Snakes> snakes = new ArrayList<>();
        for (int i = 0; i < noOfSnakes; i++) {
            snakes.add(new Snakes(sc.nextInt(), sc.nextInt()));
        }

        int noOfLadders = sc.nextInt();
        List<Ladders> ladders = new ArrayList<>();
        for (int i = 0; i < noOfLadders; i++) {
            ladders.add(new Ladders(sc.nextInt(), sc.nextInt()));
        }

        int noOfCrocodiles = sc.nextInt();
        List<Crocodile> crocodiles = new ArrayList<>();
        for (int i = 0; i < noOfCrocodiles; i++) {
            crocodiles.add(new Crocodile(sc.nextInt()));
        }

        int noOfMines = sc.nextInt();
        List<Mine> mines = new ArrayList<>();
        for (int i = 0; i < noOfMines; i++) {
            mines.add(new Mine(sc.nextInt()));
        }

        // Initialize players
        int noOfPlayers = sc.nextInt();
        List<Players> players = new ArrayList<>();
        for (int i = 0; i < noOfPlayers; i++) {
            players.add(new Players(sc.next(), sc.nextInt(), 0));
        }
        Board board = new Board(configuration.getBoardSize().getLength() * configuration.getBoardSize().getWidth(), snakes, ladders, crocodiles, mines);
        SnakeAndLadderDriver snakeAndLadderDriver = new SnakeAndLadderDriver(board, players);

        // Perform simulation or test
        char isTestMode = sc.next().charAt(0);
        if(isTestMode == 'Y'){
            boolean startTest;
            do {
                System.out.print("Enter dice roll for ");
                startTest = snakeAndLadderDriver.testGame(sc);
            } while (startTest);
        } else {
            snakeAndLadderDriver.startGame();
        }
    }
}
