package com.incubyte.marsrover;

import java.util.Map;

public class CommandFactory {

    private static final Map<Character, RoverCommand> COMMAND_REGISTRY = Map.of(
            'F', new ForwardCommand(),
            'B', new BackwardCommand(),
            'L', new LeftwardCommand()
    );

    public RoverCommand createCommand(char commandCharacter) {
        return COMMAND_REGISTRY.get(commandCharacter);
    }
}