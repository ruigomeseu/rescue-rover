package rescuerover.logic;

public class Position {
	private int x;
	private int y;
	private int direction;
	
	Sprite sprite;
	
	public Position(int x, int y, int direction) {
		this.x = x;
		this.y = y;
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
