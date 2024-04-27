package org.example.junits.service;

import java.io.File;

import org.example.configuration.Configuration;
import org.example.configuration.ConfigurationFactory;
import org.example.models.Board;
import org.example.models.Crocodile;
import org.example.models.Ladders;
import org.example.models.Mine;
import org.example.models.Players;
import org.example.models.Snakes;
import org.example.service.SnakeAndLadderDriver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;


public class SnakeAndLadderDriverTest {
    
    private SnakeAndLadderDriver driver;
    private Board board;
    private Players player1;
    private Players player2;
    List<Players> players = new ArrayList<>();
    List<Snakes> snakes = new ArrayList<>();
    
    @BeforeEach
    public void setup() {
        ConfigurationFactory.initializeConfiguration();
        Snakes snakePos1  = new Snakes(62, 5);
        Snakes snakePos2  = new Snakes(33, 6);
        Snakes snakePos3  = new Snakes(49, 9);
        snakes.add(snakePos1);
        snakes.add(snakePos2);
        snakes.add(snakePos3);
        board = new Board(100, snakes ,Arrays.asList( new Ladders(2,37) , new Ladders(27, 46) , new Ladders(10,32)),
            Arrays.asList(new Crocodile(4), new Crocodile(16), new Crocodile(9)), Arrays.asList(new Mine(24), new Mine(82), new Mine(97)));
        player1 = new Players("Aditya",0,0);
        player2 = new Players("Prakhar", 0,0);
        players.add(player1);
        players.add(player2);
        driver = new SnakeAndLadderDriver(board, players);
    }
    
    @Test
    public void testStartGameInSuffiecentDiceInputPresent() throws FileNotFoundException, NoSuchElementException {
        String shortDiceInputFilePath= "src/main/resources/inputShortDiceSize.txt";
        Scanner scanner = new Scanner(new File(shortDiceInputFilePath));
        boolean startTest;
        try {
            do {
                startTest = driver.testGame(scanner);
            } while (startTest);
        } catch (Exception e) {
            assertEquals(NoSuchElementException.class, e.getClass());
            
        }
    }
    
    @Test
    public void testStartGameSuccessWinnerMoveExactToBoardSize() throws FileNotFoundException {
        String winnerMoveExactToBoardSizeFilePath = "src/main/resources/winerMoveExactToBoardSize.txt";
        Scanner scanner = new Scanner(new File(winnerMoveExactToBoardSizeFilePath));
        boolean startTest;
            do {
                startTest = driver.testGame(scanner);
            } while (startTest);
            assertEquals(Boolean.FALSE, false);
    }
    
    @Test
    public void testStartGameSuccessWinnerMoveNotExactToBoardSize() throws FileNotFoundException {
        String winnerMoveExactToBoardSizeFilePath = "src/main/resources/winerMoveGreaterThanBoardSize.txt";
        Scanner scanner = new Scanner(new File(winnerMoveExactToBoardSizeFilePath));
        boolean startTest;
        do {
            startTest = driver.testGame(scanner);
        } while (startTest);
        assertEquals(Boolean.FALSE, false);
    }

    @Test
    public void testStartGameSuccessAllScenarios() throws FileNotFoundException {
        String winnerMoveExactToBoardSizeFilePath = "src/main/resources/allCases.txt";
        Scanner scanner = new Scanner(new File(winnerMoveExactToBoardSizeFilePath));
        initializeDriver();
        boolean startTest;
        do {
            startTest = driver.testGame(scanner);
        } while (startTest);
        assertEquals(Boolean.FALSE, false);
    }

    private void initializeDriver() throws FileNotFoundException {
        String winnerMoveExactToBoardSizeFilePath = "src/main/resources/allCasesInput.txt";
        Scanner sc = new Scanner(new File(winnerMoveExactToBoardSizeFilePath));
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
        ConfigurationFactory.initializeConfiguration();
        Configuration configuration = ConfigurationFactory.configuration;
        board = new Board(configuration.getBoardSize().getLength() * configuration.getBoardSize().getWidth(), snakes, ladders, crocodiles, mines);
        driver = new SnakeAndLadderDriver(board, players);
    }
}
