/*
 * Main class for building GUI and starting game
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {
	
	public static Administrator admin; // made static so it can be accessed in Menu class
	private static JPanel gamePanel;
	
	public static void startGame() {
		buildGUI();
	}
	
	public static void buildGUI() {
		// top level container
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		// main panel within frame
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
		contentPane.setPreferredSize(new Dimension(550, 675));
		frame.setContentPane(contentPane);

		// gameplay panel
		gamePanel = new JPanel(new BorderLayout());
		gamePanel.setPreferredSize(new Dimension(550, 550));
		contentPane.add(gamePanel);

		// spacer panel
		JPanel spacerPanel = new JPanel(new BorderLayout());
		spacerPanel.setPreferredSize(new Dimension(550, 35));
		spacerPanel.setBackground(Color.DARK_GRAY);
		contentPane.add(spacerPanel);

		// menu panel
		JPanel menuPanel = new JPanel(new BorderLayout());
		menuPanel.setPreferredSize(new Dimension(550, 90));
		menuPanel.setBackground(Color.WHITE);
		contentPane.add(menuPanel);

		// create Grid, add to gameplay panel
		Grid grid = new Grid();
		gamePanel.add(grid);		
		// create Menu, add to menu panel
		Menu menu = new Menu();
		Menu.updateTimer();
		menuPanel.add(menu.getMenu());

		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}

	public static void run() {
		new LoginForm();
		admin = new Administrator("admin", "admin");
		// add StandardUsers so some users pre-exist
		admin.add(new StandardUser("Morgan","123"));
		admin.add(new StandardUser("Chen","123"));
		admin.add(new StandardUser("Wenting", "123"));
		admin.add(new StandardUser("Dan", "123"));
	}

}
