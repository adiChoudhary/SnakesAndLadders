package org.example.enums;

import org.example.service.DiceService;

public enum MovementStrategy {
    SUM() {
        @Override
        public int diceRollResult(int dices) {
            int sum = 0;
            for(int i=0; i<dices; i++){
                sum += DiceService.roll();
            }
            return sum;
        }
    },
    MAX() {
        @Override
        public int diceRollResult(int dices) {
            int max = 0;
            for(int i=0; i<dices; i++){
                max = Math.max(DiceService.roll(), max);
            }
            return max;
        }
    },
    MIN() {
        @Override
        public int diceRollResult(int dices) {
            int min = Integer.MAX_VALUE;
            for(int i=0; i<dices; i++){
                min = Math.min(DiceService.roll(), min);
            }
            return min;
        }
    };
    public abstract int diceRollResult(int dices);
}
