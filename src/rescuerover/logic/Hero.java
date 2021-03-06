package rescuerover.logic;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class Hero extends MapObject implements Visitable {

    private Map map;
    private boolean hasDog;
    private int framesPerMovement;
    private int framesLeft;
    private boolean alive;
    private int xInit, yInit;
    private Key key;
    private boolean hasKey;

    public Hero(int x, int y, int direction, Map map) {
        super(x, y, direction);
        this.map = map;
        framesPerMovement = 6;
        sprite = new Sprite(9, 5, 32, "/sprites/hero.png");
        alive = true;
        this.xInit = x;
        this.yInit = y;
        this.key = null;
        this.hasKey = false;
    }

    /**
     * Moves the hero according to the direction chosen
     * Verifies if the movement is valid and sets the sprites
     * accordingly to the movement.
     * @param direction Direction to move
     */
    public void move(int direction) {
        switch (direction) {
            case Constants.UP:
                this.direction = Constants.UP;
                if (map.isValidMove(this.x, this.y - 1)) {
                    this.moving = true;
                    if (map.isKillingTile(this.x, this.y - 1)) {
                        this.alive = false;
                    } else if (map.isDogTile(this.x, this.y - 1)) {
                        catchDog();
                        map.setDogWithHero();
                    } else if (map.isKeyTile(this.x, this.y - 1)) {
                        if (this.hasKey())
                            dropKey();

                        getKey(this.x, this.y - 1);
                    } else if (this.hasKey) {
                        this.key.decY();
                    }

                    sprite.setLimits(10, 15);
                    sprite.setFrameNumber(10);

                } else {
                    sprite.setFrameNumber(9);
                }
                break;
            case Constants.RIGHT:
                this.direction = Constants.RIGHT;
                if (map.isValidMove(this.x + 1, this.y)) {
                    this.moving = true;
                    if (map.isKillingTile(this.x + 1, this.y)) {
                        this.alive = false;
                    } else if (map.isDogTile(this.x + 1, this.y)) {
                        catchDog();
                        map.setDogWithHero();
                    } else if (map.isKeyTile(this.x + 1, this.y)) {
                        if (this.hasKey())
                            dropKey();

                        getKey(this.x + 1, this.y);
                    } else if (this.hasKey) {
                        this.key.incX();
                    }
                    sprite.setLimits(19, 24);
                    sprite.setFrameNumber(19);
                } else {
                    sprite.setFrameNumber(16);
                }

                break;
            case Constants.DOWN:
                this.direction = Constants.DOWN;
                if (map.isValidMove(this.x, this.y + 1)) {
                    this.moving = true;
                    if (map.isKillingTile(this.x, this.y + 1)) {
                        this.alive = false;
                    } else if (map.isDogTile(this.x, this.y + 1)) {
                        catchDog();
                        map.setDogWithHero();
                    } else if (map.isKeyTile(this.x, this.y + 1)) {
                        if (this.hasKey())
                            dropKey();

                        getKey(this.x, this.y + 1);
                    } else if (this.hasKey) {
                        this.key.incY();
                    }
                    sprite.setLimits(3, 8);
                    sprite.setFrameNumber(3);
                } else {
                    sprite.setFrameNumber(1);
                }
                break;
            case Constants.LEFT:
                this.direction = Constants.LEFT;
                if (map.isValidMove(this.x - 1, this.y)) {
                    this.moving = true;
                    if (map.isKillingTile(this.x - 1, this.y)) {
                        this.alive = false;
                    } else if (map.isDogTile(this.x - 1, this.y)) {
                        catchDog();
                        map.setDogWithHero();
                    } else if (map.isKeyTile(this.x - 1, this.y)) {
                        if (this.hasKey())
                            dropKey();

                        getKey(this.x - 1, this.y);
                    } else if (this.hasKey) {
                        this.key.decX();
                    }
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

    private void getKey(int x, int y) {
        for (MapObject obj : this.map.objectsMap.getObjects()) {
            if (obj instanceof Key) {
                if (obj.getX() == x && obj.getY() == y) {
                    this.key = (Key) obj;
                    ((Key) obj).setWithHero(true);
                    this.hasKey = true;
                    if(!Constants.MUTED) {
                        playSound();
                    }
                }
            }
        }
    }

    private void playSound() {

        URL url;
        try {
            // Open an audio input stream.

            url = this.getClass().getClassLoader().getResource("sounds/item.mid");

            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            // Get a sound clip resource.
            Clip clip = AudioSystem.getClip();

            // Open audio clip and load samples from the audio input stream.
            clip.open(audioIn);
            //clip.start();
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

    }

    private void dropKey() {
        this.key.setWithHero(false);
        this.key = null;
        this.hasKey = false;
    }

    public boolean isAlive() {
        return this.alive;
    }

    /**
     * Moves the hero 1 step, incrementing
     * the frame number, thus generating
     * an animation
     */
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

    public boolean isAtEnd() {
        if (this.hasDog() && this.x == xInit && this.y == yInit) {
            return true;
        }
        return false;
    }

    public boolean hasKey() {
        return hasKey;
    }

    public Key getKey() {
        return key;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void kill() {
        this.alive=false;
    }
}
