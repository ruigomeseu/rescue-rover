package rescuerover.logic;

/**
 * The bullet that is fired from the robots
 */
public class Bullet extends MapObject implements Visitor {

    public Bullet(int x, int y, int direction) {
        super(x, y, direction);
        sprite = new Sprite(4, 1, 32, "/sprites/bullet.png");
        switch (direction) {
            case Constants.UP:
                sprite.setFrameNumber(3);
                break;
            case Constants.LEFT:
                sprite.setFrameNumber(1);
                break;
            case Constants.DOWN:
                sprite.setFrameNumber(2);
                break;
            case Constants.RIGHT:
                sprite.setFrameNumber(0);
                break;
            default:
                break;

        };

    }

    public void step() {
        switch (direction) {
            case Constants.UP:
                this.decY();
                break;
            case Constants.RIGHT:
                this.incX();
                break;
            case Constants.DOWN:
                this.incY();
                break;
            case Constants.LEFT:
                this.decX();
                break;
            default:
                break;
        }
    }

    @Override
    public void visit(Hero hero) {
        hero.kill();
    }
}
