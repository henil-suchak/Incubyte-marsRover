package com.incubyte.marsrover;

public class Rover {

    private int currentXCoordinate;
    private int currentYCoordinate;
    private Direction currentDirection;

    public Rover(int startingXCoordinate, int startingYCoordinate, Direction startingDirection) {
        this.currentXCoordinate = startingXCoordinate;
        this.currentYCoordinate = startingYCoordinate;
        this.currentDirection = startingDirection;
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
}