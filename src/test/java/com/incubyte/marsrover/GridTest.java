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
}
