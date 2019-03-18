/*
 * Monster class, subclass of Character
 */
public class Monster extends Character {
	
	private int speed;
	
	public Monster(int charX, int charY) {
		super(charX, charY);
		this.speed = 50;
	}
	
	@Override
	public void moveUp() {
		if (this.charY == 0) {
			this.constrainCharY1();
		} else {
			this.setCharY(-speed);
		}
	}
	
	@Override
	public void moveDown() {
		if (this.charY == 500) {
			this.constrainCharY2();
		} else {
			this.setCharY(speed);
		}
	}
	
	@Override
	public void moveLeft() {
		if (this.charX == 0) {
			this.constrainCharX1();
		} else {
			this.setCharX(-speed);
		}
	}
	
	@Override
	public void moveRight() {
		if (this.charX == 500) {
			this.constrainCharX2();
		} else {
			this.setCharX(speed);
		}
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public void moveMonster() {
		if (getUp()) {
			moveUp();
		}
		if (getDown()) {
			moveDown();
		}
		if (getLeft()) {
			moveLeft();
		}
		if (getRight()) {
			moveRight();
		}
	}

}
