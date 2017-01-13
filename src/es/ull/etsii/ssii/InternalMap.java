package es.ull.etsii.ssii;

import java.awt.Point;

public class InternalMap extends MapPanel {
	
	public InternalMap(int rows, int cols) {
		super(rows, cols);
		initializeUnknownCells();
	}
	
	public void resizeMap(int newRows, int newCols) {
		super.resizeMap(newRows, newCols);
		initializeUnknownCells();
	}
	
	public boolean dirtyAreas(){
		return super.dirtyAreas();
	}
	public Point nearest( int row, int col){
		return super.nearest (row, col);
	}
}
