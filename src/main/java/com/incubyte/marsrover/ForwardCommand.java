package com.incubyte.marsrover;

public class ForwardCommand implements RoverCommand {

    @Override
    public boolean execute(Rover rover) {
        Direction direction = rover.getCurrentDirection();
        int targetXCoordinate = rover.getCurrentXCoordinate() + direction.getDeltaX();
        int targetYCoordinate = rover.getCurrentYCoordinate() + direction.getDeltaY();

        return rover.tryMoveTo(targetXCoordinate, targetYCoordinate);
    }
}