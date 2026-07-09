package com.incubyte.marsrover;

import java.util.ArrayList;
import java.util.List;

public class CommandInterpreter {

    public ExecutionResult interpret(Rover rover, String commands) {
        List<Character> commandsExecuted = new ArrayList<>();

        for (int i = 0; i < commands.length(); i++) {
            char command = commands.charAt(i);
            boolean succeeded = rover.executeCommand(command);

            if (!succeeded) {
                return new ExecutionResult(
                        "BLOCKED",
                        rover.getCurrentXCoordinate(),
                        rover.getCurrentYCoordinate(),
                        rover.getLastBlockedX(),
                        rover.getLastBlockedY(),
                        commandsExecuted
                );
            }

            commandsExecuted.add(command);
        }

        return new ExecutionResult(
                "COMPLETED",
                rover.getCurrentXCoordinate(),
                rover.getCurrentYCoordinate(),
                0,
                0,
                commandsExecuted
        );
    }
}