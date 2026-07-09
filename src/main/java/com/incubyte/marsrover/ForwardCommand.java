package com.incubyte.marsrover;

public class ForwardCommand implements RoverCommand {

    @Override
    public void execute(Rover rover) {
        Direction direction = rover.getCurrentDirection();
        int newXCoordinate = rover.getCurrentXCoordinate() + direction.getDeltaX();
        int newYCoordinate = rover.getCurrentYCoordinate() + direction.getDeltaY();
        Grid grid=rover.getGrid();
        if(grid.hasObstacleAt(newXCoordinate,newYCoordinate)) return ;
        rover.setPosition(newXCoordinate, newYCoordinate);
    }
}