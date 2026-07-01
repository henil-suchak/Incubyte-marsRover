package com.incubyte.marsrover;

import java.util.Map;

public class CommandFactory {

    private static final Map<Character, RoverCommand> COMMAND_REGISTRY = Map.of(
            'F', new ForwardCommand()
    );

    public RoverCommand createCommand(char commandCharacter) {
        return COMMAND_REGISTRY.get(commandCharacter);
    }
}