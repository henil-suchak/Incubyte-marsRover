package com.incubyte.marsrover;

public class LeftwardCommand implements RoverCommand {

    @Override
    public boolean execute(Rover rover) {
        String[] dirs = DirectionOrder.COUNTER_CLOCKWISE_ORDER;
        int i = 0;
        for (i = 0; i < 4; i++) {
            if (rover.getCurrentDirection().name().equals(dirs[i])) {
                break;
            }
        }
        rover.setCurrentDirection(dirs[(i - 1 + 4) % 4]);
        return true;
    }
}