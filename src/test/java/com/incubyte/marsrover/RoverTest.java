package com.incubyte.marsrover;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RoverTest {

    @Test
    void roverInitializesAtGivenPositionAndDirection() {
        Rover rover = new Rover(0, 0, Direction.NORTH, new Grid(-10, 10, -10, 10));

        assertEquals(0, rover.getCurrentXCoordinate());
        assertEquals(0, rover.getCurrentYCoordinate());
        assertEquals(Direction.NORTH, rover.getCurrentDirection());
    }

    @Test
    void roverMovesOneStepForwardWhenFacingNorth() {
        Rover rover = new Rover(0, 0, Direction.NORTH, new Grid(-10, 10, -10, 10));

        rover.executeCommand('F');

        assertEquals(0, rover.getCurrentXCoordinate());
        assertEquals(1, rover.getCurrentYCoordinate());
    }

    @Test
    void roverMovesOneStepBackwardWhenFacingNorth() {
        Rover rover = new Rover(0, 0, Direction.NORTH, new Grid(-10, 10, -10, 10));

        rover.executeCommand('B');

        assertEquals(0, rover.getCurrentXCoordinate());
        assertEquals(-1, rover.getCurrentYCoordinate());
    }

    @Test
    void roverTurnsLeftFromNorthToWest() {
        Rover rover = new Rover(0, 0, Direction.NORTH, new Grid(-10, 10, -10, 10));

        rover.executeCommand('L');

        assertEquals(Direction.WEST, rover.getCurrentDirection());
    }

    @Test
    void roverTurnsRightFromNorthToEast() {
        Rover rover = new Rover(0, 0, Direction.NORTH, new Grid(-10, 10, -10, 10));

        rover.executeCommand('R');

        assertEquals(Direction.EAST, rover.getCurrentDirection());
    }
    @Test
    void roverWrapsToSouthEdgeWhenMovingForwardPastNorthBoundary() {
        Grid grid = new Grid(-10, 10, -10, 10);
        Rover rover = new Rover(0, 10, Direction.NORTH, grid);

        rover.executeCommand('F');

        assertEquals(0, rover.getCurrentXCoordinate());
        assertEquals(-10, rover.getCurrentYCoordinate());
    }
}
