package com.incubyte.marsrover;

public class CommandInterpreter {

    public ExecutionResult interpret(Rover rover, String commands) {
        for (int i = 0; i < commands.length(); i++) {
            char command = commands.charAt(i);
            boolean succeeded = rover.executeCommand(command);
            if (!succeeded) {
                return new ExecutionResult("BLOCKED");
            }
        }
        return new ExecutionResult("COMPLETED");
    }
}