package es.ull.etsii.ssii;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.Timer;

import es.ull.etsii.ssii.aStar.AStar;
import es.ull.etsii.ssii.aStar.Node;

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
	private Timer timerSensor;
	public int xModifyOriginSensor = 0;	// To go of vacuum cell to explore cell
	public int yModifyOriginSensor = 0; // To go of vacuum cell to explore cell
	public int sectorExplore = 0;
	public boolean collisionSensorWithObstacle = false;
	public ArrayList<Integer[]> obstaclesDetected = new ArrayList<Integer[]>();
	VacuumMovement direction;
	private ArrayList <Node> path;
	private int camIndex;

	Vacuum(RealMap aRealMap, InternalMap anInternalMap) {
		nrow = -1;
		ncol = -1;
		realMap = aRealMap;
		internalMap = anInternalMap;
		animationTime = DEFAULT_ANIMATION_TIME;
		animationTimer = null;
		sensorRange = DEFAULT_SENSOR_RANGE;
	}

	public boolean dirtyNeighbor () {
		return internalMap.isDirty(nrow-1, ncol) || internalMap.isDirty(nrow+1, ncol) || internalMap.isDirty(nrow, ncol-1) || internalMap.isDirty(nrow, ncol+1);
	}

	public boolean unexploreNeighbor () {
		return internalMap.isUnknown(nrow-1, ncol) || internalMap.isUnknown(nrow+1, ncol) || internalMap.isUnknown(nrow, ncol-1) || internalMap.isUnknown(nrow, ncol+1);
	}

	public boolean isPosibleToMove (VacuumMovement movement){
		switch (movement) {
		case UP: return internalMap.isDirty(nrow-1, ncol);
		case DOWN: return internalMap.isDirty(nrow+1, ncol);
		case LEFT: return internalMap.isDirty(nrow, ncol-1);
		case RIGHT:	return internalMap.isDirty(nrow, ncol+1);
		default: return false;
		}

	}

	public void move (VacuumMovement movement){
		internalMap.setCleanCell(nrow, ncol);
		realMap.setCleanCell(nrow, ncol);
		switch (movement) {
		case UP: 
			nrow--;
			break;
		case DOWN: 
			nrow++;
			break;
		case LEFT: 
			ncol--;
			break;
		case RIGHT:	
			ncol++;
			break;
		default:
			break;
		}
		internalMap.setVacuumAtPos(nrow, ncol);
		realMap.setVacuumAtPos(nrow, ncol);
	}


	public AlgAction clean (AlgAction action) {

		switch (action){
		case START:
			direction =VacuumMovement.UP;
			return AlgAction.SENSE;
		case SENSE:
			return applyObstacleSensor();
		case CLEAN:
			if (unexploreNeighbor()){
				return AlgAction.SENSE;
			} else if (dirtyNeighbor()){ 
				if (isPosibleToMove (direction)){
					move(direction);
					return AlgAction.CLEAN;
				} else {
					direction = direction.getNextDirection();
					return AlgAction.CLEAN;
				}
			} else if (internalMap.dirtyAreas()) {
				Point point = internalMap.nearest(nrow, ncol);
				AStar aStar = new AStar(internalMap, point);
				path = aStar.run();
				if (path != null){
					camIndex = path.size()-1;
				} else {
					System.out.println("Not accesible areas in the scene");
					return AlgAction.FINISH;
				}
				return AlgAction.MOVE;
			} else {
				return AlgAction.FINISH;
			}
		case MOVE:
			internalMap.setCleanCell(nrow, ncol);
			realMap.setCleanCell(nrow, ncol);
			nrow = path.get(camIndex).yCoord;
			ncol = path.get(camIndex).xCoord;
			internalMap.setVacuumAtPos(nrow, ncol);
			realMap.setVacuumAtPos(nrow, ncol);
			camIndex--;
			if (camIndex >= 0){
				return AlgAction.MOVE;
			} else {
				return AlgAction.CLEAN;
			}
		case ERROR:
			System.out.println("Error");
			return AlgAction.FINISH;
		case FINISH_SENSE:
			return AlgAction.CLEAN;
		default:
			return AlgAction.ERROR;
		}
	}

	public void setNewPosition(int newRow, int newCol) {
		setnRow(newRow);
		setnCol(newCol);
	}


	/**
	 * Applies a sensor operation so that the agent can see what elements are within its sensor range.
	 */
	public AlgAction applyObstacleSensor() {
		boolean isEnd = false;
		switch (getSectorToExplore()) {
		case 0: {
			if (Math.abs(getYToModifyOriginSensor()) == getSensorRange()) {
				setYToModifyOriginSensor(0);
				if (Math.abs(getXToModifyOriginSensor()) == getSensorRange()) {
					setXToModifyOriginSensor(0);
					setSectorToExplore(1);
				} else {
					setXToModifyOriginSensor(getXToModifyOriginSensor() - 1);
				}
			} else {
				setYToModifyOriginSensor(getYToModifyOriginSensor() - 1);
			}
		} break;
		case 1: {
			if (Math.abs(getYToModifyOriginSensor()) == getSensorRange()) {
				setYToModifyOriginSensor(1);
				if (Math.abs(getXToModifyOriginSensor()) == getSensorRange()) {
					setXToModifyOriginSensor(1);
					setYToModifyOriginSensor(0);
					setSectorToExplore(2);
				} else {
					setXToModifyOriginSensor(getXToModifyOriginSensor() - 1);
				}
			} else {
				setYToModifyOriginSensor(getYToModifyOriginSensor() + 1);
			}
		} break;
		case 2: {
			if (Math.abs(getYToModifyOriginSensor()) == getSensorRange()) {
				setYToModifyOriginSensor(0);
				if (Math.abs(getXToModifyOriginSensor()) == getSensorRange()) {
					setXToModifyOriginSensor(1);
					setYToModifyOriginSensor(-1);
					setSectorToExplore(3);
				} else {
					setXToModifyOriginSensor(getXToModifyOriginSensor() + 1);
				}
			} else {
				setYToModifyOriginSensor(getYToModifyOriginSensor() + 1);
			}
		} break;
		case 3: {
			if (Math.abs(getYToModifyOriginSensor()) == getSensorRange()) {
				setYToModifyOriginSensor(-1);
				if (Math.abs(getXToModifyOriginSensor()) == getSensorRange()) {
					setXToModifyOriginSensor(0);
					setYToModifyOriginSensor(0);
					setSectorToExplore(4);
					getObstaclesDetected().clear();
				} else {
					setXToModifyOriginSensor(getXToModifyOriginSensor() + 1);
				}
			} else {
				setYToModifyOriginSensor(getYToModifyOriginSensor() - 1);
			}
		} break;
		case 4: {
			setSectorToExplore(0);
			isEnd = true;
		} 
		break;
		}

		if (!isEnd) {
			int xPosOriginSensor = (getRealMap().getPixelColStart() + getnCol() * getRealMap().getCellSpace()) + getRealMap().getCellSpace() / 2;
			int yPosOriginSensor = (getRealMap().getPixelRowStart() + getnRow() * getRealMap().getCellSpace()) + getRealMap().getCellSpace() / 2;
			int xPosDestinySensor = xPosOriginSensor + (getYToModifyOriginSensor() * getRealMap().getCellSpace());
			int yPosDestinySensor = yPosOriginSensor + (getXToModifyOriginSensor() * getRealMap().getCellSpace());
			int rowDestiny = getnRow() + getXToModifyOriginSensor();
			int colDestiny = getnCol() + getYToModifyOriginSensor();

			if (rowDestiny >= 0 && rowDestiny < getRealMap().getNrows() && colDestiny >= 0 && colDestiny < getRealMap().getNcols()) {
				getRealMap().drawLineSensor(false, xPosOriginSensor, yPosOriginSensor, xPosDestinySensor, yPosDestinySensor);

				BufferedImage image = new BufferedImage(getRealMap().getWidth(), getRealMap().getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
				Graphics2D g2 = image.createGraphics();
				getRealMap().paint(g2);

				for (int i = 0; i < getObstaclesDetected().size(); i++) {
					Integer[] obstacle = getObstaclesDetected().get(i);

					for (int j = 0; j < getRealMap().getCellSpace(); j++) {
						for (int k = 0; k < getRealMap().getCellSpace(); k++) {		
							int xPixel = (getRealMap().getPixelColStart() + k) + obstacle[1] * getRealMap().getCellSpace();
							int yPixel = (getRealMap().getPixelRowStart() + j) + obstacle[0] * getRealMap().getCellSpace();
							int color = image.getRGB(xPixel, yPixel);
							if (((color & 0x00ff0000) >> 16) == 255) {	// Detect RED of sensor
								setSensorCollisionObstacle(true);
							}
						}
					}
				}
				g2.dispose();

				int xPosCellExplore = getnRow() + getXToModifyOriginSensor();
				int yPosCellExplore = getnCol() + getYToModifyOriginSensor();

				if (getSensorCollisionObstacle()) {
					setSensorCollisionObstacle(false);

				} else if (getRealMap().isObstacle(xPosCellExplore, yPosCellExplore)) {
					getObstaclesDetected().add(new Integer[]{xPosCellExplore, yPosCellExplore});
					getInternalMap().setObstacleAtPos(xPosCellExplore, yPosCellExplore);

				} else if (getXToModifyOriginSensor() != 0 || getYToModifyOriginSensor() != 0) {
					if (getInternalMap().isUnknown(xPosCellExplore, yPosCellExplore)){
						getInternalMap().setDirtyCell(xPosCellExplore, yPosCellExplore);
					}
				}
			}
			return AlgAction.SENSE;

		} else {
			getRealMap().drawLineSensor(true, 0, 0, 0, 0);
			return AlgAction.FINISH_SENSE;
		}

	}

	public void resetSensor () {
		xModifyOriginSensor = 0;	
		yModifyOriginSensor = 0;
		sectorExplore = 0;
		collisionSensorWithObstacle = false;
		obstaclesDetected.clear();
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

	public int getSensorRange() {
		return sensorRange;
	}

	public void setSensorRange(int range) {
		sensorRange = range;
	}

	public boolean getSensorCollisionObstacle() {
		return collisionSensorWithObstacle;
	}

	public void setSensorCollisionObstacle(boolean value) {
		collisionSensorWithObstacle = value;
	}

	public ArrayList<Integer[]> getObstaclesDetected() {
		return obstaclesDetected;
	}

	public int getXToModifyOriginSensor() {
		return xModifyOriginSensor;
	}

	public int getYToModifyOriginSensor() {
		return yModifyOriginSensor;
	}

	public void setXToModifyOriginSensor(int val) {
		xModifyOriginSensor = val;
	}

	public void setYToModifyOriginSensor(int val) {
		yModifyOriginSensor = val;
	}

	public int getSectorToExplore() {
		return sectorExplore;
	}

	public void setSectorToExplore(int val) {
		sectorExplore = val;
	}

	public Timer getTimerSensor() {
		return timerSensor;
	}

	public void setTimerSensor(Timer timerSensor) {
		this.timerSensor = timerSensor;
	}
}