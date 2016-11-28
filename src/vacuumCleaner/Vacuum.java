/**
 * Proyecto de simulaci�n de una aspiradora robot que se mueve de forma
 * aut�noma para limpiar habitaciones
 */
package vacuumCleaner;

/**
 * Clase Vacuum que representa la aspiradora que se movera y limpiará el suelo
 * 
 * @author Kevin Miguel Rivero Martin
 * @author Teguayco Guti�rrez Gonz�lez
 * @author Rub�n Labrador P�ez
 */
public class Vacuum {
	private Floor floor;		// Floor where the vacuum move
	
	Vacuum(Floor floor) {
		this.floor = floor;		// Get the floor
	}
	
	private void setVacuum(int row, int column) {
		floor.setVacuum(row, column);
	}
}
