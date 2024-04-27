package org.example.models;

import lombok.Getter;

@Getter
public class Crocodile {
    private final int start;
    private final int end;

    public Crocodile(int start){
        this.start = start;
        int temp = start - 5;
        this.end = Math.max(temp, 1);
    }
}
