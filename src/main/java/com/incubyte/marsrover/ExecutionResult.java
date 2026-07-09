package com.incubyte.marsrover;

public class ExecutionResult {

    private final String status;

    public ExecutionResult(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}