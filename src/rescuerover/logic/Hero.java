package rescuerover.logic;

public class Hero extends MapObject implements Movable {

    private Map map;
    private boolean hasDog;
    private int framesPerMovement;
    private int framesLeft;
    private boolean alive;
    private int xInit, yInit;

    public Hero(int x, int y, int direction, Map map) {
        super(x, y, direction);
        this.map = map;
        framesPerMovement = 6;
        sprite = new Sprite(9, 5, 32, "/hero.png");
        alive = true;
        this.xInit = x;
        this.yInit = y;
    }

    public void move(int direction) {
        switch (direction) {
            case Constants.UP:
                if (map.isValidMove(this.x, this.y - 1)) {
                    this.direction = Constants.UP;
                    this.moving = true;
                    if (map.isKillingTile(this.x, this.y - 1)) {
                        this.alive = false;
                    }
                    if(map.isDogTile(this.x, this.y - 1)){
                        catchDog();
                    }
                    sprite.setLimits(10, 15);
                    sprite.setFrameNumber(10);

                } else {
                    sprite.setFrameNumber(9);
                }
                break;
            case Constants.RIGHT:
                if (map.isValidMove(this.x + 1, this.y)) {
                    this.moving = true;
                    this.direction = Constants.RIGHT;
                    if (map.isKillingTile(this.x + 1, this.y)) {
                        this.alive = false;
                    }
                    if(map.isDogTile(this.x + 1, this.y)){
                        catchDog();
                        map.setDogWithHero();
                    }
                    sprite.setLimits(19, 24);
                    sprite.setFrameNumber(19);
                } else {
                    sprite.setFrameNumber(16);
                }

                break;
            case Constants.DOWN:
                if (map.isValidMove(this.x, this.y + 1)) {
                    this.moving = true;
                    this.direction = Constants.DOWN;
                    if (map.isKillingTile(this.x, this.y + 1)) {
                        this.alive = false;
                    }
                    if(map.isDogTile(this.x, this.y + 1)){
                        catchDog();
                        map.setDogWithHero();
                    }
                    sprite.setLimits(3, 8);
                    sprite.setFrameNumber(3);
                } else {
                    sprite.setFrameNumber(1);
                }
                break;
            case Constants.LEFT:
                if (map.isValidMove(this.x - 1, this.y)) {
                    this.moving = true;
                    this.direction = Constants.LEFT;
                    if (map.isKillingTile(this.x - 1, this.y)) {
                        this.alive = false;
                    }
                    if(map.isDogTile(this.x - 1, this.y)){
                        catchDog();
                        map.setDogWithHero();
                    }
                    System.out.println("Has dog: " + this.hasDog());
                    sprite.setLimits(28, 33);
                    sprite.setFrameNumber(28);
                } else {
                    sprite.setFrameNumber(25);
                }
                break;
            default:
                break;
        }
    }

    public boolean isAlive() {
        return this.alive;
    }

    public void step() {
        if (sprite.incrementFrameNumber()) {
            moving = false;
        }

        switch (direction) {
            case Constants.UP:
                if (moving) {
                    this.offsetY -= 1 / (double) framesPerMovement;
                } else {
                    this.decY();
                    this.offsetY = this.getY();
                    sprite.setFrameNumber(9);
                    map.tileMap.setPosition(
                            this.getX() - Constants.VISIBLE_TILES / 2,
                            this.getY() - Constants.VISIBLE_TILES / 2);
                }
                break;
            case Constants.RIGHT:
                if (moving) {
                    this.offsetX += 1 / (double) framesPerMovement;
                } else {
                    this.incX();
                    this.offsetX = this.getX();
                    sprite.setFrameNumber(16);
                    map.tileMap.setPosition(
                            this.getX() - Constants.VISIBLE_TILES / 2,
                            this.getY() - Constants.VISIBLE_TILES / 2);
                }
                break;
            case Constants.DOWN:
                if (moving) {
                    this.offsetY += 1 / (double) framesPerMovement;
                } else {
                    this.incY();
                    this.offsetY = this.getY();
                    sprite.setFrameNumber(1);
                    map.tileMap.setPosition(
                            this.getX() - Constants.VISIBLE_TILES / 2,
                            this.getY() - Constants.VISIBLE_TILES / 2);
                }
                break;
            case Constants.LEFT:
                if (moving) {
                    this.offsetX -= 1 / (double) framesPerMovement;
                } else {
                    this.decX();
                    this.offsetX = this.getX();
                    sprite.setFrameNumber(25);
                    map.tileMap.setPosition(
                            this.getX() - Constants.VISIBLE_TILES / 2,
                            this.getY() - Constants.VISIBLE_TILES / 2);
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

    public boolean isAtEnd(){
        if(this.hasDog() && this.x == xInit && this.y == yInit){
            return true;
        }
        return false;
    }

}
