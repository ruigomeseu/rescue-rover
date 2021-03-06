package rescuerover.logic;

/**
 * The key used to open the gate
 * May be a fake key or the real key
 */
public class Key extends MapObject {

    String type;
    boolean withHero = false;

    public Key(int x, int y, int direction, String type) {
        super(x, y, direction);
        sprite = new Sprite(1, 1, 32, "/sprites/key.png");
        this.type = type;
    }

    public void setWithHero(boolean bool) {
        this.withHero = bool;
    }

    public boolean getWithHero() {
        return withHero;
    }

    public String getType() {
        return type;
    }
}
