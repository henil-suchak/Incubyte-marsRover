package com.incubyte.marsrover;

public interface RoverCommand {
    String[] dirs={East,South,West,North};
    void execute(Rover rover);
}