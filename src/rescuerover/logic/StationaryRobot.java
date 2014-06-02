package rescuerover.logic;

public class StationaryRobot extends Robot {

    private int framesPerMovement;
    private int framesLeft;
    private boolean alive;

    public StationaryRobot(int x, int y, int direction) {
        super(x, y, direction);
        sprite = new Sprite(12, 4, 32, "/robot.png");
    }

    public void step() {

    }

}
