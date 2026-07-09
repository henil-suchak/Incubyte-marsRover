package com.incubyte.marsrover;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CommandInterpreterTest {

    @Test
    void executesEachCharacterInCommandStringInOrder() {
        Rover rover = new Rover(0, 0, Direction.NORTH, new Grid(-10, 10, -10, 10, java.util.List.of()));
        CommandInterpreter commandInterpreter = new CommandInterpreter();

        commandInterpreter.interpret(rover, "FF");

        assertEquals(0, rover.getCurrentXCoordinate());
        assertEquals(2, rover.getCurrentYCoordinate());
    }
    @Test
    void stopsExecutingRemainingCommandsWhenObstacleBlocksPath() {
        Grid grid = new Grid(-10, 10, -10, 10, java.util.List.of(new int[]{0, 2}));
        Rover rover = new Rover(0, 0, Direction.NORTH, grid);
        CommandInterpreter commandInterpreter = new CommandInterpreter();

        commandInterpreter.interpret(rover, "FFR");

        assertEquals(0, rover.getCurrentXCoordinate());
        assertEquals(1, rover.getCurrentYCoordinate());
        assertEquals(Direction.NORTH, rover.getCurrentDirection());
    }

    @Test
    void returnsCompletedStatusWhenNoObstacleBlocksPath() {
        Grid grid = new Grid(-10, 10, -10, 10, java.util.List.of());
        Rover rover = new Rover(0, 0, Direction.NORTH, grid);
        CommandInterpreter commandInterpreter = new CommandInterpreter();

        ExecutionResult result = commandInterpreter.interpret(rover, "FF");

        assertEquals("COMPLETED", result.getStatus());
    }
    @Test
    void reportsLastSafePositionWhenBlockedByObstacle() {
        Grid grid = new Grid(-10, 10, -10, 10, java.util.List.of(new int[]{0, 2}));
        Rover rover = new Rover(0, 0, Direction.NORTH, grid);
        CommandInterpreter commandInterpreter = new CommandInterpreter();

        ExecutionResult result = commandInterpreter.interpret(rover, "FFR");

        assertEquals(0, result.getLastSafeX());
        assertEquals(1, result.getLastSafeY());
    }

    @Test
    void reportsObstacleCoordinateWhenBlocked() {
        Grid grid = new Grid(-10, 10, -10, 10, java.util.List.of(new int[]{0, 2}));
        Rover rover = new Rover(0, 0, Direction.NORTH, grid);
        CommandInterpreter commandInterpreter = new CommandInterpreter();

        ExecutionResult result = commandInterpreter.interpret(rover, "FFR");

        assertEquals(0, result.getObstacleX());
        assertEquals(2, result.getObstacleY());
    }
    @Test
    void reportsCommandsExecutedBeforeBlocked() {
        Grid grid = new Grid(-10, 10, -10, 10, java.util.List.of(new int[]{0, 2}));
        Rover rover = new Rover(0, 0, Direction.NORTH, grid);
        CommandInterpreter commandInterpreter = new CommandInterpreter();

        ExecutionResult result = commandInterpreter.interpret(rover, "FFR");

        assertEquals(java.util.List.of('F'), result.getCommandsExecuted());
    }
    @Test
    void replicatesSpecSampleScenarioForObstacleBlocking() {
        Grid grid = new Grid(-10, 10, -10, 10, java.util.List.of(new int[]{0, 2}));
        Rover rover = new Rover(0, 0, Direction.NORTH, grid);
        CommandInterpreter commandInterpreter = new CommandInterpreter();

        ExecutionResult result = commandInterpreter.interpret(rover, "FFRFLB");

        assertEquals("BLOCKED", result.getStatus());
        assertEquals(0, result.getLastSafeX());
        assertEquals(1, result.getLastSafeY());
        assertEquals("NORTH", rover.getCurrentDirection().name());
        assertEquals(0, result.getObstacleX());
        assertEquals(2, result.getObstacleY());
        assertEquals(java.util.List.of('F'), result.getCommandsExecuted());
    }
}