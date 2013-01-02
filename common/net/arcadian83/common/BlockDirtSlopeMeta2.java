package net.arcadian83.common;

import java.util.HashMap;

import net.minecraft.src.Material;

public class BlockDirtSlopeMeta2 extends BlockDirtSlope{

	private int metaOffset = 16;
	
	public BlockDirtSlopeMeta2(int id, int texture) {
		super(id, texture);
	}
	
	public int getRenderType() {
		return Arc.dirtSlopeRenderId2;
	}
	
	public int getMetaOffset() {
		return metaOffset;
	}
}
