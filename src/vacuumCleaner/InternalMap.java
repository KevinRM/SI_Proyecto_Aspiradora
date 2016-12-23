package vacuumCleaner;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * Clase que representa el mapa interno del robot 
 * (representado en la interfaz en la esquina inferior
 * izquierda)
 * @author Teguayco
 */
public class InternalMap extends JPanel {
	private int nrows;
	private int ncols;
	private int[][] map;
	int zeroX = 0;
	int zeroY = 0;
	public static int OBSTACLE = 1;
	public static int DIRTY = 0;
	public static int VACUUM = 2;
	public static int CLEAR = 3;
	
	public InternalMap() {
		
	}
	
	public InternalMap(int rows, int cols) {
		nrows = rows;
		ncols = cols;
		initializeMatrix();
	}
	
	public InternalMap(Floor2 realMap) {
		System.out.println(realMap.getX());
		System.out.println(realMap.getY());
		nrows = realMap.getY();
		ncols = realMap.getX();
		initializeMatrix();
	}
	
	private void initializeMatrix() {
		this.map = new int [getnCols()][getnRows()];
		
		for (int i = 0; i < getnCols(); i++) {
			for (int j = 0; j < getnRows(); j++) {
				map [i][j]= DIRTY;
			}
		}
		
		repaint();
	}
		
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int cellSideLength = (int) getCellSideLength();
		
		zeroX = (this.getWidth()-(getnCols()*cellSideLength))/2;
		zeroY = (this.getHeight()-(getnRows()*cellSideLength))/2;
		int finY = (cellSideLength*getnRows()) + zeroY;
		int finX = (cellSideLength*getnCols()) + zeroX;
		g.setColor(Color.BLACK);
		for (int i = 0; i <= getnCols(); i++){
			int lineX = zeroX+(i*cellSideLength);
			g.drawLine(lineX, zeroY, lineX, finY);
		}
		for (int i = 0; i <= getnRows(); i++){
			int lineY = zeroY+(i*cellSideLength);
			g.drawLine(zeroX, lineY, finX, lineY);
		}
		for(int i = 0; i < getnCols(); i++){
			for(int j = 0; j < getnRows(); j++){
				if (map [i][j] == OBSTACLE){
					g.setColor(Color.BLACK);

				}else if (map [i][j] == DIRTY){
					g.setColor(new Color(153, 128, 0));

				}else if ((map [i][j] == VACUUM)){
					g.setColor(Color.GREEN);
				}else if ((map [i][j] == CLEAR)){
					g.setColor(Color.WHITE);
				}
				g.fillRect(zeroX+(i*cellSideLength)+1, zeroY+(j*cellSideLength)+1, 
						cellSideLength-1, cellSideLength-1);
			}
		}
	}
	
	private double getCellSideLength() {
		if ((getnRows() > 0) && (getnCols() > 0)) {
			if ((getWidth() / getnRows()) <= (getHeight() / getnCols())) {
				return getWidth() / getnCols();
			} else {
				return getHeight() / getnRows();
			}
		}
		
		return 0.0;
	}

	public int getnRows() {
		return nrows;
	}

	public void setnRows(int nrows) {
		this.nrows = nrows;
	}

	public int getnCols() {
		return ncols;
	}

	public void setnCols(int ncols) {
		this.ncols = ncols;
	}

	public int[][] getMap() {
		return map;
	}

	public void setMap(int[][] map) {
		this.map = map;
	}
	
	
}
