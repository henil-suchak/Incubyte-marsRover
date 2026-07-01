package com.incubyte.marsrover;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RoverTest {

    @Test
    void roverInitializesAtGivenPositionAndDirection() {
        Rover rover = new Rover(0, 0, Direction.NORTH);

        assertEquals(0, rover.getCurrentXCoordinate());
        assertEquals(0, rover.getCurrentYCoordinate());
        assertEquals(Direction.NORTH, rover.getCurrentDirection());
    }

    @Test
    void roverMovesOneStepForwardWhenFacingNorth() {
        Rover rover = new Rover(0, 0, Direction.NORTH);

        rover.executeCommand('F');

        assertEquals(0, rover.getCurrentXCoordinate());
        assertEquals(1, rover.getCurrentYCoordinate());
    }

    @Test
    void roverMovesOneStepBackwardWhenFacingNorth() {
        Rover rover = new Rover(0, 0, Direction.NORTH);

        rover.executeCommand('B');

        assertEquals(0, rover.getCurrentXCoordinate());
        assertEquals(-1, rover.getCurrentYCoordinate());
    }

    @Test
    void roverTurnsLeftFromNorthToWest() {
        Rover rover = new Rover(0, 0, Direction.NORTH);

        rover.executeCommand('L');

        assertEquals(Direction.WEST, rover.getCurrentDirection());
    }

    @Test
    void roverTurnsRightFromNorthToEast() {
        Rover rover = new Rover(0, 0, Direction.NORTH);

        rover.executeCommand('R');

        assertEquals(Direction.EAST, rover.getCurrentDirection());
    }
}
