package org.example.models;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

@Getter
public class Board {
    private final int size;
    private Map<Integer, Integer> snakesMap;
    private Map<Integer, Integer> ladderMap;
    private Map<Integer, Integer> crocodileMap;
    private Map<Integer, Integer> mineMap;

    public Board(int size, List<Snakes> snakes, List<Ladders> ladders, List<Crocodile> crocodiles, List<Mine> mines){
        this.size = size;
        setSnakesMap(snakes);
        setLadderMap(ladders);
        setCrocodileMap(crocodiles);
        setMineMap(mines);
    }

    public void setSnakesMap(List<Snakes> snakes){
        snakesMap = new HashMap<>();
        snakes.forEach(e -> {
            snakesMap.put(e.getStart(), e.getEnd());
        });
    }

    public void setLadderMap(List<Ladders> ladders){
        ladderMap = new HashMap<>();
        ladders.forEach(e -> {
            ladderMap.put(e.getStart(), e.getEnd());
        });
    }

    public void setCrocodileMap(List<Crocodile> crocodiles){
        crocodileMap = new HashMap<>();
        crocodiles.forEach(e -> {
            crocodileMap.put(e.getStart(), e.getEnd());
        });
    }

    public void setMineMap(List<Mine> mines) {
        mineMap = new HashMap<>();
        mines.forEach(e -> {
            mineMap.put(e.getStart(), e.getHold());
        });
    }

    @Override
    public String toString() {
        return "Board{" +
                "size=" + size +
                ", snakesMap=" + snakesMap +
                ", ladderMap=" + ladderMap +
                ", crocodileMap=" + crocodileMap +
                ", mineMap=" + mineMap +
                '}';
    }
}
