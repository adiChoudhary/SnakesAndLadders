package org.example.service;

import org.example.configuration.Configuration;
import org.example.configuration.ConfigurationFactory;
import org.example.models.boardAndPieces.*;
import org.example.models.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameStarterService {
    private static final Configuration configuration = ConfigurationFactory.configuration;
    public static void startGame(){
        Scanner sc = new Scanner(System.in);
        int noOfSnakes = sc.nextInt();

        // Initialize board elements
        List<Piece> pieces = new ArrayList<>();
        for (int i = 0; i < noOfSnakes; i++) {
            pieces.add(new Snake(sc.nextInt(), sc.nextInt()));
        }

        int noOfLadders = sc.nextInt();
        for (int i = 0; i < noOfLadders; i++) {
            pieces.add(new Ladder(sc.nextInt(), sc.nextInt()));
        }

        int noOfCrocodiles = sc.nextInt();
        for (int i = 0; i < noOfCrocodiles; i++) {
            pieces.add(new Crocodile(sc.nextInt()));
        }

        int noOfMines = sc.nextInt();
        for (int i = 0; i < noOfMines; i++) {
            pieces.add(new Mine(sc.nextInt()));
        }

        // Initialize players
        int noOfPlayers = sc.nextInt();
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < noOfPlayers; i++) {
            players.add(new Player(sc.next(), sc.nextInt(), 0));
        }
        Board board = new Board(configuration.getBoardSize().getLength() * configuration.getBoardSize().getWidth(), pieces);
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
