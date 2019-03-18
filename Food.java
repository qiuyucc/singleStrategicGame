/*
 * Food class. Player starts out with 2 Food objects, and can drop them on the Grid. 
 */
public class Food {
	
	private int x;
	private int y;
	private boolean eaten;
	
	public Food() {
		this.eaten = false;
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
	
	public boolean getEaten() {
		return eaten;
	}
	
	public void setEaten(boolean eaten) {
		this.eaten = true;
	}

}
