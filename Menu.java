/*
 * Menu class, shown below Grid during gameplay
 */
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class Menu extends JFrame implements ActionListener {
	
	private static Container container = new Container();
	// private Player p1;
	private static JButton startButton = new JButton("Start Game");
	private static JButton upButton = new JButton("UP");
	private static JButton downButton = new JButton("Down");
	private static JButton leftButton = new JButton("LEFT");
	private static JButton rightButton = new JButton("RIGHT");
	private static String timeCounter = String.valueOf(Grid.getTimeCounter());
	private static JLabel timerLabel = new JLabel(timeCounter);
	
	
	public Menu() {
		setLayoutManager();
		//setLocationAndSize();
		addComponentsToContainer();
		addActionEvent();
	}
	
	public static void updateTimer() {
		timerLabel.setText(String.valueOf(Grid.getTimeCounter()));
	}
	
	public static void showPause() {
		startButton.setText("PAUSED");
	}
	
	public static void showGameWin() {
		JOptionPane.showMessageDialog(container, "You Won!");
	}
	
	public static void showGameOver() {
		JOptionPane.showMessageDialog(container, "Game Over...");
	}
	
	public void setLayoutManager() {
		container.setLayout(new FlowLayout());
	}

//	private void setLocationAndSize() 
//	{
//		startButton.setBounds(420,40,100,30);
//		upButton.setBounds(20, 40, 80, 30);
//		downButton.setBounds(120, 40, 80, 30);
//		leftButton.setBounds(220, 40, 80, 30);
//		rightButton.setBounds(320, 40, 80, 30);
//		timerLabel.setBounds(420, 10, 40, 30);
//	}
	
	
	private void addComponentsToContainer() {
		container.add(rightButton);
		container.add(leftButton);
		container.add(upButton);
		container.add(downButton);
		container.add(startButton);
		container.add(timerLabel);
	}
	
	private void addActionEvent() {
		startButton.addActionListener(this);
		rightButton.addActionListener(this);
		leftButton.addActionListener(this);
		downButton.addActionListener(this);
		upButton.addActionListener(this);
	}
	
	public Container getMenu() {
		return container;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().compareTo("Start Game") == 0) {
			Main.startGame();
		}
		if (e.getActionCommand().compareTo("UP") == 0) {
			
		}
		if (e.getActionCommand().compareTo("DOWN") == 0) {
			
		}
		if (e.getActionCommand().compareTo("LEFT") == 0) {
			
		}
		if (e.getActionCommand().compareTo("RIGHT") ==0) {
		
		}
	}

}
