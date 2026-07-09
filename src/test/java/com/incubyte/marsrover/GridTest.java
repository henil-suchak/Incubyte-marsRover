package com.incubyte.marsrover;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GridTest {

    @Test
    void wrapsYCoordinateFromNorthEdgeToSouthEdge() {
        Grid grid = new Grid(-10, 10, -10, 10);

        int wrappedY = grid.wrapYCoordinate(11);

        assertEquals(-10, wrappedY);
    }
    @Test
    void wrapsXCoordinateFromEastEdgeToWestEdge() {
        Grid grid = new Grid(-10, 10, -10, 10);

        int wrappedX = grid.wrapXCoordinate(11);

        assertEquals(-10, wrappedX);
    }

    @Test
    void detectsObstacleAtGivenCoordinate() {
        Grid grid = new Grid(-10, 10, -10, 10, java.util.List.of(new int[]{0, 2}));

        boolean hasObstacle = grid.hasObstacleAt(0, 2);

        assertEquals(true, hasObstacle);
    }
}

