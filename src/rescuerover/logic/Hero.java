package rescuerover.logic;

public class Hero extends MapObject implements Movable {

    private Map map;
    private boolean hasDog;

    public Hero(int x, int y, int direction, Map map) {
        super(x, y, direction);
        this.map = map;
    }

    public boolean move(int direction) {
        return false;
    }

    public boolean hasDog() {
        return this.hasDog;
    }

    public void catchDog() {
        this.hasDog = true;
    }

}
