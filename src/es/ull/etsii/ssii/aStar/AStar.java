package es.ull.etsii.ssii.aStar;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;

import es.ull.etsii.ssii.InternalMap;

public class AStar {
	Node [][] map;
	ArrayList <Node> openList;
	ArrayList <Node> closeList;
	Node start;
	Node objetive;
	
	
	public AStar (InternalMap map, Point finish){
		openList = new ArrayList<Node>();
		closeList = new ArrayList<Node>();
		
		for (int i = 0; i < map.getnCols(); i++){
			for (int j = 0; j < map.getnRows(); j++){
				if (map.isClean(j, i)){
					this.map [i][j] = new Node (i, j, false, false, false);
				} else if (map.isUnknown(j, i) || map.isObstacle(j, i)){
					this.map [i][j] = new Node (i, j, true, false, false);
				} else if (map.isDirty(j, i)){
					this.map [i][j] = new Node (i, j, false, true, false);
				} else if (map.isVacuum(j, i)){
					this.map [i][j] = new Node (i, j, false, false, true);
					start = this.map [i][j];
				}
			}
		}
		objetive = this.map [finish.x][finish.y];
	}
	
	private void run () {
		Node actual;
		start.realCost = 0;
		start.stimatedCost = g(start);
		openList.add(start);
		while (!openList.isEmpty()){
			Collections.sort(openList);
			actual = openList.get(0);
			openList.remove(0);
			if (actual.isFinish){
				return;
			} else {
				//pasar a lista cerrada
				//explorar vecinos
			}
		}
		return;
	}
	
	private int g(Node o){
		return Math.abs(o.xCoord-objetive.xCoord)+Math.abs(o.yCoord-objetive.yCoord);
	}
	
	private int f(){
		return 1;
	}
}
