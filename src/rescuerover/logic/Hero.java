package rescuerover.logic;

public class Hero extends MapObject implements Movable {

    private Map map;
    private boolean hasDog;
    private int framesPerMovement;
    private int framesLeft;
    private boolean alive;

    public Hero(int x, int y, int direction, Map map) {
        super(x, y, direction);
        this.map = map;
        framesPerMovement = 6;
        sprite = new Sprite(9, 5, 32, "/hero.png");
        alive = true;
    }

    public void move(int direction) {
        switch(direction) {
            case Constants.UP:
                break;
            case Constants.RIGHT:
                this.moving = true;
                this.direction = Constants.RIGHT;
                sprite.setLimits(19, 24);
                sprite.setFrameNumber(19);
                this.offsetX += 1/(double) framesPerMovement;
                break;
            case Constants.DOWN:
                break;
            case Constants.LEFT:
                this.moving = true;
                this.direction = Constants.LEFT;
                sprite.setLimits(25, 30);
                sprite.setFrameNumber(25);
                this.offsetX += 1/(double) framesPerMovement;
                break;
            default:
                break;
        }
    }

    public boolean isAlive() {
        return this.alive;
    }

    public void step() {
        if(sprite.incrementFrameNumber()) {
            moving = false;
        }

        switch(direction) {
            case Constants.UP:
                break;
            case Constants.RIGHT:
                if(moving) {
                    this.offsetX += 1/(double) framesPerMovement;
                } else {
                    this.incX();
                    this.offsetX = Math.round(this.offsetX);
                    sprite.setFrameNumber(18);
                }
                break;
            case Constants.DOWN:
                break;
            case Constants.LEFT:
                if(moving) {
                    this.offsetX -= 1/(double) framesPerMovement;
                } else {
                    this.decX();
                    this.offsetX = Math.round(this.offsetX);
                    sprite.setFrameNumber(24);
                }
                break;
            default:
                break;
        }
    }

    public boolean hasDog() {
        return this.hasDog;
    }

    public void catchDog() {
        this.hasDog = true;
    }

}
