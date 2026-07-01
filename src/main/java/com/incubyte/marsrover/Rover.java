package com.incubyte.marsrover;

public class Rover {

    private final CommandFactory commandFactory = new CommandFactory();

    private int currentXCoordinate;
    private int currentYCoordinate;
    private Direction currentDirection;

    public Rover(int startingXCoordinate, int startingYCoordinate, Direction startingDirection) {
        this.currentXCoordinate = startingXCoordinate;
        this.currentYCoordinate = startingYCoordinate;
        this.currentDirection = startingDirection;
    }

    public void executeCommand(char command) {
        RoverCommand roverCommand = commandFactory.createCommand(command);
        if (roverCommand != null) {
            roverCommand.execute(this);
        }
    }

    public void setPosition(int newXCoordinate, int newYCoordinate) {
        this.currentXCoordinate = newXCoordinate;
        this.currentYCoordinate = newYCoordinate;
    }

    public int getCurrentXCoordinate() {
        return currentXCoordinate;
    }

    public int getCurrentYCoordinate() {
        return currentYCoordinate;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }
    public void setCurrentDirection(String newDirection){
        this.currentDirection=Direction.valueOf(newDirection);
    }
}