package rescuerover.logic;


public class Key extends MapObject {

    String type;
    boolean withHero = false;

    public Key(int x, int y, int direction, String type) {
        super(x, y, direction);
        sprite = new Sprite(1, 1, 32, "/sprites/Key.png");
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
