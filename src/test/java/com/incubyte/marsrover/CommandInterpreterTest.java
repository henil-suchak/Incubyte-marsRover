package com.incubyte.marsrover;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CommandInterpreterTest {

    @Test
    void executesEachCharacterInCommandStringInOrder() {
        Rover rover = new Rover(0, 0, Direction.NORTH);
        CommandInterpreter commandInterpreter = new CommandInterpreter();

        commandInterpreter.interpret(rover, "FF");

        assertEquals(0, rover.getCurrentXCoordinate());
        assertEquals(2, rover.getCurrentYCoordinate());
    }
}