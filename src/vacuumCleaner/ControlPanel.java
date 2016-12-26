package vacuumCleaner;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
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

public class ControlPanel extends JPanel {
	private static final Color BACKGROUND_COLOR = Color.LIGHT_GRAY;
	private static final String[] VACUUM_COLORS = {"BLUE", "RED", "GREEN", "PINK"};
	private static final int TEXTFIELD_WIDTH = 35;
	private static final int TEXTFIELD_HEIGHT = 22;
	private static final int TEXTFIELD_RIGHT_GAP = 20;
	private static final int INTERNALMAP_DEFAULT_NROWS = 20;
	private static final int INTERNALMAP_DEFAULT_NCOLS = 30;
	
	private Vacuum vacuum;
	private JTextField mapRows;
	private JTextField mapCols;
	private JTextField rndObstaclesPercentage;
	private JButton applyButton;
	private JTextField sensorRange;
	private JComboBox vacuumColor;
	private JRadioButton setVacuumRadioButton;
	private JRadioButton setObstacleRadioButton;
	private JRadioButton eraseObjectRadioButton;
	private JSlider speedSlider;
	private JButton startButton;
	private JButton resetButton;
	private InternalMap internalMap;

	public ControlPanel() {
		setBackground(BACKGROUND_COLOR);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		initializeGUIComponents();

		JPanel roomSettingsPanel = new JPanel();
		JPanel agentSettingsPanel = new JPanel();
		JPanel drawSettingsPanel = new JPanel();
		JPanel animationSettingsPanel = new JPanel();
		JPanel internalMapPanel = new JPanel();
		internalMapPanel.setLayout(new GridLayout(1, 1));
		internalMap = new InternalMap(INTERNALMAP_DEFAULT_NROWS, 
												INTERNALMAP_DEFAULT_NCOLS);
		internalMapPanel.setPreferredSize(new Dimension(150, 400));
		internalMapPanel.add(internalMap);
		
		add(roomSettingsPanel);
		add(agentSettingsPanel);
		add(drawSettingsPanel);
		add(internalMapPanel);
		add(animationSettingsPanel);

		LineBorder lineBorderPanel = (LineBorder) BorderFactory.createLineBorder(Color.BLACK);

		// Room
		roomSettingsPanel.setBorder(BorderFactory.createTitledBorder(lineBorderPanel, "Room"));
		roomSettingsPanel.setLayout(new BoxLayout(roomSettingsPanel, BoxLayout.Y_AXIS));
		JPanel panel1 = new JPanel();
		panel1.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panel1.add(new JLabel("Number of rows (10-100):"));
		panel1.add(mapRows);
		panel1.add(Box.createRigidArea(new Dimension(TEXTFIELD_RIGHT_GAP, 0)));
		roomSettingsPanel.add(panel1);
		JPanel panel2 = new JPanel();
		panel2.setLayout(new FlowLayout(FlowLayout.RIGHT));
		roomSettingsPanel.add(panel2);
		panel2.add(new JLabel("Number of columns (10-100):"));
		panel2.add(mapCols);
		panel2.add(Box.createRigidArea(new Dimension(TEXTFIELD_RIGHT_GAP, 0)));
		JPanel panel3 = new JPanel();
		panel3.setLayout(new FlowLayout(FlowLayout.RIGHT));
		roomSettingsPanel.add(panel3);
		panel3.add(new JLabel("Random obstacles percent.:"));
		panel3.add(rndObstaclesPercentage);
		panel3.add(Box.createRigidArea(new Dimension(TEXTFIELD_RIGHT_GAP, 0)));
		JPanel panel4 = new JPanel();
		roomSettingsPanel.add(panel4);
		//applyButton.setBackground(Color.WHITE);
		//applyButton.setForeground(Color.BLACK);
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
		drawButtonsGroup.add(setObstacleRadioButton);
		drawButtonsGroup.add(eraseObjectRadioButton);
		drawButtonsGroup.add(setVacuumRadioButton);
		JPanel firstRadioButtonsRow = new JPanel();
		firstRadioButtonsRow.setLayout(new FlowLayout(FlowLayout.CENTER));
		firstRadioButtonsRow.add(setObstacleRadioButton);
		firstRadioButtonsRow.add(eraseObjectRadioButton);
		drawSettingsPanel.add(firstRadioButtonsRow);
		JPanel vacuumRadioButtonPanel = new JPanel();
		vacuumRadioButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		vacuumRadioButtonPanel.add(setVacuumRadioButton);
		drawSettingsPanel.add(vacuumRadioButtonPanel);

		// InternalMap
		Border internalMapPanelBorder = BorderFactory.createTitledBorder(lineBorderPanel, "Internal Map");
		internalMapPanel.setBorder(internalMapPanelBorder);

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
		//startButton.setBackground(Color.WHITE);
		//startButton.setForeground(Color.BLACK);
		//resetButton.setBackground(Color.WHITE);
		//resetButton.setForeground(Color.BLACK);
		panel6.add(startButton);
		panel6.add(resetButton);
		animationSettingsPanel.add(panel6, BorderLayout.SOUTH);
		
		internalMap.repaint();
	}

	private void initializeGUIComponents() {
		mapRows = new JTextField();
		mapCols = new JTextField();
		rndObstaclesPercentage = new JTextField();
		applyButton = new JButton("APPLY");
		sensorRange = new JTextField();
		setVacuumRadioButton = new JRadioButton("Set agent (only 1)");
		setObstacleRadioButton = new JRadioButton("Set obstacle");
		eraseObjectRadioButton = new JRadioButton("Erase object");
		vacuumColor = new JComboBox(VACUUM_COLORS);
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

	public Vacuum getVacuum() {
		return vacuum;
	}

	public void setVacuum(Vacuum vacuum) {
		this.vacuum = vacuum;
	}

	public JTextField getMapRows() {
		return mapRows;
	}

	public void setMapRows(JTextField mapRows) {
		this.mapRows = mapRows;
	}

	public JTextField getMapCols() {
		return mapCols;
	}

	public void setMapCols(JTextField mapCols) {
		this.mapCols = mapCols;
	}

	public JTextField getRndObstaclesPercentage() {
		return rndObstaclesPercentage;
	}

	public void setRndObstaclesPercentage(JTextField rndObstaclesPercentage) {
		this.rndObstaclesPercentage = rndObstaclesPercentage;
	}

	public JButton getApplyButton() {
		return applyButton;
	}

	public void setApplyButton(JButton applyButton) {
		this.applyButton = applyButton;
	}

	public JTextField getSensorRange() {
		return sensorRange;
	}

	public void setSensorRange(JTextField sensorRange) {
		this.sensorRange = sensorRange;
	}

	public JComboBox getVacuumColor() {
		return vacuumColor;
	}

	public void setVacuumColor(JComboBox vacuumColor) {
		this.vacuumColor = vacuumColor;
	}
	
	public JSlider getSpeedSlider() {
		return speedSlider;
	}

	public void setSpeedSlider(JSlider speedSlider) {
		this.speedSlider = speedSlider;
	}

	public JButton getStartButton() {
		return startButton;
	}

	public void setStartButton(JButton startButton) {
		this.startButton = startButton;
	}

	public JButton getResetButton() {
		return resetButton;
	}

	public void setResetButton(JButton resetButton) {
		this.resetButton = resetButton;
	}
	
	public JRadioButton getSetVacuumRadioButton() {
		return setVacuumRadioButton;
	}

	public void setSetVacuumRadioButton(JRadioButton setVacuumRadioButton) {
		this.setVacuumRadioButton = setVacuumRadioButton;
	}

	public JRadioButton getSetObstacleRadioButton() {
		return setObstacleRadioButton;
	}

	public void setSetObstacleRadioButton(JRadioButton setObstacleRadioButton) {
		this.setObstacleRadioButton = setObstacleRadioButton;
	}

	public JRadioButton getEraseObjectRadioButton() {
		return eraseObjectRadioButton;
	}

	public void setEraseObjectRadioButton(JRadioButton eraseObjectRadioButton) {
		this.eraseObjectRadioButton = eraseObjectRadioButton;
	}
	
/*	//Esto posiblemente sea mejor hacerlo con un action listener
	private class acListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == setObstacleRadioButton){
				floor.SetPointerOption(RealMap.OBSTACLE);
			} else if (e.getSource() == eraseObjectRadioButton){
				floor.SetPointerOption(RealMap.DIRTY);
			} else if (e.getSource() == setVacuumRadioButton){
				floor.SetPointerOption(RealMap.VACUUM);
			}		
		}
		
	}
*/
	public InternalMap getInternalMap() {
		return internalMap;
	}

	public void setInternalMap(InternalMap internalMap) {
		this.internalMap = internalMap;
	}
}
