package org.example;

import org.example.configuration.ConfigurationFactory;
import org.example.service.GameStarterService;

public class Main {

    public static void main(String[] args) {
        ConfigurationFactory.initializeConfiguration();
        GameStarterService.startGame();
    }
}