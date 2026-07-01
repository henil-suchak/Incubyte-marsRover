package com.incubyte.marsrover;

public class RightwardCommand implements RoverCommand {
    String[] dirs={"EAST","SOUTH","WEST","NORTH"};
    @Override
    public void execute(Rover rover) {
        int i=0;
        for( i=0;i<4;i++){
            if(rover.getCurrentDirection().name().equals(dirs[i])){
                break;
            }
        }
        rover.setCurrentDirection(dirs[(i+1)%4]);
    }
}