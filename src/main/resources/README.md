# Snakes and ladders

## Input Format
* Total Snakes S
* Following S lines contains pair (Snake’s Head and Snake’s Tail)
* Total Ladders L
* Following L lines contains pair (Ladder bottom and Ladder top)
* Total Crocodiles C
* Following C lines contains crocodile head
* Total Mines M
* Following M lines contains mine location
* N no of players
* Following N lines contains names & starting locations of each Player
* Is Test Mode T(Y/N)
* If Test Mode N, simulation is executed
* If Test Mode Y
  * Supply dice roll value for each turn till a winning condition is encountered.

## Assumptions
* Board size supplied via application.yml
* Number of dices supplied via application.yml
* Movement strategy supplied via application.yml
* Snakes, Ladders, Mines and Crocodile don't collide with each other's start location
* Simulation is executed until one of the users win the game

## Sample input and output
* Sample Input file for simulation in **"src/main/resources/input.txt"**
* Sample Input file for test mode in **src/main/resources/inputForTestModeRun.txt**
* Sample Input file + Output for test mode in **src/main/resources/inputForTestModeWithOutputRun.txt**
* Sample Simulation Output file in **"src/main/resources/Simulation.txt"**

