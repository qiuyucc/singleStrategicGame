/*
 * Administrator class. 
 * A type of user, but maintains information about StandardUsers
 */
import java.util.ArrayList;

public class Administrator extends User {

	public Administrator(String username, String password) {
		super(username, password);
	}

	// ArrayList to hold User objects
	@SuppressWarnings("unused")
	private User currentUser;
	private static ArrayList<StandardUser> userList = new ArrayList<StandardUser>();
	
	public void add(StandardUser u) {
		userList.add(u);
	}
	
	public ArrayList<StandardUser> getUserList() {
		return userList;
	}
	
	public User getLastUser() {
		return userList.get(userList.size()-1);
	}
	
	public void removeUser(User user) {
		userList.remove(user);
	}
	
	public void setCurrentUser(User user) {
		currentUser = user;
	}
	
}
