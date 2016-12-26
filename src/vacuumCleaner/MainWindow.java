package vacuumCleaner;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;

public class MainWindow extends JFrame {
	private static final int FRAME_WIDTH = 900;
	private static final int FRAME_HEIGHT = 620;
	private static final int MAPS_DEFAULT_NROWS = 20;
	private static final int MAPS_DEFAULT_NCOLS = 20;
	private static final Color COLOR_BACKGROUND = Color.BLACK;
	
	private RealMap realMap;
	private ControlPanel controlPanel;
	private AppController controller;
	
	public MainWindow() {
		buildWindow();
		initializeWindowComponents();
		
		setLayout(new BorderLayout());
		add(controlPanel, BorderLayout.WEST);
		add(realMap, BorderLayout.CENTER);
		
		getControlPanel().getInternalMap().repaint();
	}
	
	private void initializeWindowComponents() {
		InternalMap internalMap = new InternalMap(MAPS_DEFAULT_NROWS, MAPS_DEFAULT_NCOLS);
		ControlPanel controlPanel = new ControlPanel();
		controlPanel.setInternalMap(internalMap);
		
		setControlPanel(controlPanel);
		setRealMap(new RealMap(MAPS_DEFAULT_NROWS, MAPS_DEFAULT_NCOLS));
		getControlPanel().setInternalMap(internalMap);
		getControlPanel().getMapRows().setText(String.valueOf(MAPS_DEFAULT_NROWS));
		getControlPanel().getMapCols().setText(String.valueOf(MAPS_DEFAULT_NCOLS));
		setController(new AppController(controlPanel, getRealMap()));
	}
	
	private void buildWindow() {
		setTitle("Intelligent Vacuum Cleaner Simulator");
		setBackground(COLOR_BACKGROUND);
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setMinimumSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		setLayout(new GridBagLayout());
	}

	public RealMap getRealMap() {
		return realMap;
	}

	public void setRealMap(RealMap realMap) {
		this.realMap = realMap;
	}

	public ControlPanel getControlPanel() {
		return controlPanel;
	}

	public void setControlPanel(ControlPanel controlPanel) {
		this.controlPanel = controlPanel;
	}

	public AppController getController() {
		return controller;
	}

	public void setController(AppController controller) {
		this.controller = controller;
	}
}