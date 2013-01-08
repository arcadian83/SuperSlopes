package net.arcadian83;



public class RenderDirtSlope2 extends RenderDirtSlope {

	private int metaOffset = 16;
	
	@Override
	public int getRenderId() {
		return Arc.dirtSlopeRenderId2;
	}
	
	public int getMetaOffset() {
		return metaOffset;
	}
}
