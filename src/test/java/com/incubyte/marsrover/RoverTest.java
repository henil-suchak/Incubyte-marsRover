package com.incubyte.marsrover;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RoverTest {

    @Test
    void roverInitializesAtGivenPositionAndDirection() {
        Rover rover = new Rover(0, 0, Direction.NORTH, new Grid(-10, 10, -10, 10, java.util.List.of()));

        assertEquals(0, rover.getCurrentXCoordinate());
        assertEquals(0, rover.getCurrentYCoordinate());
        assertEquals(Direction.NORTH, rover.getCurrentDirection());
    }

    @Test
    void roverMovesOneStepForwardWhenFacingNorth() {
        Rover rover = new Rover(0, 0, Direction.NORTH, new Grid(-10, 10, -10, 10, java.util.List.of()));

        rover.executeCommand('F');

        assertEquals(0, rover.getCurrentXCoordinate());
        assertEquals(1, rover.getCurrentYCoordinate());
    }

    @Test
    void roverMovesOneStepBackwardWhenFacingNorth() {
        Rover rover = new Rover(0, 0, Direction.NORTH, new Grid(-10, 10, -10, 10, java.util.List.of()));

        rover.executeCommand('B');

        assertEquals(0, rover.getCurrentXCoordinate());
        assertEquals(-1, rover.getCurrentYCoordinate());
    }

    @Test
    void roverTurnsLeftFromNorthToWest() {
        Rover rover = new Rover(0, 0, Direction.NORTH, new Grid(-10, 10, -10, 10, java.util.List.of()));

        rover.executeCommand('L');

        assertEquals(Direction.WEST, rover.getCurrentDirection());
    }

    @Test
    void roverTurnsRightFromNorthToEast() {
        Rover rover = new Rover(0, 0, Direction.NORTH, new Grid(-10, 10, -10, 10, java.util.List.of()));

        rover.executeCommand('R');

        assertEquals(Direction.EAST, rover.getCurrentDirection());
    }

    @Test
    void roverWrapsToSouthEdgeWhenMovingForwardPastNorthBoundary() {
        Grid grid = new Grid(-10, 10, -10, 10, java.util.List.of());
        Rover rover = new Rover(0, 10, Direction.NORTH, grid);

        rover.executeCommand('F');

        assertEquals(0, rover.getCurrentXCoordinate());
        assertEquals(-10, rover.getCurrentYCoordinate());
    }

    @Test
    void roverWrapsToWestEdgeWhenMovingForwardPastEastBoundary() {
        Grid grid = new Grid(-10, 10, -10, 10, java.util.List.of());
        Rover rover = new Rover(10, 0, Direction.EAST, grid);

        rover.executeCommand('F');

        assertEquals(-10, rover.getCurrentXCoordinate());
        assertEquals(0, rover.getCurrentYCoordinate());
    }
}