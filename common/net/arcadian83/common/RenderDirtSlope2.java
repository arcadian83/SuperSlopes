package net.arcadian83.common;

import net.minecraft.src.Block;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.RenderBlocks;


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
