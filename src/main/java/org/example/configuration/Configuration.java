package org.example.configuration;

import lombok.Data;
import org.example.enums.MovementStrategy;

import java.util.Map;

@Data
public class Configuration {
    private int players;
    private Map<String, Integer> boardSize;
    private int snakes;
    private int ladders;
    private int dies;
    private MovementStrategy ms;
}
