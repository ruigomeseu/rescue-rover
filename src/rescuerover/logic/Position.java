package rescuerover.logic;

/**
 * A default class for all the map objects
 */
public class Position {
    protected int x;
    protected int y;

    public double getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(double offsetX) {
        this.offsetX = offsetX;
    }

    public double getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(double offsetY) {
        this.offsetY = offsetY;
    }

    protected double offsetX;
    protected double offsetY;
    protected int direction;
	
	Sprite sprite;
	
	public Position(int x, int y, int direction) {
		this.x = x;
		this.y = y;
        this.offsetX = x;
        this.offsetY = y;
		this.setDirection(direction);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void incX() {
		this.x++;
	}
	
	public void incY() {
		this.y++;
	}
	
	public void decX() {
		this.x--;
	}
	
	public void decY() {
		this.y--;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}
}
