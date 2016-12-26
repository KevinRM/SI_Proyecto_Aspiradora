package vacuumCleaner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AppController {
	private static final int SENSOR_RANGE_MIN_VALUE = 3;
	private static final int SENSOR_RANGE_MAX_VALUE = 7;
	private static final int MIN_ROWSCOLS_NUM = 10;
	private static final int MAX_ROWSCOLS_NUM = 100;
	
	private ControlPanel controlPanel;
	private RealMap realMap;
	
	/**
	 * The vacuum cleaner is instantiated in the controller.
	 */
	private Vacuum vacuumCleaner;
	
	public AppController(ControlPanel aControlPanel, RealMap aRealMap) {
		controlPanel = aControlPanel;
		realMap = aRealMap;
		vacuumCleaner = null;
		
		controlPanel.getInternalMap().repaint();
		realMap.repaint();
		initializeGUIComponentsHandlers();
	}

	public void initializeVacuumCleaner() {
		
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
					
					// TO-DO manejar la cantidad de obstáculos aleatorios
					
					if ((nrows < MIN_ROWSCOLS_NUM || ncols < MIN_ROWSCOLS_NUM) ||
							(nrows > MAX_ROWSCOLS_NUM || ncols > MAX_ROWSCOLS_NUM)) {
						displayWrongNumberOfRowsOrColumnsDialog();
					} else {
						getRealMap().resizeMap(nrows, ncols);
						getControlPanel().getInternalMap().resizeMap(nrows, ncols);
						// TO-DO Llamada a la generación aleatoria de obstáculos
					}
		
				} catch (NumberFormatException exception) {
					displayWrongNumberOfRowsOrColumnsDialog();
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
			}

			@Override
			public void mouseMoved(MouseEvent e) {}
		});
		
		/**
		 * START button listener.
		 */
		getControlPanel().getStartButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int sensorRange = Integer.parseInt(getControlPanel().getSensorRange().getText());
					if ((sensorRange > SENSOR_RANGE_MAX_VALUE) || sensorRange < SENSOR_RANGE_MIN_VALUE) {
						displayWrongValueForSensorRange();
					} else {
						// TO-DO comenzar la simulación
						// Debería comprobarse también que la aspiradora esté colocada
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
				// TO-DO detener simulación
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
		
		
		// TODO borrar esto
		getControlPanel().getInternalMap().addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("click!");
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	
	/**
	 * Determines what cell of the real map has been clicked
	 * and paint the object on it.
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
				getRealMap().setObstacleAtPos(rowClicked, colClicked);
			} else if (getControlPanel().getSetVacuumRadioButton().isSelected()) {
				if (!getRealMap().isVacuumSet()) {
					getRealMap().setVacuumAtPos(rowClicked, colClicked);
				} else {
					JOptionPane.showMessageDialog(getControlPanel().getParent(), 
							"A vacuum is already set on the map. ", 
							"Warning", 
							JOptionPane.INFORMATION_MESSAGE);
				}
			} else if (getControlPanel().getEraseObjectRadioButton().isSelected()) {
				getRealMap().setDirtyCell(rowClicked, colClicked);
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
}
