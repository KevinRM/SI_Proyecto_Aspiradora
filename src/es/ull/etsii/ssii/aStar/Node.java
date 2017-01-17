package es.ull.etsii.ssii.aStar;

public class Node implements Comparable <Node> {
	int realCost;
	int stimatedCost;
	public int xCoord;
	public int yCoord;
	Node prev = null;
	boolean isStart = false;
	boolean isFinish = false;
	boolean isOpaque = false;
	
	public Node (int x, int y, boolean opaque, boolean finish, boolean start ){
		xCoord = x;
		yCoord = y;
		isOpaque = opaque;
		isFinish = finish;
		isStart = start;
	}

	@Override
	public int compareTo(Node o) {
		if (realCost+stimatedCost < o.realCost+o.stimatedCost){
			return -1;
		}
		if (realCost+stimatedCost > o.realCost+o.stimatedCost){
			return 1;
		}
		// TODO Auto-generated method stub
		return 0;
	}
}
