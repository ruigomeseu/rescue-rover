package rescuerover.logic;

import java.util.ArrayList;

public class Robot extends MapObject implements Movable {

    private boolean alive;

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    private ArrayList<Bullet> bullets;

    public Robot(int x, int y, int direction) {
        super(x, y, direction);
        alive = true;
        bullets = new ArrayList<Bullet>();
    }

    @Override
    public void move(int direction) {

    }

    public void step() {

    }

    public void fire() {
        Bullet bullet = new Bullet(this.x, this.y, this.direction);
        bullets.add(bullet);
    }

}
