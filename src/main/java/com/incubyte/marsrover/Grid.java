package com.incubyte.marsrover;
import java.util.List;
public class Grid {

    private final int minXCoordinate;
    private final int maxXCoordinate;
    private final int minYCoordinate;
    private final int maxYCoordinate;
    private final List<int[]> obstacleCoordinates;

    public Grid(int minXCoordinate, int maxXCoordinate, int minYCoordinate, int maxYCoordinate, List<int[]> obstacleCoordinates) {
        this.minXCoordinate = minXCoordinate;
        this.maxXCoordinate = maxXCoordinate;
        this.minYCoordinate = minYCoordinate;
        this.maxYCoordinate = maxYCoordinate;
        this.obstacleCoordinates = obstacleCoordinates;
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
    public boolean hasObstacleAt(int xCoordinate,int yCoordinate){
        for(int[] obstacleCoordinate: obstacleCoordinates){
            if(xCoordinate==obstacleCoordinate[0] && yCoordinate==obstacleCoordinate[1]){
                return true;
            }
        }
        return false;
    }
}