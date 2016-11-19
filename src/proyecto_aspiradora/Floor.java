/**
 * Proyecto de simulaci�n de una aspiradora robot que se mueve de forma
 * aut�noma para limpiar habitaciones
 */
package proyecto_aspiradora;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

/**
 * Clase Floor que ser� un JPanel representando el suelo por donde se
 * movera la aspiradora, contendra obstaculos m�viles y fijos
 * 
 * @author Kevin Miguel Rivero Martin
 * @author Teguayco Guti�rrez Gonz�lez
 * @author Rub�n Labrador P�ez
 */
public class Floor extends JPanel {
	private Color COLOR_BACKGROUND = Color.GRAY;
	private int HGAP = 1;			// Horizontal gap between components
	private int VGAP = 1;			// Vertical gap between components
	private int ROWS = 65;			// Rows of the floor
	private int COLUMNS = 65;		// Columns of the floor
	private FloorCell cells[][];
	
	Floor() {
		setBackground(COLOR_BACKGROUND);
		setLayout(new GridLayout(ROWS, COLUMNS, HGAP, VGAP));
		
		// Initializing floor cells
		cells = new FloorCell[ROWS][COLUMNS];
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				cells[i][j] = new FloorCell();
				add(cells[i][j]);
			}
		}
	}
}
