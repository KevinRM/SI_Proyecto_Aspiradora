package vacuumCleaner;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class Menu extends JPanel {
	private Color BACKGROUND_COLOR = Color.LIGHT_GRAY;
	
	private Floor floor;
	private Vacuum vacuum;
	
	public Menu(Floor floor, Vacuum vacuum) {
		this.floor = floor;
		this.vacuum = vacuum;
		setBackground(BACKGROUND_COLOR);
		setLayout(new GridLayout(4, 1));
		
		JPanel roomSettingsPanel = new JPanel();
		JPanel drawSettingsPanel = new JPanel();
		JPanel animationSettingsPanel = new JPanel();
		InternalMap internalMapPanel = new InternalMap();
		
		add(roomSettingsPanel);
		add(drawSettingsPanel);
		add(animationSettingsPanel);
		add(internalMapPanel);
		
		LineBorder lineBorderPanel = (LineBorder) BorderFactory.createLineBorder(Color.BLACK);
		
		// Room
		roomSettingsPanel.setBorder(BorderFactory.createTitledBorder(lineBorderPanel, "Room"));
		roomSettingsPanel.setLayout(new FlowLayout());
		roomSettingsPanel.add(new JLabel("Rows:"));
		roomSettingsPanel.add(new JTextField());
		roomSettingsPanel.add(new JLabel("Columns:"));
		roomSettingsPanel.add(new JTextField());
		roomSettingsPanel.add(new JLabel("% RND OBST."));
		roomSettingsPanel.add(new JTextField());
		JButton applyButton = new JButton("APPLY");
		applyButton.setBackground(Color.WHITE);
	    applyButton.setForeground(Color.BLACK);
		roomSettingsPanel.add(applyButton);
		
		// Draw
		drawSettingsPanel.setBorder(BorderFactory.createTitledBorder(lineBorderPanel, "Draw"));
		drawSettingsPanel.setLayout(new GridLayout(2, 1));
		ButtonGroup drawButtonsGroup = new ButtonGroup();
		JRadioButton vacuumButton = new JRadioButton("Vacuum cleaner");
		JRadioButton obstacleButton = new JRadioButton("Obstacle");
		drawButtonsGroup.add(vacuumButton);
		drawButtonsGroup.add(obstacleButton);
		drawSettingsPanel.add(vacuumButton);
		drawSettingsPanel.add(obstacleButton);

		// Animation
		Border animationSettingsPanelBorder = BorderFactory.createTitledBorder(lineBorderPanel, "Animation");		
		animationSettingsPanel.setBorder(animationSettingsPanelBorder);
		animationSettingsPanel.add(new JLabel("Speed: "));
		JSlider speedSlider = new JSlider();
		speedSlider.setForeground(Color.WHITE);
		animationSettingsPanel.add(speedSlider);
		JButton startButton = new JButton("START");
		JButton resetButton = new JButton("RESET");
		startButton.setBackground(Color.WHITE);
		startButton.setForeground(Color.BLACK);
		resetButton.setBackground(Color.WHITE);
		resetButton.setForeground(Color.BLACK);
		animationSettingsPanel.add(startButton);
		animationSettingsPanel.add(resetButton);
		
		//initializeButtons();
	}
	
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
