package net.arcadian83.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.src.Tessellator;

public class VertexLight {
	
	public int brightness;
	public float red;
	public float green;
	public float blue;
	
	// x,y,z are vertex location in the world (block location + vertex offset)
	//public VertexLight(Block block, float x, float y, float z, RenderBlocks renderer) {
	public VertexLight(int direction, Tuple corner, HashMap<Tuple, Integer> aoBrightness) {
		
		
		
		
	}
	
	// given a face (side) and corner of that face and the brightness of our neighboring blocks
	// calculate the brightness at the given location
	public static int getBrightnessAtFaceCorner(int direction, Tuple corner, HashMap<Tuple, Integer> aoBrightness) {
		
		// convert the Tuple from a convenient input (0 to 1 on each axis so it can be an coordinate offset)
		// to a relative, facing-oriented Tuple (-1 to 1 to represent direction)
		corner = new Tuple(corner); // make a copy of corner so as not to change the one passed to us
		if(corner.x == 0) corner.x = -1;
		if(corner.y == 0) corner.y = -1;
		if(corner.z == 0) corner.z = -1;
		
		// these are the brightnesses of the neighboring blocks around the given corner
		// in the given direction
		List<Tuple> tuples = new ArrayList<Tuple>();
		
		//
		Tuple main = new Tuple(0,0,0); // TODO give a proper default
		
		for(int zRel = -1; zRel <= 1; zRel++) {
    		for(int yRel = -1; yRel <= 1; yRel++) {
    			for(int xRel = -1; xRel <= 1; xRel++) {
    				Tuple tuple = new Tuple(xRel, yRel, zRel);
    				//if(tuple.facesDirection(direction) && tuple.surroundsCorner(corner)){
    				if(tuple.inDirectionAndCorner(direction, corner)){
    					tuples.add(tuple);
    					if(tuple.isCenterOfFace()) main = tuple;
    				}
    			}
        	}
    	}
		
		int defaultBrightness = aoBrightness.get(main);
		int totalBrightness = 0;
		for(Tuple tuple : tuples) {
			int brightness = aoBrightness.get(tuple);
			if(brightness == 0) {
				brightness = defaultBrightness;
			}
			totalBrightness += brightness;
		}
		
		return totalBrightness >> 2 & 16711935;
	}
	
	public static void addVertex(Tessellator tess, int lightingDirection, Tuple coordinates, Tuple offset, double textureU, double textureV, HashMap<Tuple, Integer>aoBrightness) {
		tess.setBrightness(VertexLight.getBrightnessAtFaceCorner(lightingDirection, offset, aoBrightness));
        tess.addVertexWithUV(coordinates.x + offset.x, coordinates.y + offset.y, coordinates.z + offset.z, textureU, textureV);
	}
}
