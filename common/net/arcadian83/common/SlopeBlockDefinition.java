package net.arcadian83.common;

import java.util.ArrayList;

public class SlopeBlockDefinition {
	ArrayList<Tuple> vertexesToRemove = new ArrayList<Tuple>();
	ArrayList<SlopeFace> slopesToAdd = new ArrayList<SlopeFace>();
	
	public SlopeBlockDefinition() {
		
	}
	
	public void removeVertex(Tuple vertex) {
		vertexesToRemove.add(vertex);
	}
	
	public void addSlope(SlopeFace slopeFace) {
		slopesToAdd.add(slopeFace);
	}
	
	public void rotateClockwise() {
		for(Tuple vertex : vertexesToRemove) {
			vertex.rotateClockwise();
		}
		for(SlopeFace slope : slopesToAdd) {
			slope.rotateClockwise();
		}
	}
	
	public void rollNorth() {
		for(Tuple vertex : vertexesToRemove) {
			vertex.rollNorth();
		}
		for(SlopeFace slope : slopesToAdd) {
			slope.rollNorth();
		}
	}
}
