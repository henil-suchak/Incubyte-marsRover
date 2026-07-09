package com.incubyte.marsrover;

public class BackwardCommand implements RoverCommand {

    @Override
    public void execute(Rover rover) {
        Direction direction = rover.getCurrentDirection();
        int targetXCoordinate = rover.getCurrentXCoordinate() - direction.getDeltaX();
        int targetYCoordinate = rover.getCurrentYCoordinate() - direction.getDeltaY();

        Grid grid = rover.getGrid();
        if (grid.hasObstacleAt(targetXCoordinate, targetYCoordinate)) {
            return;
        }

        rover.setPosition(targetXCoordinate, targetYCoordinate);
    }
}