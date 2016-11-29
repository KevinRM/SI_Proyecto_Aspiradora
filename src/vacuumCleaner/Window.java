/**
 * Proyecto de simulaci�n de una aspiradora robot que se mueve de forma
 * aut�noma para limpiar habitaciones
 */
package vacuumCleaner;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;

/**
 * Clase JFrame que ser� la ventana principal de la aplicaci�n
 * 
 * @author Kevin Miguel Rivero Martin
 * @author Teguayco Guti�rrez Gonz�lez
 * @author Rub�n Labrador P�ez
 */
public class Window extends JFrame {
	private int WIDTH = 1000;						// Width of the frame in pixels
	private int HEIGHT = 700;						// height of the frame in pixels
	private Color COLOR_BACKGROUND = Color.BLACK;	// Color of the frame background
	private Floor floor = new Floor();
	private Vacuum vacuum = new Vacuum(floor);
	private Menu menu = new Menu(floor, vacuum);
	
	Window() {
		buildWindow();
		
		GridBagConstraints constraints = new GridBagConstraints();
		
		// Menu
		constraints.gridx = 0;          // Column
		constraints.gridy = 0;  		// Row
		constraints.gridwidth = 1;		// Fill 1 column
		constraints.gridheight = 1;		// Fill 1 row
		constraints.weighty = 1;		// Row 0 should stretch
		constraints.weightx = 0.1;		// Column 0 should stretch
		constraints.fill = GridBagConstraints.BOTH;
		constraints.insets = new Insets(5, 5, 5, 0);	// Padding of element
		add(menu, constraints);
		constraints.weightx = 0;		// Reset
		constraints.weighty = 0;		// Reset
		
		// Floor
		constraints.gridx = 1;          // Column
		constraints.gridy = 0;  		// Row
		constraints.gridwidth = 1;		// Fill 1 column
		constraints.gridheight = 1;		// Fill 1 row
		constraints.weighty = 1;		// Row 0 should stretch
		constraints.weightx = 1;		// Column 0 should stretch
		constraints.fill = GridBagConstraints.BOTH;
		constraints.insets = new Insets(5, 0, 5, 5);	// Padding of element
		add(floor, constraints);
		constraints.weightx = 0;		// Reset
		constraints.weighty = 0;		// Reset
	}
	
	private void buildWindow() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Simulador aspiradora");
		setSize(WIDTH, HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		setLayout(new GridBagLayout());
		getContentPane().setBackground(COLOR_BACKGROUND);
	}
}
