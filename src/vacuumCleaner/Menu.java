/**
 * Proyecto de simulaci�n de una aspiradora robot que se mueve de forma
 * aut�noma para limpiar habitaciones
 */
package vacuumCleaner;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;


/**
 * Clase Menu que ser� el men� de la aplicaci�n
 * 
 * @author Kevin Miguel Rivero Martin
 * @author Teguayco Guti�rrez Gonz�lez
 * @author Rub�n Labrador P�ez
 */
public class Menu extends JPanel {
	private Floor floor;			// Floor of the application
	private Vacuum vacuum;
	private MouseListener eventMouseToRemove;	// Reference to mouseListener for remove it
	private Color COLOR_BACKGROUND = Color.LIGHT_GRAY;
	private JButton startButton = new JButton("Empezar");
	private JButton stopButton = new JButton("Parar");
	private JButton restartButton = new JButton("Reiniciar");
	private JButton setVacuum = new JButton("Poner aspiradora");
	private JButton setMobilObstacle = new JButton("Poner obst�culo m�vil");
	private JButton setStillObstacle = new JButton("Poner obst�culo inm�vil");
	
	Menu(Floor floor, Vacuum vacuum) {
		this.floor = floor;
		this.vacuum = vacuum;
		setBackground(COLOR_BACKGROUND);
		setLayout(new GridBagLayout());
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;		// Buttons can stretch
		
		// Still obstacle Button
		constraints.gridx = 0;          // Column
		constraints.gridy = 0;  		// Row
		constraints.gridwidth = 3;		// Fill 1 column
		constraints.gridheight = 1;		// Fill 1 row
		constraints.insets = new Insets(0, 0, 5, 0);	// Padding of element
		add(setStillObstacle, constraints);
		
		// Mobile obstacle Button
		constraints.gridx = 0;          // Column
		constraints.gridy = 1;  		// Row
		constraints.gridwidth = 3;		// Fill 1 column
		constraints.gridheight = 1;		// Fill 1 row
		constraints.insets = new Insets(0, 0, 5, 0);	// Padding of element
		add(setMobilObstacle, constraints);
		
		// Vacuum Button
		constraints.gridx = 0;          // Column
		constraints.gridy = 2;  		// Row
		constraints.gridwidth = 3;		// Fill 1 column
		constraints.gridheight = 1;		// Fill 1 row
		constraints.insets = new Insets(0, 0, 5, 0);	// Padding of element
		add(setVacuum, constraints);
		
		// Start Button
		constraints.gridx = 0;          // Column
		constraints.gridy = 3;  		// Row
		constraints.gridwidth = 1;		// Fill 1 column
		constraints.gridheight = 1;		// Fill 1 row
		constraints.insets = new Insets(0, 0, 0, 1);	// Padding of element
		add(startButton, constraints);
		
		// Stop Button
		constraints.gridx = 1;          // Column
		constraints.gridy = 3;  		// Row
		constraints.gridwidth = 1;		// Fill 1 column
		constraints.gridheight = 1;		// Fill 1 row
		constraints.insets = new Insets(0, 0, 0, 1);	// Padding of element
		add(stopButton, constraints);
				
		// Restart Button
		constraints.gridx = 2;          // Column
		constraints.gridy = 3;  		// Row
		constraints.gridwidth = 1;		// Fill 1 column
		constraints.gridheight = 1;		// Fill 1 row
		add(restartButton, constraints);
		
		initializeButtons();
	}
	
	private void initializeButtons() {
		// Button set Vacuum
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
	}
}
