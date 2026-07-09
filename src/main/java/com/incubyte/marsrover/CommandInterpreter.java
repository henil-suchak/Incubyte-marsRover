package com.incubyte.marsrover;

public class CommandInterpreter {

    public void interpret(Rover rover, String commands) {
        for (int i = 0; i < commands.length(); i++) {
            char command = commands.charAt(i);
            rover.executeCommand(command);
        }
    }
}