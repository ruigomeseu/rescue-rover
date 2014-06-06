package rescuerover.logic;


public class Gate extends MapObject {

    boolean active = false;

    public Gate(int x, int y, int direction) {
        super(x, y, direction);
        sprite = new Sprite(1, 1, 32, "/sprites/gate.png");
    }

    public void open() {
        this.active = true;
    }

    public boolean getActive() {
        return active;
    }
}
