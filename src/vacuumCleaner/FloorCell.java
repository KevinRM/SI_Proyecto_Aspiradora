/**
 * Proyecto de simulaci�n de una aspiradora robot que se mueve de forma
 * aut�noma para limpiar habitaciones
 */
package vacuumCleaner;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * Clase FloorCell que representa cada cuadro o celda del suelo
 * 
 * @author Kevin Miguel Rivero Martin
 * @author Teguayco Guti�rrez Gonz�lez
 * @author Rub�n Labrador P�ez
 */
public class FloorCell extends JPanel {
	private int row;		// Row to know index in vacuum
	private int column;		// Column to know index in vacuum
	private Color DIRTY_COLOR = Color.decode("#B45F04");	// Default color of the cell
	private Color CLEAN_COLOR = Color.WHITE;				// Color when the cell is clean
	private Color WALL_COLOR = Color.BLACK;					// Color of the wall
	private boolean isVacuum = false;					// True when the vacuum is in the cell and need paint it
	private boolean isObstacle = false;
	//private boolean isClean = false;

	FloorCell(int row, int column, String is) {
		this.row = row;
		this.column = column;

		if (is != "wall") {
			setBackground(DIRTY_COLOR);
		} else {
			setBackground(WALL_COLOR);
			isObstacle = true;
		}
	}

	public void setCleanCell() {
		setBackground(CLEAN_COLOR);
	}

	// Active the boolean that paint the vacuum
	public void setVacuumHere() {
		isVacuum = true;
		this.repaint();
	}

	// Deactivate the boolean that paint the vacuum and repaint cell
	public void removeVacuumHere() {
		isVacuum = false;
		setCleanCell();
	}
	
	// Get if the cell has the vacuum
	public boolean haveVacuum() {
		return isVacuum;
	}

	// Active the boolean that paint obstacle
	public void setObstacleHere() {
		setBackground(WALL_COLOR);
		isObstacle = true;
	}
	
	// Get if is an obstacle
	public boolean isObstacle() {
		return isObstacle;
	}

	// Return the parameter of sensor [clean, obstacle]
	public boolean/*boolean[]*/ getSensorParameters() {
		//boolean[] parameters = {isClean, isObstacle};
		//return parameters;
		return isObstacle;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (isVacuum) {
			Graphics2D g2d = (Graphics2D) g;
			g2d.setColor(Color.GREEN);
			g2d.fillOval(0, 0, this.getWidth(), this.getHeight());
			g2d.setColor(Color.RED);
			g2d.setStroke(new BasicStroke(2));
			g2d.drawLine(0, getHeight()/2, (getWidth()/2), (getHeight()/2));
		}
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}
}
