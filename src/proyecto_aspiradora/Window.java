/**
 * Proyecto de simulación de una aspiradora robot que se mueve de forma
 * autónoma para limpiar habitaciones
 */
package proyecto_aspiradora;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;

/**
 * Clase JFrame que será la ventana principal de la aplicación
 * 
 * @author Kevin Miguel Rivero Martin
 * @author Teguayco Gutiérrez González
 * @author Rubén Labrador Páez
 */
public class Window extends JFrame {
	private int WIDTH = 1000;						// Width of the frame in pixels
	private int HEIGHT = 800;						// height of the frame in pixels
	private Color COLOR_BACKGROUND = Color.BLACK;	// Color of the frame background
	private Menu menu = new Menu();
	private Floor floor = new Floor();
	
	Window() {
		buildWindow();
		
		GridBagConstraints constraints = new GridBagConstraints();
		
		// Menu
		constraints.gridx = 0;          // Column
		constraints.gridy = 0;  		// Row
		constraints.gridwidth = 1;		// Fill 1 column
		constraints.gridheight = 1;		// Fill 1 row
		constraints.weighty = 1;		// Row 0 should stretch
		constraints.weightx = 0.5;		// Column 0 should stretch
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
		constraints.weightx = 0.5;		// Column 0 should stretch
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
