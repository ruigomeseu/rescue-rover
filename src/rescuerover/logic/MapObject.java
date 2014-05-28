package rescuerover.logic;

public abstract class MapObject extends Position {

    protected Sprite sprite;

    protected boolean moving;

    public Sprite getSprite() {
        return this.sprite;
    }

    public MapObject(int x, int y, int direction) {
        super(x, y, direction);
        moving = false;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void step() { }
}
