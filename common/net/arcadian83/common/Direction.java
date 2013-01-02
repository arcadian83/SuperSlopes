package net.arcadian83.common;

import java.util.ArrayList;

public class Direction {
	public static final int down = 0;
	public static final int up = 1;
	public static final int north = 2;
	public static final int south = 3;
	public static final int east = 4;
	public static final int west = 5;
	public static final ArrayList<Integer> all = new ArrayList<Integer>() {{
			add(Direction.down);
			add(Direction.up);
			add(Direction.north);
			add(Direction.south);
			add(Direction.east);
			add(Direction.west);
		}};
		
	public static int rotateClockwise(int direction) {
		switch(direction) {
			case north: return east;
			case east: return south;
			case south: return west;
			case west: return north;
		}
		return direction;
	}
	
	public static int rollNorth(int direction) {
		switch(direction) {
			case down: return south; 
			case up: return north;
			case north: return down;
			case south: return up;
		}
		return direction;
	}
}
