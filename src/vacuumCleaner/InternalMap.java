package vacuumCleaner;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class InternalMap extends MapPanel {
	
	public InternalMap(int rows, int cols) {
		super(rows, cols);
		initializeUnknownCells();
		
		setPreferredSize(new Dimension(100, 300));
	}
	
	public void resizeMap(int newRows, int newCols) {
		super.resizeMap(newRows, newCols);
		initializeUnknownCells();
	}
}
