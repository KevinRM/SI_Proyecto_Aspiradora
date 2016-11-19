/**
 * Proyecto de simulación de una aspiradora robot que se mueve de forma
 * autónoma para limpiar habitaciones
 */
package proyecto_aspiradora;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;


/**
 * Clase Menu que será el menú de la aplicación
 * 
 * @author Kevin Miguel Rivero Martin
 * @author Teguayco Gutiérrez González
 * @author Rubén Labrador Páez
 */
public class Menu extends JPanel {
	private Color COLOR_BACKGROUND = Color.LIGHT_GRAY;
	private JButton startButton = new JButton("Empezar");
	private JButton stopButton = new JButton("Parar");
	private JButton restartButton = new JButton("Reiniciar");
	private JButton setVacuum = new JButton("Poner aspiradora");
	private JButton setMobilObstacle = new JButton("Poner obstáculo móvil");
	private JButton setStillObstacle = new JButton("Poner obstáculo inmóvil");
	
	Menu() {
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
		
		
				
		
				
		
	}
}
