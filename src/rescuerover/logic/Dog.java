package rescuerover.logic;

public class Dog extends MapObject {
    private boolean withHero;

    public Dog(int x, int y, int direction) {
        super(x, y, direction);
        withHero = false;
    }

    public void setWithHero() {
        this.withHero = true;
    }

}
