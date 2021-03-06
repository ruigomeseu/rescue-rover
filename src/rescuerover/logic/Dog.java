package rescuerover.logic;

public class Dog extends MapObject {
    private boolean withHero;

    public Dog(int x, int y, int direction) {
        super(x, y, direction);
        withHero = false;
        sprite = new Sprite(1, 1, 32, "/sprites/dog.png");
    }

    public void setWithHero() {
        this.withHero = true;
    }

    public void setNotWithHero() {
        this.withHero = true;
    }

    public boolean getWithHero() {
        return this.withHero;
    }
}
