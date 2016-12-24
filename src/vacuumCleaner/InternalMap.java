package vacuumCleaner;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class InternalMap extends JPanel {
	private static final Color VACUUM_DEFAULT_COLOR = Color.GREEN;
	private static final Color CLEAN_CELL_COLOR = Color.WHITE;
	private static final Color UNKNOWN_CELL_COLOR = Color.GRAY;
	private static final Color OBSTACLE_CELL_COLOR = Color.BLACK;
	private static final int R_DIRTY = 153;
	private static final int G_DIRTY = 128;
	private static final int B_DIRTY = 0;
	
	private int nrows;
	private int ncols;
	private CellState[][] cells;
	private Color vacuumColor;
	private boolean vacuumSet;
	
	public InternalMap() {
		
	}
	
	public InternalMap(int rows, int cols) {
		nrows = rows;
		ncols = cols;
		cells = new CellState[rows][cols];
		vacuumColor = VACUUM_DEFAULT_COLOR;
		vacuumSet = false;
		initializeCells();
	}
	
	/**
	 * Initialize all the cells as unknown.
	 */
	private void initializeCells() {
		for (int i = 0; i < getnRows(); i++) {
			for (int j = 0; j < getnCols(); j++) {
				getCells()[i][j] = CellState.UNKNOWN;
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
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int cellSpace = (int) Math.round(getCellPixelSize());
		int pixelRowStart = 0;
		int pixelColStart = 0;
		
		// First pixel coordinates
		pixelRowStart = (int) ((getHeight() / 2) - ((int) cellSpace * (getnRows() / 2)));	
		pixelColStart = (int) ((getWidth() / 2) - ((int) cellSpace * (getnCols() / 2)));	
		
		// Cells
		for (int i = 0; i < getnRows(); i++) {
			for (int j = 0; j < getnCols(); j++) {
				switch (getCells()[i][j]) {
					case DIRTY: g.setColor(new Color(R_DIRTY, G_DIRTY, B_DIRTY));
					case CLEAN: g.setColor(CLEAN_CELL_COLOR);
					case OBSTACLE: g.setColor(OBSTACLE_CELL_COLOR);
					case VACUUM: g.setColor(getVacuumColor());
					case UNKNOWN: g.setColor(UNKNOWN_CELL_COLOR);
				}
				
				g.fillRect(pixelColStart + j * cellSpace, pixelRowStart + i * cellSpace, 
						cellSpace, cellSpace);
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
	}
	
	public boolean setVacuumPos(int row, int col) {
		if (!vacuumSet) {
			getCells()[row][col] = CellState.VACUUM;
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
}
