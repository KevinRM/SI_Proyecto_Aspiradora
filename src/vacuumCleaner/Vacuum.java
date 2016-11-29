/**
 * Proyecto de simulaci�n de una aspiradora robot que se mueve de forma
 * aut�noma para limpiar habitaciones
 */
package vacuumCleaner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

/**
 * Clase Vacuum que representa la aspiradora que se movera y limpiará el suelo
 * 
 * @author Kevin Miguel Rivero Martin
 * @author Teguayco Guti�rrez Gonz�lez
 * @author Rub�n Labrador P�ez
 */
public class Vacuum {
	private Floor floor;			// Floor where the vacuum move
	private static int xPos = 0;
	private static int yPos = 0;
	private Timer timerAlgorithm;
	private int TIME_TO_TIMER = 1000;	// In ms
	
	Vacuum(Floor floor) {
		this.floor = floor;		// Get the floor
		algorithClean();
	}
	
	// Get position in the array to paint vacuum during the algorithm
	public static void setPosition(int row, int column) {
		xPos = row;
		yPos = column;
	}
	
	private void algorithClean() {
		timerAlgorithm = new Timer(TIME_TO_TIMER, new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// getSensorParameters para obtener si es obstaculo o limpia de las celdas de alrededor
				// floor.setVacuum(i, j) para poner la aspiradora en una determinada celda
			}
		});
	}
	
	// Start algorithm
	public void startClean() {
		timerAlgorithm.start();
	}
	
	// Stop algorithm
	public void stopClean() {
		timerAlgorithm.stop();
	}
}
