package org.example.configuration;

import lombok.Data;
import org.example.enums.MovementStrategy;
import org.example.models.boardAndPieces.BoardDimensions;

@Data
public class Configuration {
    private int players;
    private BoardDimensions boardSize;
    private int snakes;
    private int ladders;
    private int crocodiles;
    private int mines;
    private int dies;
    private boolean playTillOneWinner;
    private MovementStrategy ms;
}
