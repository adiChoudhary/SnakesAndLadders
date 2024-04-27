package org.example.models;

import lombok.Getter;

@Getter
public class Mine {
    private final int start;
    private final int hold = 2;
    public Mine(int start){
        this.start = start;
    }
}
