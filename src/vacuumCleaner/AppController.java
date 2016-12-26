package vacuumCleaner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

public class AppController {
	private static final int MIN_ROWSCOLS_NUM = 10;
	private static final int MAX_ROWSCOLS_NUM = 100;
	
	private ControlPanel controlPanel;
	private RealMap realMap;
	
	public AppController(ControlPanel aControlPanel, RealMap aRealMap) {
		controlPanel = aControlPanel;
		realMap = aRealMap;
		
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
					
					// TO-DO manejar la cantidad de obstáculos aleatorios
					
					if ((nrows < MIN_ROWSCOLS_NUM || ncols < MIN_ROWSCOLS_NUM) ||
							(nrows > MAX_ROWSCOLS_NUM || ncols > MAX_ROWSCOLS_NUM)) {
						displayWrongNumberOfRowsOrColumnsDialog();
					} else {
						getRealMap().resizeMap(nrows, ncols);
					}
		
				} catch (NumberFormatException exception) {
					displayWrongNumberOfRowsOrColumnsDialog();
				}
			}
		});
		
		/**
		 * RealMap listener.
		 */
		getRealMap().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int rowDragged = (e.getY() - getRealMap().getPixelRowStart()) / getRealMap().getCellSpace();
				int colDragged = (e.getX() - getRealMap().getPixelColStart()) / getRealMap().getCellSpace();
				
				if (getControlPanel().getSetObstacleRadioButton().isSelected()) {
					getRealMap().setObstacleAtPos(rowDragged, colDragged);
				} else if (getControlPanel().getSetVacuumRadioButton().isSelected()) {
					getRealMap().setVacuumAtPos(rowDragged, colDragged);
				} else if (getControlPanel().getEraseObjectRadioButton().isSelected()) {
					getRealMap().setDirtyCell(rowDragged, colDragged);
				}
				
				getRealMap().repaint();
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowClicked = (e.getY() - getRealMap().getPixelRowStart()) / getRealMap().getCellSpace();
				int colClicked = (e.getX() - getRealMap().getPixelColStart()) / getRealMap().getCellSpace();
				
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
				
				getRealMap().repaint();
			}
		});
		
		/**
		 * START button listener.
		 */
		getControlPanel().getStartButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		/**
		 * RESET button listener.
		 */
		getControlPanel().getResetButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	}
	
	private void displayWrongNumberOfRowsOrColumnsDialog() {
		JOptionPane.showMessageDialog(getControlPanel().getParent(), 
				"Number of rows or columns is not correct (10-100). ", 
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
