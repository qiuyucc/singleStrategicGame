/*
 * Grid class to perform all gameplay functions
 * All Cells, Players, Monsters to be initialized from Grid
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Grid extends JPanel implements KeyListener, ActionListener {
	
	private Cell[] cells = new Cell[121];
	private Player player = new Player(0, 0);
	private Monster[] monsters = new Monster[2];
	private Food[] foodArray = new Food[2];
	private int[] intersectionCells = {0, 5, 10, 55, 60, 65, 110, 115, 120};
	private int[] pathCells = {1, 2, 3, 4, 6, 7, 8, 9, 11, 16, 21, 22, 27, 32,
			33, 38, 43, 44, 49, 54, 56, 57, 58, 59, 61, 62, 63, 64, 66, 71,
			76, 77, 82, 87, 88, 93, 98, 99, 104, 109, 111, 112, 113, 114,
			116, 117, 118, 119};
	private Timer timer;
	private int delay = 200;
	private boolean hasStarted = false;
	private HashMap<String, Cell> cellsHashMap;
	private int lastKey; // int value of last key pressed
	private boolean wasPressed = false; // to check if a key was pressed but direction did not change
	private ImageIcon playerImage;
	private ImageIcon[] monsterImages;
	private long time;
	private static int timeCounter;
	private int gameLength;
	private int foodCount = 0;
	
	public Grid() {
		makeCells();
		makeMonsters();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		time = System.currentTimeMillis();
		timeCounter = 0;
		gameLength = 100;
	}
	
	private void incrementTime() {
		if (System.currentTimeMillis() >= (time + 1000)) {
			time = System.currentTimeMillis();
			timeCounter++;
			Menu.updateTimer();
		}
	}
	
	public static int getTimeCounter() {
		return timeCounter;
	}
	
	private void checkWinningCondition() {
		if (timeCounter >= gameLength) {
			timer.stop();
			// display Game Win message
			Menu.showGameWin();
		}
	}
	
	private void checkIfFoodEaten() {
		for (int i = 0; i < foodCount; i++) {
			for (Monster m: monsters) {
				if (m.getCharX() == foodArray[i].getX() && m.getCharY() == foodArray[i].getY()) {
					m.setSpeed(25);
					foodArray[i].setEaten(true);
				}
			}
		}
	}
	
	public void setGameLength(int length) {
		gameLength = length;
	}
	
	public void makeCells() {
		// create cells with x, y coordinates and default status of 3
		cellsHashMap = new HashMap<String, Cell>();
		ArrayList<Cell> cellsArrayList = new ArrayList<Cell>();
		for (int i=0; i<550; i+=50) {
			for (int y=0; y<550; y+=50) {
				Cell c = new Cell(i, y, 3);
				String index = Integer.toString(i).concat(Integer.toString(y));
				cellsHashMap.put(index, c);
				cellsArrayList.add(c);
			}
		}
		// add created cells into main cells array
		for (int i=0; i<cells.length; i++) {
			cells[i] = cellsArrayList.get(i);
		}
		for (int i: intersectionCells) {
			cells[i].setStatus(2);
		}
		for (int i: pathCells) {
			cells[i].setStatus(1);
		}
	}
	
	public void makeMonsters() {
		monsters[0] = new Monster(250, 250);
		monsters[1] = new Monster(500, 500);
		monsterImages = new ImageIcon[2];
		monsterImages[0] = new ImageIcon("monster1.png");
		monsterImages[1] = new ImageIcon("monster2.png");
	}
	
	public void paintCells(Graphics g) {
		int gridX = 0;
		int gridY = 0;
		for (Cell c: cells) {
			if (c.getStatus() == 1) {
				g.setColor(Color.BLACK);
				g.drawRect(gridX, gridY, 50, 50);
				g.setColor(Color.WHITE);
				g.fillRect(gridX, gridY, 50, 50);
			} else if (c.getStatus() == 2) {
				g.setColor(Color.BLACK);
				g.drawRect(gridX, gridY, 50, 50);
				g.setColor(Color.GREEN);
				g.fillRect(gridX, gridY, 50, 50);
			} else {
				g.setColor(Color.GRAY);
				g.fillRect(gridX, gridY, 50, 50);
			}
			gridX += 50;
			if (gridX > 500) {
				gridX = 0;
				gridY += 50;
			}
		}
	}
	
	public void paintPlayer(Graphics g) {
		int playerX = player.getCharX();
		int playerY = player.getCharY();
		playerImage = new ImageIcon("player.png");
		playerImage.paintIcon(this, g, playerX, playerY);
	}
	
	public void paintMonster(Graphics g) {
		for (int i=0; i<monsters.length; i++) {
			int monsterX = monsters[i].getCharX();
			int monsterY = monsters[i].getCharY();
			monsterImages[i].paintIcon(this, g, monsterX, monsterY);
		}
	}
	
	public void paintFood(Graphics g) {
		for (Food food: foodArray) {
			if (food != null && !(food.getEaten())) {
				g.setColor(Color.BLACK);
				g.fillOval(food.getX(), food.getY(), 50, 50);
			}
		}
	}
	
	public void paint(Graphics g) {
		paintCells(g);
		paintPlayer(g);
		paintMonster(g);
		paintFood(g);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		incrementTime();
		checkWinningCondition();
		if (foodCount > 0) {
			checkIfFoodEaten();
		}
		// if a direction button was pressed, 
		// but direction did not change due to not being on an intersection,
		// keep calling changeDir() until the change happens
		if (wasPressed) {
			changeDir();
		}
		player.movePlayer();
		repaint();
		moveMonsters();
	}
	
	private void moveMonsters() {
		if (hasStarted) {
			pickRandomDir(monsters[0]); // strategy for monster 1
			checkDifference(monsters[1]); // strategy for monster 2
			checkLosingCondition();
		}
	}
	
	private void checkLosingCondition() {
		for (Monster m: monsters) {
			if (m.getCharX() == player.getCharX() && m.getCharY() == player.getCharY()) {
				timer.stop();
				Menu.showGameOver();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}
	
	public void changeDir() {
		if (lastKey == KeyEvent.VK_UP) {
			turnUp(player);
		}
		if (lastKey == KeyEvent.VK_DOWN) {
			turnDown(player);
		}
		if (lastKey == KeyEvent.VK_LEFT) {
			turnLeft(player);
		}
		if (lastKey == KeyEvent.VK_RIGHT) {
			turnRight(player);
		}
	}
	
	// Character steering methods, applicable to Player and Monster objects
	public void turnUp(Character c) {
		if (!c.getUp()) {
			int charX = c.getCharX();
			int charY = c.getCharY() - 50;
			if (charY < 0) {
				return;
			}
			if (charX % 50 == 0 && charY % 50 == 0) {
				String index = Integer.toString(charX).concat(Integer.toString(charY));
				Cell nextCell = cellsHashMap.get(index);
				if (nextCell.getStatus() == 3) {
					c.setUp(false);
				} else {
					c.setUp(true);
					c.setDown(false);
					c.setLeft(false);
					c.setRight(false);
					if (c instanceof Player) {
						wasPressed = false;
					}
				}
			}
		}
	}
	
	public void turnDown(Character c) {
		if (!c.getDown()) {
			int charX = c.getCharX();
			int charY = c.getCharY() + 50;
			if (charY > 500) {
				return;
			}
			if (charX % 50 == 0 && charY % 50 == 0) {
				String index = Integer.toString(charX).concat(Integer.toString(charY));
				Cell nextCell = cellsHashMap.get(index);
				if (nextCell.getStatus() == 3) {
					c.setDown(false);
				} else {
					c.setUp(false);
					c.setDown(true);
					c.setLeft(false);
					c.setRight(false);
					if (c instanceof Player) {
						wasPressed = false;
					}
				}
			}
		}
	}
	
	public void turnLeft(Character c) {
		if (!c.getLeft()) {
			int charX = c.getCharX() - 50;
			int charY = c.getCharY();
			if (charX < 0) {
				return;
			}
			if (charX % 50 == 0 && charY % 50 == 0) {
				String index = Integer.toString(charX).concat(Integer.toString(charY));
				Cell nextCell = cellsHashMap.get(index);
				if (nextCell.getStatus() == 3) {
					c.setLeft(false);
				} else {
					c.setUp(false);
					c.setDown(false);
					c.setLeft(true);
					c.setRight(false);
					if (c instanceof Player) {
						wasPressed = false;
					}
				}
			}
		}
	}
	
	public void turnRight(Character c) {
		if (!c.getRight()) {
			int charX = c.getCharX() + 50;
			int charY = c.getCharY();
			if (charX > 500) {
				return;
			}
			if (charX % 50 == 0 && charY % 50 == 0) {
				String index = Integer.toString(charX).concat(Integer.toString(charY));
				Cell nextCell = cellsHashMap.get(index);
				if (nextCell.getStatus() == 3) {
					c.setRight(false);
				} else {
					c.setUp(false);
					c.setDown(false);
					c.setLeft(false);
					c.setRight(true);
					if (c instanceof Player) {
						wasPressed = false;
					}
				}
			}
		}
	}

	//@Override
	public void keyPressed(KeyEvent e) {
		timer.start();
		hasStarted = true;
		if (e.getKeyCode() == KeyEvent.VK_UP) { 
			lastKey = e.getKeyCode();
			wasPressed = true;
			turnUp(player);
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			lastKey = e.getKeyCode();
			wasPressed = true;
			turnDown(player);
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			lastKey = e.getKeyCode();
			wasPressed = true;
			turnLeft(player);
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			lastKey = e.getKeyCode();
			wasPressed = true;
			turnRight(player);
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			if (timer.isRunning()) {
				timer.stop();
				Menu.showPause();
			} else {
				timer.start();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (foodCount < 2) {
				Food food = player.dropFood();
				food.setX(player.getCharX());
				food.setY(player.getCharY());
				foodArray[foodCount] = food;
				foodCount++;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}
	
	// STRATEGY: Follow player
	public void checkDifference(Character m) {
		if (Math.abs(m.getCharX() - player.getCharX()) > Math.abs(m.getCharY() - player.getCharY())) {
			if (m.getCharX() > player.getCharX()) {
				turnLeft(m);
			} else {
				turnRight(m);
			}
		} else {
			if (m.getCharY() > player.getCharY()) {
				turnUp(m);
			} else {
				turnDown(m);
			}
		}
		((Monster) m).moveMonster();
	}
	
	// STRATEGY: Random direction
	public void pickRandomDir(Character m) {
		int monsterX = m.getCharX();
		int monsterY = m.getCharY();
		if (monsterX % 50 == 0 & monsterY % 50 == 0) {
			String index = Integer.toString(monsterX).concat(Integer.toString(monsterY));
			Cell currentCell = cellsHashMap.get(index);
			if (currentCell.getStatus() == 2) {
				int choice = (int)(Math.random()*4);
				if (choice == 0) {
					turnUp(m);
				}
				if (choice == 1) {
					turnDown(m);
				}
				if (choice == 2) {
					turnLeft(m);
				}
				if (choice == 3) {
					turnRight(m);
				}
			}
		}
		((Monster) m).moveMonster();
	}
	
}
