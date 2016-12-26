package vacuumCleaner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

public class Vacuum {
	private static final int DEFAULT_ANIMATION_TIME = 1000;
	private static final int DEFAULT_SENSOR_RANGE = 3;
	
	/**
	 * NOTA: revisar código de esta clase
	 * 
	 * 
	 * el algoritmo de sensado y limpieza se podría programar aquí mismo
	 */
	
	private int nrow;
	private int ncol;
	private RealMap realMap;
	private InternalMap internalMap;
	private int animationTime;
	private Timer animationTimer;
	private int sensorRange;
	
	Vacuum(RealMap aRealMap, InternalMap anInternalMap) {
		nrow = -1;
		ncol = -1;
		realMap = aRealMap;
		internalMap = anInternalMap;
		animationTime = DEFAULT_ANIMATION_TIME;
		animationTimer = null;
		sensorRange = DEFAULT_SENSOR_RANGE;
	}

	public void setNewPosition(int newRow, int newCol) {
		setnRow(newRow);
		setnCol(newCol);
	}
	
	/**
	 * Applies a sensor operation so that the agent can see what elements are within its sensor range.
	 * 
	 * KEVIN (borrar esta línea)
	 */
	public void applyObstacleSensor() {
		
	}
	
	// este metodo borrarlo... todavía no
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
		
		*/
	}

	/****************************************************
	 *               Getters and Setters                *
	 ****************************************************/
	
	public int getnRow() {
		return nrow;
	}

	public void setnRow(int nrow) {
		this.nrow = nrow;
	}

	public int getnCol() {
		return ncol;
	}

	public void setnCol(int ncol) {
		this.ncol = ncol;
	}

	public RealMap getRealMap() {
		return realMap;
	}

	public void setRealMap(RealMap realMap) {
		this.realMap = realMap;
	}

	public InternalMap getInternalMap() {
		return internalMap;
	}

	public void setInternalMap(InternalMap internalMap) {
		this.internalMap = internalMap;
	}

	public int getAnimationTime() {
		return animationTime;
	}

	public void setAnimationTime(int animationTime) {
		this.animationTime = animationTime;
	}

	public Timer getAnimationTimer() {
		return animationTimer;
	}

	public void setAnimationTimer(Timer animationTimer) {
		this.animationTimer = animationTimer;
	}
}