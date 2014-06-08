package rescuerover.logic;

/**
 * A robot that fires every 2s
 */
public class StationaryRobot extends Robot {

    private int framesPerMovement;
    private int framesLeft;
    private boolean alive;
    private int time;

    public StationaryRobot(int x, int y, int direction) {
        super(x, y, direction);
        sprite = new Sprite(12, 4, 32, "/sprites/robot.png");
        switch (direction) {
            case Constants.UP:
                sprite.setFrameNumber(9);
                break;
            case Constants.LEFT:
                sprite.setFrameNumber(3);
                break;
            case Constants.DOWN:
                sprite.setFrameNumber(0);
                break;
            case Constants.RIGHT:
                sprite.setFrameNumber(6);
                break;
            default:
                break;

        };
        this.time = 0;
    }

    public void step() {
        time += 50;

        if(time == 2000){
            fire();
            time = 0;
        }

    }

}
