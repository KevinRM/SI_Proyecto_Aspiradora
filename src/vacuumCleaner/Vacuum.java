package vacuumCleaner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

public class Vacuum {
	private Floor2 floor;
	private static int rowPos = 0;
	private static int colPos = 0;
	private Timer timerAlgorithm;
	private int TIME_TO_TIMER = 1000;
	private InternalMap internalMap;
	
	Vacuum(Floor2 floor) {
		this.floor = floor;
		algorithmClean();
	}

	// Set position in the array to paint vacuum during the algorithm
	public static void setPosition(int row, int column) {
		rowPos = row;
		colPos = column;
	}

	private void algorithmClean() {
		timerAlgorithm = new Timer(TIME_TO_TIMER, new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*floor.getCells()[xPos][yPos].removeVacuumHere();
				yPos--;
				floor.setVacuum(xPos, yPos);*/
				// getSensorParameters para obtener si es obstaculo o limpia de las celdas de alrededor
				// floor.setVacuum(i, j) para poner la aspiradora en una determinada celda
			}
		});
	}
	
	public void getSensorParameters() {
	/*	ArrayList<Integer> xEmptyCells = new ArrayList<Integer>();
		ArrayList<Integer> yEmptyCells = new ArrayList<Integer>();
		ArrayList<Integer> xObstacleCells = new ArrayList<Integer>();
		ArrayList<Integer> yObstacleCells = new ArrayList<Integer>(); 
		
		// Centros del radio de sensado
		int xCenter = getColPos();
		int yCenter = getRowPos();
		
		// El radio del sensado puede ser variable
		int sensorRadius = 7;
		
		// Realizar un sensado en redondo
		for (int x = 0; x < getFloor().getNumberColumns(); x++) {
			for (int y = 0; y < getFloor().getNumberRows(); y++) {
				if ((x - xCenter) * (x - xCenter) + (y - yCenter) * (y - yCenter) < sensorRadius * sensorRadius) {
					// Si no es una posición obstaculizada
					if (!getFloor().obstaculizedCell(y, x)) {
						xEmptyCells.add(x);
						yEmptyCells.add(y);
					// Guardar la posición de los obstáculos
					} else {
						xObstacleCells.add(x);
						yObstacleCells.add(y);
					}
				}
			}
		}
		
		int xVacuumCell = getFloor().getCells()[yCenter][xCenter].getWidth() / 2;
		int yVacuumCell = getFloor().getCells()[yCenter][xCenter].getHeight() / 2;
		for (int i = 0; i < xObstacleCells.size(); i++) {
			
		}
		
		// Pintar las celdas que ve el sensor
		for (int i = 0; i < xEmptyCells.size(); i++) {
			floor.getCells()[yEmptyCells.get(i)][xEmptyCells.get(i)].setCleanCell();
		}
		*/
	}

	/**
	 * Execute the algorithm for cleaning the room
	 */
	public void startClean() {
		//timerAlgorithm.start();
		getSensorParameters();
	}

	/**
	 * Stop the algorithm for cleaning the room
	 */
	public void stopClean() {
		timerAlgorithm.stop();
	}

	public Floor2 getFloor() {
		return floor;
	}

	public void setFloor(Floor2 floor) {
		this.floor = floor;
	}

	public static int getRowPos() {
		return rowPos;
	}

	public static void setRowPos(int rowPos) {
		Vacuum.rowPos = rowPos;
	}

	public static int getColPos() {
		return colPos;
	}

	public static void setColPos(int colPos) {
		Vacuum.colPos = colPos;
	}

	public Timer getTimerAlgorithm() {
		return timerAlgorithm;
	}

	public void setTimerAlgorithm(Timer timerAlgorithm) {
		this.timerAlgorithm = timerAlgorithm;
	}

	public InternalMap getInternalMap() {
		return internalMap;
	}

	public void setInternalMap(InternalMap internalMap) {
		this.internalMap = internalMap;
	}
}