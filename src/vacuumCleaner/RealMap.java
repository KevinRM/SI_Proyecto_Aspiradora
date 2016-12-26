package vacuumCleaner;

public class RealMap extends MapPanel {
	
	public RealMap(int rows, int cols) {
		super(rows, cols);
		initializeDirtyCells();
		setObstaculizedWalls();
	}

	private void setObstaculizedWalls() {
		
	}
	
	/**
	 * Generates random obstacles through the map
	 * given a percentage of randomness.
	 * @param rndPercentage
	 */
	public void generateObstaclesRandomly(int rndPercentage) {
		
	}
	
	// mover este bloque de código a appcontroller.java
	/*private class motionListener implements MouseMotionListener{
		@Override
		public void mouseDragged(MouseEvent e) {
			int eventX = (e.getX()-(zeroX))/cellSide;
			int eventY = (e.getY()-(zeroY))/cellSide;
			if (eventX < ncols && eventY < nrows){
				if (pointerOption == VACUUM){
					if (!vacuum){
						cells[eventX][eventY] = pointerOption;
						vacuum = true;
					}
				} else if (cells[eventX][eventY] == VACUUM && pointerOption != VACUUM){
					cells[eventX][eventY] = pointerOption;
					vacuum = false;
				} else if (cells[eventX][eventY] != VACUUM && pointerOption != VACUUM){
					cells[eventX][eventY] = pointerOption;
				}
				
			}
			repaint();
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub

		}
	}*/
}
