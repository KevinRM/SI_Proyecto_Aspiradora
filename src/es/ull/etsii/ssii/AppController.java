package es.ull.etsii.ssii;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class AppController {
	private static final int SENSOR_RANGE_MIN_VALUE = 3;
	private static final int SENSOR_RANGE_MAX_VALUE = 7;
	private static final int MIN_ROWSCOLS_NUM = 10;
	private static final int MAX_ROWSCOLS_NUM = 70;
	private static final int MIN_PERCENTAGE_OBS = 0;
	private static final int MAX_PERCENTAGE_OBS = 50;

	private ControlPanel controlPanel;
	private RealMap realMap;
	private AlgAction algAction;
	private Timer algTimer;
	private boolean isRunning;
	private boolean isPaused;

	/**
	 * The vacuum cleaner is instantiated in the controller.
	 */
	private Vacuum vacuumCleaner;

	public AppController(ControlPanel aControlPanel, RealMap aRealMap) {
		controlPanel = aControlPanel;
		realMap = aRealMap;
		vacuumCleaner = new Vacuum(realMap, controlPanel.getInternalMap());
		algTimer = null;
		isRunning = false;
		isPaused = false;
		
		controlPanel.getInternalMap().repaint();
		realMap.repaint();
		initializeGUIComponentsHandlers();
	}

	private void initializeGUIComponentsHandlers() {

		/**
		 * APPLY button listener.
		 */
		getControlPanel().getApplyButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int nrows = Integer.parseInt(getControlPanel().getMapRows().getText());
					int ncols = Integer.parseInt(getControlPanel().getMapCols().getText());

					if ((nrows < MIN_ROWSCOLS_NUM || ncols < MIN_ROWSCOLS_NUM) ||
							(nrows > MAX_ROWSCOLS_NUM || ncols > MAX_ROWSCOLS_NUM)) {
						displayWrongNumberOfRowsOrColumnsDialog();
					} else {
						getRealMap().resizeMap(nrows, ncols);
						getControlPanel().getInternalMap().resizeMap(nrows, ncols);
					}
				} catch (NumberFormatException exception) {
					displayWrongNumberOfRowsOrColumnsDialog();
				}

				try {
					int percentageObs = Integer.parseInt(getControlPanel().getRndObstaclesPercentage().getText());

					if (percentageObs < MIN_PERCENTAGE_OBS || percentageObs > MAX_PERCENTAGE_OBS) {
						displayWrongValueForPercentageObstacle();
					} else {
						getRealMap().generateObstaclesRandomly(percentageObs);
					}
				} catch (NumberFormatException exception) {
					displayWrongValueForPercentageObstacle();
				}
			}
		});

		/**
		 * RealMap mouse's clicks listener.
		 */
		getRealMap().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				paintTargetCellForRealMap(e.getX(), e.getY());
				getRealMap().repaint();
				getControlPanel().getInternalMap().repaint();
			}
		});

		/**
		 * MouseDragged listener for the real map.
		 */
		getRealMap().addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseDragged(MouseEvent e) {
				paintTargetCellForRealMap(e.getX(), e.getY());
				getRealMap().repaint();
				getControlPanel().getInternalMap().repaint();
			}

			@Override
			public void mouseMoved(MouseEvent e) {}
		});

		/**
		 * START button listener.
		 */
		getControlPanel().getSpeedSlider().addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				if (algTimer.isRunning()){
					algTimer.setDelay(getControlPanel().getSpeedSlider().getValue());
				}

			}

		});

		getControlPanel().getStartButton().addActionListener(new ActionListener() {
			@Override

			public void actionPerformed(ActionEvent e) {
				try {
					if (getRealMap().isVacuumSet()) {
						int sensorRange = Integer.parseInt(getControlPanel().getSensorRange().getText());
						if ((sensorRange > SENSOR_RANGE_MAX_VALUE) || sensorRange < SENSOR_RANGE_MIN_VALUE) {
							displayWrongValueForSensorRange();
						} else {
	
	
							if (!isRunning){
								isRunning = true;
								getControlPanel().getStartButton().setText("PAUSE");
								getVacuum().setSensorRange(sensorRange);
								algAction = AlgAction.START;
								algTimer = new Timer(getControlPanel().getSpeedSlider().getValue(), new ActionListener () {
									public void actionPerformed(ActionEvent e) {
										algAction = getVacuum().clean(algAction);
										realMap.repaint();
										getVacuum().getInternalMap().repaint();
										if (algAction == AlgAction.FINISH){
											algTimer.stop();
											getControlPanel().getStartButton().setText("START");
											isPaused = false;
											isRunning = false;
										} else if (algAction == AlgAction.SENSE){
											algTimer.setDelay(getControlPanel().getSpeedSlider().getValue()/10);
										} else if (algAction == AlgAction.FINISH_SENSE){
											algTimer.setDelay(getControlPanel().getSpeedSlider().getValue());
										}
									}
								});
								algTimer.start();
							} else if (!isPaused) {
								isPaused = true;
								algTimer.stop();
								getControlPanel().getStartButton().setText("START");
							} else {
								isPaused = false;
								algTimer.start();
								getControlPanel().getStartButton().setText("PAUSE");
							}
						}
					}
					
					else {
							displayVacuumNotSetMessage();
					}
					
				} catch (NumberFormatException exception) {
					displayWrongValueForSensorRange();
				}
			}
		});

		/**
		 * RESET button listener.
		 */
		getControlPanel().getResetButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Stop simulation
				algTimer.stop();
				getControlPanel().getStartButton().setText("START");
				isPaused = false;
				isRunning = false;
				
				// Reset the maps
				int currentnRows = getRealMap().getnRows();
				int currentnCols = getRealMap().getnCols();
				getRealMap().resizeMap(currentnRows, currentnCols);
				getControlPanel().getInternalMap().resizeMap(currentnRows, currentnCols);
				getRealMap().drawLineSensor(true, 0, 0, 0, 0);
				getVacuum().resetSensor();
				getRealMap().repaint();
				getControlPanel().getInternalMap().repaint();
				
				// Setting color for vacuum cleaner
				String newVacuumColor = 
						String.valueOf(getControlPanel().getVacuumColor().getSelectedItem());
				getRealMap().changeVacuumColor(newVacuumColor);
				getRealMap().repaint();
			}
		});

		/**
		 * JComboxBox listener for changing the vacuum's color.
		 */
		getControlPanel().getVacuumColor().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String newVacuumColor = 
						String.valueOf(getControlPanel().getVacuumColor().getSelectedItem());
				getRealMap().changeVacuumColor(newVacuumColor);
				getRealMap().repaint();
			}
		});

		/**
		 * Setting tool tip for sensor range text field with allowed values.
		 */
		javax.swing.ToolTipManager.sharedInstance().setInitialDelay(0);
		getControlPanel().getSensorRange().setToolTipText("Sensor range must be "
				+ "a value between " + SENSOR_RANGE_MIN_VALUE + " and " + 
				SENSOR_RANGE_MAX_VALUE + ". ");

		class ClearTextField extends MouseAdapter {
			@Override
			public void mouseClicked(MouseEvent e){
				((JTextField) e.getSource()).setText("");
			}
		}

		/**
		 * Listener for clearing text boxes for rows and columns when clicked.
		 */
		getControlPanel().getMapRows().addMouseListener(new ClearTextField());
		getControlPanel().getMapCols().addMouseListener(new ClearTextField());
		getControlPanel().getRndObstaclesPercentage().addMouseListener(new ClearTextField());
	}

	/**
	 * Determines what cell of the real map has been clicked and paint the object on it.
	 * 
	 * @param pixelX
	 * @param pixelY
	 */
	private void paintTargetCellForRealMap(int pixelX, int pixelY) {
		int rowClicked = (pixelY - getRealMap().getPixelRowStart()) / getRealMap().getCellSpace();
		int colClicked = (pixelX - getRealMap().getPixelColStart()) / getRealMap().getCellSpace();

		if ((rowClicked >= 0) && (rowClicked < getRealMap().getnRows()) &&
				(colClicked >= 0) && colClicked < getRealMap().getnCols()) {

			if (getControlPanel().getSetObstacleRadioButton().isSelected()) {
				if (!getRealMap().isVacuum(rowClicked, colClicked)) {
					getRealMap().setObstacleAtPos(rowClicked, colClicked);
				}

			} else if (getControlPanel().getSetVacuumRadioButton().isSelected()) {
				if (!getRealMap().isVacuumSet()) {
					// Vacuum cleaner is set just on a free cell
					if (!getRealMap().isObstacle(rowClicked, colClicked)) {
						getRealMap().setVacuumAtPos(rowClicked, colClicked);
						getControlPanel().getInternalMap().setVacuumAtPos(rowClicked, colClicked);
						getVacuum().setNewPosition(rowClicked, colClicked);
					}
				} else {
					JOptionPane.showMessageDialog(getControlPanel().getParent(), 
							"A vacuum is already set on the map. ", 
							"Warning", 
							JOptionPane.INFORMATION_MESSAGE);
				}

			} else if (getControlPanel().getEraseObjectRadioButton().isSelected()) {
				getRealMap().setDirtyCell(rowClicked, colClicked);
				getControlPanel().getInternalMap().setUnknownCell(rowClicked, colClicked);
			}
		}
	}

	private void displayWrongNumberOfRowsOrColumnsDialog() {
		JOptionPane.showMessageDialog(getControlPanel().getParent(), 
				"Number of rows or columns is not correct (10-100). ", 
				"Warning", 
				JOptionPane.INFORMATION_MESSAGE);
	}

	private void displayWrongValueForSensorRange() {
		JOptionPane.showMessageDialog(getControlPanel().getParent(), 
				"The sensor range value must be between " + SENSOR_RANGE_MIN_VALUE + 
				" and " + SENSOR_RANGE_MAX_VALUE + ". ", 
				"Warning", 
				JOptionPane.INFORMATION_MESSAGE);
	}

	private void displayWrongValueForPercentageObstacle() {
		JOptionPane.showMessageDialog(getControlPanel().getParent(), 
				"The percentage obstacle value must be between " + MIN_PERCENTAGE_OBS + 
				" and " + MAX_PERCENTAGE_OBS + ". ", 
				"Warning", 
				JOptionPane.INFORMATION_MESSAGE);
	}

	private void displayVacuumNotSetMessage() {
		JOptionPane.showMessageDialog(getControlPanel().getParent(), 
				"There must be a vacuum set on the map.", 
				"Warning", 
				JOptionPane.INFORMATION_MESSAGE);
	}
	
	public ControlPanel getControlPanel() {
		return controlPanel;
	}

	public void setControlPanel(ControlPanel controlPanel) {
		this.controlPanel = controlPanel;
	}

	public RealMap getRealMap() {
		return realMap;
	}

	public void setRealMap(RealMap realMap) {
		this.realMap = realMap;
	}

	public Vacuum getVacuum() {
		return vacuumCleaner;
	}
}
