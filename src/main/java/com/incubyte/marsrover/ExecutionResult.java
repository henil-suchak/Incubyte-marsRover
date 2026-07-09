package com.incubyte.marsrover;

import java.util.List;

public class ExecutionResult {

    private final String status;
    private final int lastSafeX;
    private final int lastSafeY;
    private final int obstacleX;
    private final int obstacleY;
    private final List<Character> commandsExecuted;

    public ExecutionResult(String status, int lastSafeX, int lastSafeY, int obstacleX, int obstacleY, List<Character> commandsExecuted) {
        this.status = status;
        this.lastSafeX = lastSafeX;
        this.lastSafeY = lastSafeY;
        this.obstacleX = obstacleX;
        this.obstacleY = obstacleY;
        this.commandsExecuted = commandsExecuted;
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

    public List<Character> getCommandsExecuted() {
        return commandsExecuted;
    }
}