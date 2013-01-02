package net.arcadian83.common;

import java.util.HashMap;

import net.minecraft.src.Material;

public class BlockDirtSlopeMeta3 extends BlockDirtSlope{

	private int metaOffset = 32;
	
	public BlockDirtSlopeMeta3(int id, int texture) {
		super(id, texture);
	}
	
	public int getRenderType() {
		return Arc.dirtSlopeRenderId3;
	}
	
	public int getMetaOffset() {
		return metaOffset;
	}
}
