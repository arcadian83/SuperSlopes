package net.arcadian83.common;



public class Vector {
	
	// a Vector is a location (Tuple of x,y,z) and a direction (enum Direction)
	// This is needed for proper vertex lighting, as the vertexes of a face may not
	// all "face" the same direction, especially in the case of sloped faces.
	public Tuple loc;
	public int dir; // direction facing outward
	
	public Vector(int x, int y, int z, int direction) {
		loc = new Tuple(x,y,z);
		this.dir = direction;
	}
	
	public void rotateClockwise() {
		loc.rotateClockwise();
		dir = Direction.rotateClockwise(dir);
	}
	
	public void rollNorth() {
		loc.rollNorth();
		dir = Direction.rollNorth(dir);
	}
}
