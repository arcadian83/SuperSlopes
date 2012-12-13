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
}
