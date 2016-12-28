package vacuumCleaner;

import java.util.ArrayList;
import java.util.Random;

public class RealMap extends MapPanel {
	
	public RealMap(int rows, int cols) {
		super(rows, cols);
		initializeDirtyCells();
		setObstaculizedWalls();
	}

	public void resizeMap(int newRows, int newCols) {
		super.resizeMap(newRows, newCols);
		initializeDirtyCells();
		setObstaculizedWalls();
	}
	
	/**
	 * Fills the walls with obstacles around the map.
	 */
	private void setObstaculizedWalls() {
		for (int i = 0; i < getNrows(); i++) {
			for (int j = 0; j < getNcols(); j++) {
				if (i == 0 || i == (getNrows() - 1) || j == 0 || j == (getNcols() - 1)) {
					setObstacleAtPos(i, j);
				}
			} 
		}
	}
	
	/**
	 * Generates random obstacles through the map
	 * given a percentage of randomness.
	 * 
	 * @param rndPercentage
	 */
	public void generateObstaclesRandomly(int rndPercentage) {
		int nrowsAvailable = getNrows() - 2;
		int ncolsAvailable = getNcols() - 2;
		ArrayList<String> positionsAvailables = new ArrayList<String>();
		
		for (int i = 1; i <= nrowsAvailable; i++) {
			for (int j = 1; j <= ncolsAvailable; j++) {
				positionsAvailables.add(i + " " + j);
			}
		}
		
		Random rn = new Random();
		int numberObstacles = (int)((rndPercentage * (nrowsAvailable * ncolsAvailable)) / 100);
		int currentNumberObstacles = 0;
		
		while (currentNumberObstacles < numberObstacles) {	
			int positionArray = rn.nextInt(positionsAvailables.size());
			String position = positionsAvailables.get(positionArray);
			String[] rowCol = position.split(" ");
			int row = Integer.parseInt(rowCol[0]);
			int column = Integer.parseInt(rowCol[1]);

			setObstacleAtPos(row, column);
			
			positionsAvailables.remove(positionArray);
			currentNumberObstacles++;
		}
	}
}
