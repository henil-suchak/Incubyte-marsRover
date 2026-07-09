package com.incubyte.marsrover;

public class Rover {

    private final CommandFactory commandFactory = new CommandFactory();
    private final Grid grid ;
    private int currentXCoordinate;
    private int currentYCoordinate;
    private int lastBlockedX;
    private int lastBlockedY;

    private Direction currentDirection;

    public Rover(int startingXCoordinate, int startingYCoordinate, Direction startingDirection, Grid grid) {
        this.currentXCoordinate = startingXCoordinate;
        this.currentYCoordinate = startingYCoordinate;
        this.currentDirection = startingDirection;
        this.grid=grid;
    }

    public boolean executeCommand(char command) {
        RoverCommand roverCommand = commandFactory.createCommand(command);
        if (roverCommand != null) {
            return roverCommand.execute(this);
        }
        return true;
    }

    public void setPosition(int newXCoordinate, int newYCoordinate) {
        this.currentXCoordinate = grid.wrapXCoordinate(newXCoordinate);
        this.currentYCoordinate = grid.wrapYCoordinate(newYCoordinate);
    }
    public boolean tryMoveTo(int targetXCoordinate, int targetYCoordinate) {
        if (grid.hasObstacleAt(targetXCoordinate, targetYCoordinate)) {
            this.lastBlockedX = targetXCoordinate;
            this.lastBlockedY = targetYCoordinate;
            return false;
        }
        setPosition(targetXCoordinate, targetYCoordinate);
        return true;
    }

    public int getLastBlockedX() {
        return lastBlockedX;
    }

    public int getLastBlockedY() {
        return lastBlockedY;
    }

    public int getCurrentXCoordinate() {
        return currentXCoordinate;
    }

    public Grid getGrid() {
        return grid;
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