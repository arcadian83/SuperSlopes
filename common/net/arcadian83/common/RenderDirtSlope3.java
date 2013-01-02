package net.arcadian83.common;

import net.minecraft.src.Block;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.RenderBlocks;


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
