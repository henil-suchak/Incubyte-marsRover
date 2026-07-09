package com.incubyte.marsrover;

public class ExecutionResult {

    private final String status;
    private final int lastSafeX;
    private final int lastSafeY;

    public ExecutionResult(String status, int lastSafeX, int lastSafeY) {
        this.status = status;
        this.lastSafeX = lastSafeX;
        this.lastSafeY = lastSafeY;
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
}