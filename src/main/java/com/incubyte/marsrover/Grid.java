package com.incubyte.marsrover;

public class Grid {

    private final int minXCoordinate;
    private final int maxXCoordinate;
    private final int minYCoordinate;
    private final int maxYCoordinate;

    public Grid(int minXCoordinate, int maxXCoordinate, int minYCoordinate, int maxYCoordinate) {
        this.minXCoordinate = minXCoordinate;
        this.maxXCoordinate = maxXCoordinate;
        this.minYCoordinate = minYCoordinate;
        this.maxYCoordinate = maxYCoordinate;
    }

    public int wrapYCoordinate(int yCoordinate) {
        if (yCoordinate > maxYCoordinate) {
            return minYCoordinate;
        }
        if (yCoordinate < minYCoordinate) {
            return maxYCoordinate;
        }
        return yCoordinate;
    }
}