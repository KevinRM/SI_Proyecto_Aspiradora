package vacuumCleaner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
						System.out.println("APPLY BUTTON IS PRESSED");
					}
		
				} catch (NumberFormatException exception) {
					displayWrongNumberOfRowsOrColumnsDialog();
				}
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
