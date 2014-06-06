package rescuerover.logic;

public class StationaryRobot extends Robot {

    private int framesPerMovement;
    private int framesLeft;
    private boolean alive;
    private int time;

    public StationaryRobot(int x, int y, int direction) {
        super(x, y, direction);
        sprite = new Sprite(12, 4, 32, "/sprites/robot.png");
        this.time = 0;
    }

    public void step() {
        time += 50;

        if(time == 10000){
            fire();
            time = 0;
        }

    }

}
