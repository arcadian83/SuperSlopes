package net.arcadian83;



public class RenderDirtSlope3 extends RenderDirtSlope {

	private int metaOffset = 32;
	
	@Override
	public int getRenderId() {
		return Arc.dirtSlopeRenderId3;
	}
	
	public int getMetaOffset() {
		return metaOffset;
	}
}
