/*
 * Player class, subclass of Character
 */
public class Player extends Character {
	
	private Food[] food;
	private int foodCount;

	public Player(int charX, int charY) {
		super(charX, charY);
		food = new Food[2];
		foodCount = 2;
		makeFood();
	}
	
	public Food dropFood() {
		// check if Food is available, then create Food object on Grid
		if (foodCount == 2) {
			foodCount--;
			return food[0];
		} else if (foodCount == 1) {
			foodCount--;
			return food[1];
		} else {
			return null;
		}
	}
	
	private void makeFood() {
		// create 2 Food objects
		for (int i = 0; i < 2; i++) {
			food[i] = new Food();
		}
	}
	
	public void movePlayer() {
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
