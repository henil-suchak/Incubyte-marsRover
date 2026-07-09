package com.incubyte.marsrover;

public class CommandInterpreter {

    public ExecutionResult interpret(Rover rover, String commands) {
        for (int i = 0; i < commands.length(); i++) {
            char command = commands.charAt(i);
            boolean succeeded = rover.executeCommand(command);
            if (!succeeded) {
                return new ExecutionResult(
                        "BLOCKED",
                        rover.getCurrentXCoordinate(),
                        rover.getCurrentYCoordinate(),
                        rover.getLastBlockedX(),
                        rover.getLastBlockedY()
                );
            }
        }
        return new ExecutionResult(
                "COMPLETED",
                rover.getCurrentXCoordinate(),
                rover.getCurrentYCoordinate(),
                0,
                0
        );
    }
}