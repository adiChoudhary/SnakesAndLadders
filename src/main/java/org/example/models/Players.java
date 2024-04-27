package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class Players {
    private String name;
    private int location;
    private int holdTillTurn;
}
