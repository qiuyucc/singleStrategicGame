/*
 * Cell class. Grid is composed of individual Cells. 
 */
public class Cell {
	/*
	 * status indicates whether the cell can be traversed
	 * 1 = path
	 * 2 = intersection
	 * 3 = out of play
	 * default cell size = 50
	 */
	private int cellX;
	private int cellY;
	private int status;
	
	public Cell(int cellX, int cellY, int status) {
		this.cellX = cellX;
		this.cellY = cellY;
		this.setStatus(status);
	}

	public int getCellX() {
		return cellX;
	}

	public void setCellX(int cellX) {
		this.cellX = cellX;
	}

	public int getCellY() {
		return cellY;
	}

	public void setCellY(int cellY) {
		this.cellY = cellY;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
