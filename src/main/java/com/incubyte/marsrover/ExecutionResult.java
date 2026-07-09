package com.incubyte.marsrover;

public class ExecutionResult {

    private final String status;
    private final int lastSafeX;
    private final int lastSafeY;
    private final int obstacleX;
    private final int obstacleY;

    public ExecutionResult(String status, int lastSafeX, int lastSafeY, int obstacleX, int obstacleY) {
        this.status = status;
        this.lastSafeX = lastSafeX;
        this.lastSafeY = lastSafeY;
        this.obstacleX = obstacleX;
        this.obstacleY = obstacleY;
    }

    public String getStatus() {
        return status;
    }

    public int getLastSafeX() {
        return lastSafeX;
    }

    public int getLastSafeY() {
        return lastSafeY;
    }

    public int getObstacleX() {
        return obstacleX;
    }

    public int getObstacleY() {
        return obstacleY;
    }
}