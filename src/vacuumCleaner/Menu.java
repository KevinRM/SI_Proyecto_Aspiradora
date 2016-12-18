package vacuumCleaner;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class Menu extends JPanel {
	private static final Color BACKGROUND_COLOR = Color.LIGHT_GRAY;
	private static final String[] VACUUM_COLORS = {"BLUE", "RED", "GREEN", "PINK"};
	private static final int TEXTFIELD_WIDTH = 100;
	private static final int TEXTFIELD_HEIGHT = 22;
	
	private Floor floor;
	private Vacuum vacuum;
	private JTextField mapRows;
	private JTextField mapCols;
	private JTextField rndObstaclesPercentage;
	private JButton applyButton;
	private JTextField sensorRange;
	private JComboBox vacuumColor;
	private JRadioButton vacuumRadioButton;
	private JRadioButton obstacleRadioButton;
	private JSlider speedSlider;
	private JButton startButton;
	private JButton resetButton;
	
	public Menu(Floor floor, Vacuum vacuum) {
		this.floor = floor;
		this.vacuum = vacuum;
		setBackground(BACKGROUND_COLOR);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		initializeGUIComponents();
		
		JPanel roomSettingsPanel = new JPanel();
		JPanel agentSettingsPanel = new JPanel();
		JPanel drawSettingsPanel = new JPanel();
		JPanel animationSettingsPanel = new JPanel();
		InternalMap internalMapPanel = new InternalMap();
		internalMapPanel.setPreferredSize(new Dimension(150, 400));
		
		add(roomSettingsPanel);
		add(agentSettingsPanel);
		add(drawSettingsPanel);
		add(animationSettingsPanel);
		add(internalMapPanel);
		
		LineBorder lineBorderPanel = (LineBorder) BorderFactory.createLineBorder(Color.BLACK);
		
		// Room
		roomSettingsPanel.setBorder(BorderFactory.createTitledBorder(lineBorderPanel, "Room"));
		roomSettingsPanel.setLayout(new BoxLayout(roomSettingsPanel, BoxLayout.Y_AXIS));
		JPanel panel1 = new JPanel();
		panel1.setLayout(new FlowLayout());
		panel1.add(new JLabel("Number of rows (10-100):"));
		panel1.add(mapRows);
		roomSettingsPanel.add(panel1);
		JPanel panel2 = new JPanel();
		panel2.setLayout(new FlowLayout());
		roomSettingsPanel.add(panel2);
		panel2.add(new JLabel("Number of columns (10-100):"));
		panel2.add(mapCols);
		JPanel panel3 = new JPanel();
		panel3.setLayout(new FlowLayout());
		roomSettingsPanel.add(panel3);
		panel3.add(new JLabel("Random obstacles percentage:"));
		panel3.add(rndObstaclesPercentage);
		JPanel panel4 = new JPanel();
		roomSettingsPanel.add(panel4);
		applyButton.setBackground(Color.WHITE);
	    applyButton.setForeground(Color.BLACK);
		panel4.add(applyButton);
		
		// Agent
		agentSettingsPanel.setBorder(BorderFactory.createTitledBorder(lineBorderPanel, "Agent"));
		agentSettingsPanel.add(new JLabel("Sensor range:"));
		agentSettingsPanel.add(sensorRange);
		JPanel panel7 = new JPanel();
		panel7.add(new JLabel("Color:"));
		panel7.add(vacuumColor);
		agentSettingsPanel.add(panel7);
		
		// Draw
		drawSettingsPanel.setBorder(BorderFactory.createTitledBorder(lineBorderPanel, "Draw"));
		drawSettingsPanel.setLayout(new GridLayout(2, 1));
		ButtonGroup drawButtonsGroup = new ButtonGroup();
		drawButtonsGroup.add(vacuumRadioButton);
		drawButtonsGroup.add(obstacleRadioButton);
		drawSettingsPanel.add(vacuumRadioButton);
		drawSettingsPanel.add(obstacleRadioButton);

		// Animation
		Border animationSettingsPanelBorder = BorderFactory.createTitledBorder(lineBorderPanel, "Animation");		
		animationSettingsPanel.setBorder(animationSettingsPanelBorder);
		animationSettingsPanel.setLayout(new BorderLayout());
		JPanel panel5 = new JPanel();
		animationSettingsPanel.add(panel5, BorderLayout.CENTER);
		panel5.add(new JLabel("Speed: "));
		speedSlider.setForeground(Color.WHITE);
		panel5.add(speedSlider);
		JPanel panel6 = new JPanel();
		startButton.setBackground(Color.WHITE);
		startButton.setForeground(Color.BLACK);
		resetButton.setBackground(Color.WHITE);
		resetButton.setForeground(Color.BLACK);
		panel6.add(startButton);
		panel6.add(resetButton);
		animationSettingsPanel.add(panel6, BorderLayout.SOUTH);
		
		//initializeButtons();
	}
	
	private void initializeGUIComponents() {
		mapRows = new JTextField();
		mapCols = new JTextField();
		rndObstaclesPercentage = new JTextField();
		applyButton = new JButton("APPLY");
		sensorRange = new JTextField();
		vacuumRadioButton = new JRadioButton("Vacuum Cleaner");
		vacuumColor = new JComboBox(VACUUM_COLORS);
		obstacleRadioButton = new JRadioButton("Obstacle");
		speedSlider = new JSlider();
		startButton = new JButton("START");
		resetButton = new JButton("RESET");
		
		vacuumColor.setSelectedIndex(2);
		mapRows.setPreferredSize(new Dimension(TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT));
		mapCols.setPreferredSize(new Dimension(TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT));
		rndObstaclesPercentage.setPreferredSize(new Dimension(TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT));
		sensorRange.setPreferredSize(new Dimension(TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT));
		
		mapRows.setHorizontalAlignment(JTextField.RIGHT);
		mapCols.setHorizontalAlignment(JTextField.RIGHT);
		rndObstaclesPercentage.setHorizontalAlignment(JTextField.RIGHT);
		sensorRange.setHorizontalAlignment(JTextField.RIGHT);
	}
	
	// NOTA: los listeners fuera de esta clase, en un controlador
	private void initializeButtons() {
		// Button set Vacuum
		/*
		setVacuum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// Create the mouse event
				eventMouseToRemove = new MouseListener() {
					public void mouseClicked(MouseEvent arg0) {}
					public void mouseEntered(MouseEvent arg0) {}
					public void mouseExited(MouseEvent arg0) {}
					public void mousePressed(MouseEvent arg0) {}
					public void mouseReleased(MouseEvent arg0) {
						// Set Vacuum in the cell
						FloorCell cell = (FloorCell)arg0.getSource();
						floor.setVacuum(cell.getRow(), cell.getColumn());
						Vacuum.setPosition(cell.getRow(), cell.getColumn());
						
						// Remove the listener of the cells
						FloorCell[][] cells = floor.getCells();
						for (int i = 0; i < floor.getNumberRows(); i++) {
							for (int j = 0; j < floor.getNumberColumns(); j++) {
								cells[i][j].removeMouseListener(eventMouseToRemove);
							}
						}
						// Disable button setVacuum
						setVacuum.setEnabled(false);
						// Enable button start
						startButton.setEnabled(true);
					}
				};
				
				// Add mouseListener to cells
				FloorCell[][] cells = floor.getCells();
				for (int i = 0; i < floor.getNumberRows(); i++) {
					for (int j = 0; j < floor.getNumberColumns(); j++) {
						cells[i][j].addMouseListener(eventMouseToRemove);
					}
				}
			}
		});
		
		// Button set Still Obstacle
		setStillObstacle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// Create the mouse event
				eventMouseToRemove = new MouseListener() {
					public void mouseClicked(MouseEvent arg0) {}
					public void mouseEntered(MouseEvent arg0) {}
					public void mouseExited(MouseEvent arg0) {}
					public void mousePressed(MouseEvent arg0) {}
					public void mouseReleased(MouseEvent arg0) {
						// Set Vacuum in the cell
						FloorCell cell = (FloorCell) arg0.getSource();
						cell.setObstacleHere();

						// Remove the listener of the cells
						FloorCell[][] cells = floor.getCells();
						for (int i = 0; i < floor.getNumberRows(); i++) {
							for (int j = 0; j < floor.getNumberColumns(); j++) {
								cells[i][j].removeMouseListener(eventMouseToRemove);
							}
						}
					}
				};

				// Add mouseListener to cells
				FloorCell[][] cells = floor.getCells();
				for (int i = 0; i < floor.getNumberRows(); i++) {
					for (int j = 0; j < floor.getNumberColumns(); j++) {
						cells[i][j].addMouseListener(eventMouseToRemove);
					}
				}
			}
		});
		
		// Button start
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				vacuum.startClean();
				stopButton.setEnabled(true);
			}
		});
		startButton.setEnabled(false);
		// Button stop
		stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				vacuum.stopClean();
			}
		});
		stopButton.setEnabled(false);
		*/
	}
}
