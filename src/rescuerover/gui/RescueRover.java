package rescuerover.gui;

import rescuerover.gui.states.ScreenStateManager;

public class RescueRover {

    ScreenStateManager stateManager;

    public RescueRover() {
        stateManager = new ScreenStateManager();

        stateManager.draw();
    }

    public static void main(String[] args) {
        RescueRover game = new RescueRover();
    }
}