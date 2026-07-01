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
}
