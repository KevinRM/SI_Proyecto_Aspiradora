/**
 * Proyecto de simulaci�n de una aspiradora robot que se mueve de forma
 * aut�noma para limpiar habitaciones
 */
package vacuumCleaner;

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
	//private int THICKNESS_BORDER = 1;
	//private Color COLOR_BORDER = Color.GRAY;
	private Color DIRTY_COLOR = Color.decode("#B45F04");		// Default color of the cell
	private Color CLEAN_COLOR = Color.WHITE;	// Color when the cell is clean
	private boolean paintVacuum = false;		// True when the vacuum is in the cell and need paint it
	
	FloorCell() {
		setBackground(DIRTY_COLOR);
		//setBorder(BorderFactory.createLineBorder(COLOR_BORDER, THICKNESS_BORDER));
	}
	
	public void setCleanCell() {
		setBackground(CLEAN_COLOR);
	}
	
	// Active the boolean that paint the vacuum
	public void setVacuumHere() {
		paintVacuum = true;
		this.repaint();
	}

	// Deactivate the boolean that paint the vacuum and repaint cell
	public void removeVacuumHere() {
		paintVacuum = false;
		setCleanCell();
		this.repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (paintVacuum) {
			Graphics2D g2d = (Graphics2D) g;
			
			g2d.fillOval(0, 0, this.getWidth(), this.getHeight());
		}
	}
}
