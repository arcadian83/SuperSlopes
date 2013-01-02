package net.arcadian83.common;

import java.util.ArrayList;

public class SlopeFace {
	public ArrayList<Vector> vertexes = new ArrayList<Vector>();
	public int direction = Direction.up;
	
	// returns "down" for a flat bottom face,
	// "north" for a sideways-facing face (perfectly vertical),
	// and "up", otherwise
	/*public int getTextureFacingDirection() {
		int[]x;
		for(Vector v : vertexes) {
			
		}
	}*/
	
	public void rotateClockwise() {
		for(Vector vertex : vertexes) {
			vertex.rotateClockwise();
		}
		direction = Direction.rotateClockwise(direction);
	}
	
	public void rollNorth() {
		for(Vector vertex : vertexes) {
			vertex.rollNorth();
		}
		direction = Direction.rollNorth(direction);
	}
}
