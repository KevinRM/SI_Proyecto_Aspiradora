package vacuumCleaner;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class RealMap extends JPanel {
	private int [][] map;
	private Window window;
	private int x = 0;
	private int y = 0;
	private int cellSide = 0;
	private int pointerOption = 1;
	int zeroX = 0;
	int zeroY = 0;
	public static int OBSTACLE = 1;
	public static int DIRTY = 0;
	public static int VACUUM = 2;
	public static int CLEAR = 3;
	private boolean vacuum = false;
	
	RealMap (Window win){
		window = win;
		this.addMouseMotionListener(new motionListener());
	}
	public void setCels (int x, int y){
		this.x = x;
		this.y = y;
		this.map = new int [x][y];
		
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				map [i][j]= DIRTY;
			}
		}
		
		repaint();
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		celDim();
		zeroX = (this.getWidth()-(x*cellSide))/2;
		zeroY = (this.getHeight()-(y*cellSide))/2;
		int finY = (cellSide*y) + zeroY;
		int finX = (cellSide*x) + zeroX;
		g.setColor(Color.BLACK);
		for (int i = 0; i <= x; i++){
			int lineX = zeroX+(i*cellSide);
			g.drawLine(lineX, zeroY, lineX, finY);
		}
		for (int i = 0; i <= y; i++){
			int lineY = zeroY+(i*cellSide);
			g.drawLine(zeroX, lineY, finX, lineY);
		}
		for(int i = 0; i < x; i++){
			for(int j = 0; j < y; j++){
				if (map [i][j] == OBSTACLE){
					g.setColor(Color.BLACK);

				}else if (map [i][j] == DIRTY){
					g.setColor(new Color(153, 128, 0));

				}else if ((map [i][j] == VACUUM)){
					g.setColor(Color.GREEN);
				}else if ((map [i][j] == CLEAR)){
					g.setColor(Color.WHITE);
				}
				g.fillRect(zeroX+(i*cellSide)+1, zeroY+(j*cellSide)+1, cellSide-1, cellSide-1);
			}
		}
	}
	
	private void celDim() {
		if ((this.getWidth()/x)<=(this.getHeight()/y)){
			cellSide = (this.getWidth()/x);
		}
		else {
			cellSide = (this.getHeight()/y);
		}
	}
	
	public void SetPointerOption (int option){
		pointerOption = option;	
	}

	private class motionListener implements MouseMotionListener{
		@Override
		public void mouseDragged(MouseEvent e) {
			int eventX = (e.getX()-(zeroX))/cellSide;
			int eventY = (e.getY()-(zeroY))/cellSide;
			if (eventX < x && eventY < y){
				if (pointerOption == VACUUM){
					if (!vacuum){
						map[eventX][eventY] = pointerOption;
						vacuum = true;
					}
				} else if (map[eventX][eventY] == VACUUM && pointerOption != VACUUM){
					map[eventX][eventY] = pointerOption;
					vacuum = false;
				} else if (map[eventX][eventY] != VACUUM && pointerOption != VACUUM){
					map[eventX][eventY] = pointerOption;
				}
				
			}
			repaint();
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub

		}
	}
}
