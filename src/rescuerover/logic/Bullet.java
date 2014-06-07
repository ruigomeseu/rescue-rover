package rescuerover.logic;

public class Bullet extends MapObject implements Visitor {

    public Bullet(int x, int y, int direction) {
        super(x, y, direction);
        sprite = new Sprite(4, 1, 32, "/sprites/bullet.png");

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
