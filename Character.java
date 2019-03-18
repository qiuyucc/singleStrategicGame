/*
 * Character class, parent class of Player and Monster
 * Player and Monster share basic functionality - x and y location, and move methods
 */
public abstract class Character {

	// Instance variables
	protected int charX;
	protected int charY;
	private boolean up;
	private boolean down;
	private boolean left;
	private boolean right;
	
	// Constructor
	public Character(int charX, int charY) {
		this.charX = charX;
		this.charY = charY;
		this.up = false;
		this.down = false;
		this.left = false;
		this.right = false;
	}
	
	public void moveUp() {
		if (this.charY == 0) {
			this.constrainCharY1();
		} else {
			this.setCharY(-50);
		}
	}
	
	public void moveDown() {
		if (this.charY == 500) {
			this.constrainCharY2();
		} else {
			this.setCharY(50);
		}
	}
	
	public void moveLeft() {
		if (this.charX == 0) {
			this.constrainCharX1();
		} else {
			this.setCharX(-50);
		}
	}
	
	public void moveRight() {
		if (this.charX == 500) {
			this.constrainCharX2();
		} else {
			this.setCharX(50);
		}
	}
	
	public void constrainCharX1() {
		this.charX = 0;
	}

	public void constrainCharX2() {
		this.charX = 500;
	}

	public void constrainCharY1() {
		this.charY = 0;
	}

	public void constrainCharY2() {
		this.charY = 500;
	}

	public int getCharX() {
		return charX;
	}

	public void setCharX(int charX) {
		this.charX += charX;
	}

	public int getCharY() {
		return charY;
	}

	public void setCharY(int charY) {
		this.charY += charY;
	}

	public boolean getUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean getDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public boolean getLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean getRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}
	
}
