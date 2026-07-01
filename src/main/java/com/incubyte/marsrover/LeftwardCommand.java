package com.incubyte.marsrover;

public class BackwardCommand implements RoverCommand {

    @Override
    public void execute(Rover rover) {
        int i=0;
        for(int i=0;i<4;i++){
            if(rover.getCurrentDirection()==dirs[i]){
                break;
            }
        }
        rover.setCurrentDirection(dirs[i-1]);
    }
}