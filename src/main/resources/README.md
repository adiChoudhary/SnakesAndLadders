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
* Board size fixed to 100, can be changed according to input needed to be provided
* Snakes, Ladders, Mines and Crocodile don't collide with each other's start location
* Default strategy used is SUM, can be changed as per convenience
* Simulation is executed until one of the users win the game

## Sample input and output
* Sample Input file for simulation in **"src/main/resources/input.txt"**
* Sample Input file + Output for test mode in **src/main/resources/inputForTestModeRun.txt**
* Sample Simulation Output file in **"src/main/resources/Simulation.txt"**

## Instructions to run
* Run `mvn clean package install` in root directory of project
* Go to "target"
* Execute `java -jar snakesAndLadders.jar`
* Supply input in commandLine
* For correct input output is generated

