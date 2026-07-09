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
        return wrapValue(yCoordinate, minYCoordinate, maxYCoordinate);
    }

    public int wrapXCoordinate(int xCoordinate) {
        return wrapValue(xCoordinate, minXCoordinate, maxXCoordinate);
    }

    private int wrapValue(int value, int minValue, int maxValue) {
        if (value > maxValue) {
            return minValue;
        }
        if (value < minValue) {
            return maxValue;
        }
        return value;
    }
}