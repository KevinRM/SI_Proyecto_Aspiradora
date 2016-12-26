package vacuumCleaner;

public class RealMap extends MapPanel {
	
	public RealMap(int rows, int cols) {
		super(rows, cols);
		initializeDirtyCells();
		setObstaculizedWalls();
	}

	public void resizeMap(int newRows, int newCols) {
		super.resizeMap(newRows, newCols);
		initializeDirtyCells();
	}
	
	/**
	 * Fills the walls with obstacles around the map.
	 * KEVIN (BORRAR ESTA LÍNEA)
	 */
	private void setObstaculizedWalls() {
		
	}
	
	/**
	 * Generates random obstacles through the map
	 * given a percentage of randomness.
	 * 
	 * KEVIN (BORRAR ESTA LÍNEA)
	 * @param rndPercentage
	 */
	public void generateObstaclesRandomly(int rndPercentage) {
		// ...
		repaint();
	}
}
