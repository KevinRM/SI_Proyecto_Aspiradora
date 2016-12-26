package vacuumCleaner;

public class InternalMap extends MapPanel {
	
	public InternalMap(int rows, int cols) {
		super(rows, cols);
		initializeUnknownCells();
	}
	
	public void resizeMap(int newRows, int newCols) {
		super.resizeMap(newRows, newCols);
		initializeUnknownCells();
	}
}
