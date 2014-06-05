package rescuerover.logic;

public class Robot extends MapObject implements Movable {

    private boolean alive;

    public Robot(int x, int y, int direction) {
        super(x, y, direction);
        alive = true;
        lastStep = false;
    }

    @Override
    public void move(int direction) {

    }

    public void step() {

    }

    public void fire() {
        Shot shot = new Shot(this.x, this.y, this.direction);
    }

}
