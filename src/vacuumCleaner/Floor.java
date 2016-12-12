/**
 * Proyecto de simulaci�n de una aspiradora robot que se mueve de forma
 * aut�noma para limpiar habitaciones
 */
package vacuumCleaner;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

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
	private int ROWS = 20;			// Rows of the floor
	private int COLUMNS = 20;		// Columns of the floor
	private FloorCell cells[][];

	Floor() {
		setBackground(COLOR_BACKGROUND);
		setLayout(new GridLayout(ROWS, COLUMNS, HGAP, VGAP));

		// Initializing floor cells
		cells = new FloorCell[ROWS][COLUMNS];
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				if (j == 0 || j == (COLUMNS - 1) || i == 0 || (i == ROWS - 1)) {	// Paint wall
					cells[i][j] = new FloorCell(i, j, "wall");
					add(cells[i][j]);
				} else {
					cells[i][j] = new FloorCell(i, j, "nowall");
					add(cells[i][j]);
				}
			}
		}
	}

	public void setVacuum(int row, int column) {
		cells[row][column].setVacuumHere();
	}

	// Return the parameter of sensor [clean, obstacle]
	public boolean/*boolean[]*/ getSensorParameters(int row, int column) {
		//return cells[row][column].getSensorParameters();
		return cells[row][column].getSensorParameters();
	}

	// Get cells of the floor
	public FloorCell[][] getCells() {
		return cells;
	}

	// Get rows of the floor
	public int getNumberRows() {
		return ROWS;
	}

	// Get columns of the floor
	public int getNumberColumns() {
		return COLUMNS;
	}
}
