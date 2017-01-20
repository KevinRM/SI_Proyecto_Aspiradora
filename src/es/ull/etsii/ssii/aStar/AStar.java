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
		this.map = new Node [map.getnCols()][map.getnRows()];
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
	
	private ArrayList <Node> RecCamino(Node last){
	  ArrayList <Node> aux = new ArrayList <Node>();
	  Node actual = last;
	  while (actual.prev != null){
	    aux.add(actual);
	    actual = actual.prev;
	  }
    return aux;
	  
	}
	
	public ArrayList <Node> run() {
		Node actual;
		start.realCost = 0;
		start.stimatedCost = g(start);
		openList.add(start);
		while (!openList.isEmpty()){
			Collections.sort(openList);
			actual = openList.get(0);
			openList.remove(0);
			if (actual.isFinish){
				return  RecCamino(actual);
			} else {
				closeList.add(actual);
				int acRealCost = actual.realCost+1;
				for (Node aux : adjacent(actual)){
				  if (openList.contains(aux)){
				    if ( aux.realCost > acRealCost){
				      aux.realCost = acRealCost;
				      aux.prev = actual;
				    }
				  } else {
				    aux.realCost = acRealCost;
				    aux.stimatedCost = g (aux);
				    aux.prev = actual;
				    openList.add(aux);
				  }
				}
			}
			
		}
		return null;
	}
	
	private ArrayList <Node> adjacent(Node o){
    ArrayList<Node> aux = new ArrayList <Node>();
    if (up(o) != null && !up(o).isOpaque && !closeList.contains(up(o))){
      aux.add(up(o));
    }
    if (down(o) != null && !down(o).isOpaque  && !closeList.contains(down(o))){
      aux.add(down(o));
    }
    if (left(o) != null && !left(o).isOpaque  && !closeList.contains(left(o))){
      aux.add(left(o));
    }
    if (right(o) != null && !right(o).isOpaque && !closeList.contains(right(o))){
      aux.add(right(o));
    }
    
    return aux;
	  
	}
	
	private Node up (Node o){
	  if (o.yCoord > 0){
	    return map [o.xCoord][o.yCoord-1];
	  } else {
	    return null;
	  }
	}
	
	private Node down (Node o){
    if (o.yCoord < map[0].length-1){
      return map [o.xCoord][o.yCoord+1];
    } else {
      return null;
    }
  }
	
	private Node left (Node o){
    if (o.xCoord > 0){
      return map [o.xCoord-1][o.yCoord];
    } else {
      return null;
    }
  }
	
	private Node right (Node o){
	  if (o.xCoord < map.length-1){
      return map [o.xCoord+1][o.yCoord];
    } else {
      return null;
    }
  }
	
	private int g(Node o){
		return Math.abs(o.xCoord-objetive.xCoord)+Math.abs(o.yCoord-objetive.yCoord);
	}
	
}
