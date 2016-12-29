package vacuumCleaner;

import java.awt.Graphics2D;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
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
	
	private Timer timerSensor;
	public int xModifyOriginSensor = 0;	// To go of vacuum cell to explore cell
	public int yModifyOriginSensor = 0; // To go of vacuum cell to explore cell
	public int sectorExplore = 0;
	public boolean collisionSensorWithObstacle = false;
	public ArrayList<Integer[]> obstaclesDetected = new ArrayList<Integer[]>();
	
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
	 */
	public void applyObstacleSensor(int pixelRowStart, int pixelColStart, int cellSpace) {
		timerSensor = new Timer(50, new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
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
					timerSensor.stop();
				} break;
				}
				
				int xPosOriginSensor = (pixelColStart + getnCol() * cellSpace) + cellSpace / 2;
				int yPosOriginSensor = (pixelRowStart + getnRow() * cellSpace) + cellSpace / 2;
				int xPosDestinySensor = xPosOriginSensor + (getYToModifyOriginSensor() * cellSpace);
				int yPosDestinySensor = yPosOriginSensor + (getXToModifyOriginSensor() * cellSpace);
				getRealMap().drawLineSensor(xPosOriginSensor, yPosOriginSensor, xPosDestinySensor, yPosDestinySensor);
				
				BufferedImage image = new BufferedImage(getRealMap().getWidth(), getRealMap().getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
				Graphics2D g2 = image.createGraphics();
				getRealMap().paint(g2);
				
				for (int i = 0; i < getObstaclesDetected().size(); i++) {
					Integer[] obstacle = getObstaclesDetected().get(i);
					
					for (int j = 0; j < cellSpace; j++) {
						for (int k = 0; k < cellSpace; k++) {		
							int xPixel = (pixelColStart + k) + obstacle[1] * cellSpace;
							int yPixel = (pixelRowStart + j) + obstacle[0] * cellSpace;
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
				
				if (getRealMap().isObstacle(xPosCellExplore, yPosCellExplore)) {
					getObstaclesDetected().add(new Integer[]{xPosCellExplore, yPosCellExplore});
					getInternalMap().setObstacleAtPos(xPosCellExplore, yPosCellExplore);
					
				} else if (getSensorCollisionObstacle()) {
					setSensorCollisionObstacle(false);
					
				} else if (getXToModifyOriginSensor() != 0 || getYToModifyOriginSensor() != 0) {
					getInternalMap().setDirtyCell(xPosCellExplore, yPosCellExplore);
				}
		    }
		});
		timerSensor.start();
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