/**
 * Proyecto de simulaci�n de una aspiradora robot que se mueve de forma
 * aut�noma para limpiar habitaciones
 */
package vacuumCleaner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
	private static int rowPos = 0;
	private static int colPos = 0;
	private Timer timerAlgorithm;
	private int TIME_TO_TIMER = 1000;	// In ms

	Vacuum(Floor floor) {
		this.floor = floor;		// Get the floor
		algorithClean();
	}

	// Set position in the array to paint vacuum during the algorithm
	public static void setPosition(int row, int column) {
		rowPos = row;
		colPos = column;
	}

	private void algorithClean() {
		timerAlgorithm = new Timer(TIME_TO_TIMER, new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*floor.getCells()[xPos][yPos].removeVacuumHere();
				yPos--;
				floor.setVacuum(xPos, yPos);*/
				// getSensorParameters para obtener si es obstaculo o limpia de las celdas de alrededor
				// floor.setVacuum(i, j) para poner la aspiradora en una determinada celda
			}
		});
	}

	// Return the parameter of sensor [clean, obstacle]
	public void getSensorParameters() {
		boolean exit = false;
		ArrayList<Integer> cellsRowCanMove = new ArrayList<Integer>();
		ArrayList<Integer> cellsColumnCanMove = new ArrayList<Integer>();
		int posRowTemp = rowPos;
		int posColTemp = colPos;
		int colLimitMin = Integer.MIN_VALUE;
		int colLimitMax = Integer.MAX_VALUE;
		//int rowLimitMin = Integer.MIN_VALUE;
		//int rowLimitMax = Integer.MAX_VALUE;

		// Left
		/*while(!exit) {
			posColTemp--;
			if (!floor.getSensorParameters(posRowTemp, posColTemp)) {
				cellsRowCanMove.add(posRowTemp);
				cellsColumnCanMove.add(posColTemp);
			} else {
				colLimitMin = posColTemp;	// Set minimum column
				posColTemp = colPos;	// Reset position
				exit = true;
			}
		}
		exit = false;
		// Right
		while(!exit) {
			posColTemp++;
			if (!floor.getSensorParameters(posRowTemp, posColTemp)) {
				cellsRowCanMove.add(posRowTemp);
				cellsColumnCanMove.add(posColTemp);
			} else {
				colLimitMax = posColTemp;	// Set minimum column
				posColTemp = colPos;	// Reset position
				exit = true;
			}
		}
		exit = false;
		// Down
		while(!exit) {
			posRowTemp++;
			if (!floor.getSensorParameters(posRowTemp, posColTemp)) {
				cellsRowCanMove.add(posRowTemp);
				cellsColumnCanMove.add(posColTemp);
			} else {
				rowLimitMax = posRowTemp;	// Set minimum column
				posRowTemp = rowPos;	// Reset position
				exit = true;
			}
		}
		exit = false;
		// Up
		while(!exit) {
			posRowTemp--;
			if (!floor.getSensorParameters(posRowTemp, posColTemp)) {
				cellsRowCanMove.add(posRowTemp);
				cellsColumnCanMove.add(posColTemp);
			} else {
				rowLimitMin = posRowTemp;	// Set minimum column
				posRowTemp = rowPos;	// Reset position
				exit = true;
			}
		}
		exit = false;*/
		//Down - Left - Right
		while(!exit) {
			if (!floor.getSensorParameters(posRowTemp, posColTemp)) {
				cellsRowCanMove.add(posRowTemp);
				cellsColumnCanMove.add(posColTemp);

				boolean exitColumns = false;
				while(!exitColumns && posColTemp > colLimitMin) {
					posColTemp--;
					if (!floor.getSensorParameters(posRowTemp, posColTemp)) {
						cellsRowCanMove.add(posRowTemp);
						cellsColumnCanMove.add(posColTemp);
					} else {
						exitColumns = true;
					}
				}
				colLimitMin = posColTemp;
				posColTemp = colPos;

				exitColumns = false;
				while(!exitColumns && posColTemp < colLimitMax) {
					posColTemp++;
					if (!floor.getSensorParameters(posRowTemp, posColTemp)) {
						cellsRowCanMove.add(posRowTemp);
						cellsColumnCanMove.add(posColTemp);
					} else {
						exitColumns = true;
					}
				}
				colLimitMax = posColTemp;
				posColTemp = colPos;

			} else {
				//rowLimitMax = posRowTemp;	// Set minimum column
				posRowTemp = rowPos;	// Reset position
				exit = true;
			}
			posRowTemp++;
		}
		//Up - Left - Right
		colLimitMin = Integer.MIN_VALUE;
		colLimitMax = Integer.MAX_VALUE;
		exit = false;
		while(!exit) {
			if (!floor.getSensorParameters(posRowTemp, posColTemp)) {
				cellsRowCanMove.add(posRowTemp);
				cellsColumnCanMove.add(posColTemp);

				boolean exitColumns = false;
				while(!exitColumns && posColTemp > colLimitMin) {
					posColTemp--;
					if (!floor.getSensorParameters(posRowTemp, posColTemp)) {
						cellsRowCanMove.add(posRowTemp);
						cellsColumnCanMove.add(posColTemp);
					} else {
						exitColumns = true;
					}
				}
				colLimitMin = posColTemp;
				posColTemp = colPos;

				exitColumns = false;
				while(!exitColumns && posColTemp < colLimitMax) {
					posColTemp++;
					if (!floor.getSensorParameters(posRowTemp, posColTemp)) {
						cellsRowCanMove.add(posRowTemp);
						cellsColumnCanMove.add(posColTemp);
					} else {
						exitColumns = true;
					}
				}
				colLimitMax = posColTemp;
				posColTemp = colPos;

			} else {
				//rowLimitMax = posRowTemp;	// Set minimum column
				posRowTemp = rowPos;	// Reset position
				exit = true;
			}
			posRowTemp--;
		}
		//Up - Left - Right
		/*posRowTemp = rowPos;	// Reset position row
		while(posRowTemp > rowLimitMin) {
			posRowTemp--;
			boolean exitColumns = false;
			while(!exitColumns && posColTemp > colLimitMin) {
				posColTemp--;
				if (!floor.getSensorParameters(posRowTemp, posColTemp)) {
					cellsRowCanMove.add(posRowTemp);
					cellsColumnCanMove.add(posColTemp);
				} else {
					exitColumns = true;
				}
			}
			colLimitMin = posColTemp;
			posColTemp = colPos;

			exitColumns = false;
			while(!exitColumns && posColTemp < colLimitMax) {
				posColTemp++;
				if (!floor.getSensorParameters(posRowTemp, posColTemp)) {
					cellsRowCanMove.add(posRowTemp);
					cellsColumnCanMove.add(posColTemp);
				} else {
					exitColumns = true;
				}
			}
			colLimitMax = posColTemp;
			posColTemp = colPos;
		}*/

		for (int i = 0; i < cellsRowCanMove.size(); i++) {
			floor.getCells()[cellsRowCanMove.get(i)][cellsColumnCanMove.get(i)].setCleanCell();
		}
	}

	// Start algorithm
	public void startClean() {
		//timerAlgorithm.start();
		getSensorParameters();
	}

	// Stop algorithm
	public void stopClean() {
		timerAlgorithm.stop();
	}
}
