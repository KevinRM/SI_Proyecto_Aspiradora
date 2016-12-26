package vacuumCleaner;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class InternalMap extends MapPanel {
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
	
	public InternalMap(int rows, int cols) {
		super(rows, cols);
		initializeUnknownCells();
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
