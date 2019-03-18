/*
 * StandardUser class, for users playing the game
 */
public class StandardUser extends User {
	
	private int currentScore;
	private int highScore;
	
	public StandardUser(String username, String password) {
		super(username, password);
		currentScore = 0;
		highScore = 0;
	}
	
	public int getCurrentScore() {
		return currentScore;
	}

	public void setCurrentScore(int currentScore) {
		this.currentScore = currentScore;
	}

	public int getHighScore() {
		return highScore;
	}

	public void setHighScore(int highScore) {
		this.highScore = highScore;
	}

}
