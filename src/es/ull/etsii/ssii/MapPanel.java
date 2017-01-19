package es.ull.etsii.ssii;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

public class MapPanel extends JPanel {
	private static final Color VACUUM_DEFAULT_COLOR = Color.GREEN;
	private static final Color CLEAN_CELL_COLOR = Color.WHITE;
	private static final Color UNKNOWN_CELL_COLOR = Color.GRAY;
	private static final Color OBSTACLE_CELL_COLOR = Color.BLACK;
	private static final int R_DIRTY = 153;
	private static final int G_DIRTY = 128;
	private static final int B_DIRTY = 0;
	
	private int nrows;
	private int ncols;
	public CellState[][] cells;
	private Color vacuumColor;
	private boolean vacuumSet;
	
	
	/**
	 * Info about how the map will be painted.
	 */
	private int cellSpace;
	private int pixelRowStart;
	private int pixelColStart;
	
	/**
	 * Info about how the line sensor will be painted.
	 */
	private int x1LineSensor;
	private int y1LineSensor;
	private int x2LineSensor;
	private int y2LineSensor;
	private boolean paintLineSensor = false;
	
	public MapPanel() {
		nrows = 0;
		ncols = 0;
		cells = null;
		vacuumColor = VACUUM_DEFAULT_COLOR;
		vacuumSet = false;
	
		cellSpace = 0;
		pixelRowStart = 0;
		pixelColStart = 0;
		
		x1LineSensor = 0;
		y1LineSensor = 0;
		x2LineSensor = 0;
		y2LineSensor = 0;
	}
	
	public MapPanel(int rows, int cols) {
		nrows = rows;
		ncols = cols;
		cells = new CellState[rows][cols];
		vacuumColor = VACUUM_DEFAULT_COLOR;
		vacuumSet = false;
		
		cellSpace = 0;
		pixelRowStart = 0;
		pixelColStart = 0;
		
		x1LineSensor = 0;
		y1LineSensor = 0;
		x2LineSensor = 0;
		y2LineSensor = 0;
	}
	
	public void resizeMap(int newRows, int newCols) {
		nrows = newRows;
		ncols = newCols;
		cells = new CellState[newRows][newCols];
		vacuumColor = VACUUM_DEFAULT_COLOR;
		vacuumSet = false;
		repaint();
	}
	
	/**
	 * Initialize all the cells as unknown.
	 */
	protected void initializeUnknownCells() {
		setVacuumSet(false);
		for (int i = 0; i < getnRows(); i++) {
			for (int j = 0; j < getnCols(); j++) {
				getCells()[i][j] = CellState.UNKNOWN;
			}
		}
	}
	
	/**
	 * Initialize all the cells as dirty.
	 */
	protected void initializeDirtyCells() {
		setVacuumSet(false);
		for (int i = 0; i < getnRows(); i++) {
			for (int j = 0; j < getnCols(); j++) {
				getCells()[i][j] = CellState.DIRTY;
			}
		}
	}
	
	/**
	 * Returns the length of a cell to be SQUARE.
	 */
	private double getCellPixelSize() {
		if ((getnRows() > 0) && (getnCols() > 0)) {
			if ((getWidth() / getnCols()) <= (getHeight() / getnRows())) {
				return getWidth() / getnCols();
			} else {
				return getHeight() / getnRows();
			}
		}
		
		return -1;
	}
	
	public void drawLineSensor(boolean end, int x1, int y1, int x2, int y2) {
		if (end) {	// Clear red point of laser
			setPaintLineSensor(false);
			repaint();
		} else {
			setPaintLineSensor(true);
			x1LineSensor = x1;
			y1LineSensor = y1;
			x2LineSensor = x2;
			y2LineSensor = y2;
			repaint();
		}
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int cellSpace = (int) Math.round(getCellPixelSize());
		int pixelRowStart = 0;
		int pixelColStart = 0;
		boolean vacuumPainted;
		
		// First pixel coordinates
		pixelRowStart = (int) ((getHeight() / 2) - ((int) cellSpace * (getnRows() / 2)));	
		pixelColStart = (int) ((getWidth() / 2) - ((int) cellSpace * (getnCols() / 2)));	
		
		// Store cells dimension info
		setCellSpace(cellSpace);
		setPixelRowStart(pixelRowStart);
		setPixelColStart(pixelColStart);
		
		// Cells
		for (int i = 0; i < getnRows(); i++) {
			for (int j = 0; j < getnCols(); j++) {
				vacuumPainted = false;
				
				switch (getCells()[i][j]) {
					case DIRTY: 	g.setColor(new Color(R_DIRTY, G_DIRTY, B_DIRTY)); break;
					case CLEAN: 	g.setColor(CLEAN_CELL_COLOR); break;
					case OBSTACLE: 	g.setColor(OBSTACLE_CELL_COLOR); break;
					case VACUUM: 	g.setColor(getVacuumColor()); 
									g.fillOval(pixelColStart + j * cellSpace, 
											pixelRowStart + i * cellSpace, 
											cellSpace, 
											cellSpace);
									vacuumPainted = true;
									break;
					case UNKNOWN: 	g.setColor(UNKNOWN_CELL_COLOR); break;
				}
				
				if (!vacuumPainted) {
					g.fillRect(pixelColStart + j * cellSpace, pixelRowStart + i * cellSpace, 
							cellSpace, cellSpace);
				}
			}
		}
		
		g.setColor(Color.BLACK);
		
		// Horizontal lines
		for (int i = 0; i <= getnRows(); i++) {
			g.drawLine(pixelColStart, pixelRowStart + i * cellSpace, 
					pixelColStart + getnCols() * cellSpace, pixelRowStart + i * cellSpace);
		}
		
		// Vertical lines
		for (int j = 0; j <= getnCols(); j++) {
			g.drawLine(pixelColStart + j * cellSpace, pixelRowStart, pixelColStart + j * cellSpace, 
					pixelRowStart + getnRows() * cellSpace);
		}
		
		// Line sensor
		if (getPaintLineSensor()) {
			g.setColor(Color.RED);
			g.drawLine(x1LineSensor, y1LineSensor, x2LineSensor, y2LineSensor);
		}
	}
	
	public void changeVacuumColor(String newColor) {
		if (newColor.equals("BLUE")) {
			setVacuumColor(Color.BLUE);
		} 
		else if (newColor.equals("GREEN")) {
			setVacuumColor(Color.GREEN);
		} 
		else if (newColor.equals("PINK")) {
			setVacuumColor(Color.PINK);
		} 
		else if (newColor.equals("RED")) {
			setVacuumColor(Color.RED);
		} 
		else {
			System.out.println("Failed while changing vacuum color");
		}
	}
	
	public boolean setVacuumAtPos(int row, int col) {
		if (!isVacuumSet()) {
			getCells()[row][col] = CellState.VACUUM;
			setVacuumSet(true);
			return true;
		} else {
			return false;
		}
	}
	
	public void setObstacleAtPos(int row, int col) {
		if (getCells()[row][col] == CellState.VACUUM) {
			setVacuumSet(false);
		}
		
		getCells()[row][col] = CellState.OBSTACLE;
		repaint();
	}
	
	public void setCleanCell(int row, int col) {
		if (getCells()[row][col] == CellState.VACUUM) {
			setVacuumSet(false);
		}
		
		getCells()[row][col] = CellState.CLEAN;
	}
	public void setDirtyCell(int row, int col) {
		if (getCells()[row][col] == CellState.VACUUM) {
			setVacuumSet(false);
		}
		
		getCells()[row][col] = CellState.DIRTY;
		repaint();
	}
	
	public void setUnknownCell(int row, int col) {
		if (getCells()[row][col] == CellState.VACUUM) {
			setVacuumSet(false);
		}
		
		getCells()[row][col] = CellState.UNKNOWN;
	}

	public boolean isVacuum(int row, int col) {
		if (getCells()[row][col] == CellState.VACUUM) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isObstacle(int row, int col) {
		if (getCells()[row][col] == CellState.OBSTACLE) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isClean(int row, int col) {
		if (getCells()[row][col] == CellState.CLEAN) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isDirty(int row, int col) {
		if (getCells()[row][col] == CellState.DIRTY) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isUnknown(int row, int col) {
		if (getCells()[row][col] == CellState.UNKNOWN) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean dirtyAreas (){
		for (int i = 0; i < getnRows(); i++ ){
			for (int j = 0; j < getnCols(); j++){
				if (isDirty(i,j)){
					return true;
				}
			}
		}
		return false;
	}
	
	private int distance (int y, int x, int row, int col){
		return Math.abs(row-y)+ Math.abs(col-x);
	}

	public Point nearest (int row, int col){
		Point nearest = new Point();
		int distance = Integer.MAX_VALUE;
		for (int i = 0; i < cells[0].length; i++){
			for (int j = 0; j < cells.length; j++){
				if (cells[j][i] == CellState.DIRTY){
					if (distance (j, i, row, col) < distance  && distance (j, i, row, col) > 0){
						distance = distance (j, i, row, col);
						nearest.x = i;
						nearest.y = j;
					}
				}
			}
		}
		return nearest;
	}
	
	/****************************************************
	 *               Getters and Setters                *
	 ****************************************************/
	
	public int getnRows() {
		return nrows;
	}

	public void setnRows(int nrows) {
		this.nrows = nrows;
	}

	public int getnCols() {
		return ncols;
	}

	public void setnCols(int ncols) {
		this.ncols = ncols;
	}

	public int getNrows() {
		return nrows;
	}

	public void setNrows(int nrows) {
		this.nrows = nrows;
	}

	public int getNcols() {
		return ncols;
	}

	public void setNcols(int ncols) {
		this.ncols = ncols;
	}

	public CellState[][] getCells() {
		return cells;
	}

	public void setCells(CellState[][] cells) {
		this.cells = cells;
	}

	public Color getVacuumColor() {
		return vacuumColor;
	}

	public void setVacuumColor(Color vacuumColor) {
		this.vacuumColor = vacuumColor;
	}

	public boolean isVacuumSet() {
		return vacuumSet;
	}

	public void setVacuumSet(boolean vacuumSet) {
		this.vacuumSet = vacuumSet;
	}

	public int getCellSpace() {
		return cellSpace;
	}

	public void setCellSpace(int cellSpace) {
		this.cellSpace = cellSpace;
	}

	public int getPixelRowStart() {
		return pixelRowStart;
	}

	public void setPixelRowStart(int pixelRowStart) {
		this.pixelRowStart = pixelRowStart;
	}

	public int getPixelColStart() {
		return pixelColStart;
	}

	public void setPixelColStart(int pixelColStart) {
		this.pixelColStart = pixelColStart;
	}
	
	public boolean getPaintLineSensor() {
		return paintLineSensor;
	}
	
	public void setPaintLineSensor(boolean val) {
		paintLineSensor = val;
	}
}
